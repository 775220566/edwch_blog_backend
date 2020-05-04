package com.per.blog.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.per.blog.annotation.AuthToken;
import com.per.blog.common.Result;
import com.per.blog.common.StatusCode;
import com.per.blog.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2020/2/8 0008.
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    //存放鉴权信息的Header名称，默认是Authorization
    private String httpHeader = "Auth-token";

    //鉴权失败后返回的错误信息，默认为401 unauthorized
    private String unauthorizedErrorMessage = "401 unauthorized";

    //鉴权失败后返回的HTTP错误码，默认为401
    private int unauthorizedErrorCode = HttpServletResponse.SC_UNAUTHORIZED;

    /**
     * 存放登录用户模型Key的Request Key
     */
    public static final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 如果打上了AuthToken注解则需要验证token
        if (method.getAnnotation(AuthToken.class) != null || handlerMethod.getBeanType().getAnnotation(AuthToken.class) != null) {

            String token = request.getHeader(httpHeader);
            String id = "";

            if (token != null && token.equals("null") && token.length() != 0) {
                id = String.valueOf(redisTemplate.opsForValue().get(token));
            }

            if (id != null && id.equals("null") && !id.trim().equals("")) {
                Long tokeBirthTime = Long.valueOf(String.valueOf(redisTemplate.opsForValue().get(token + id)));
                Long diff = System.currentTimeMillis() - tokeBirthTime;
                //token未过期则重置token过期时间
                if (diff > TokenUtil.TOKEN_RESET_TIME) {
//                    redisTemplate.opsForValue().set(id, token);
                    redisTemplate.expire(id, TokenUtil.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
//                    redisTemplate.opsForValue().set(token, id);
                    redisTemplate.expire(token, TokenUtil.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
                    redisTemplate.opsForValue().set(token + id, String.valueOf(System.currentTimeMillis()));
                }
                request.setAttribute(REQUEST_CURRENT_KEY, id);
                return true;
            } else {
                //token过期 or token不存在
                JSONObject jsonObject = new JSONObject();
                PrintWriter out = null;
                try {
                    response.setStatus(unauthorizedErrorCode);
                    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                    out = response.getWriter();
                    out.append(JSON.toJSONString(new Result(true, StatusCode.ACCESSERROR, "没有访问权限")));
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (null != out) {
                        out.flush();
                        out.close();
                    }
                }

            }

        }
        request.setAttribute(REQUEST_CURRENT_KEY, null);
        return true;
    }

}
