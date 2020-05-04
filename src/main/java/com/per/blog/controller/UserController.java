package com.per.blog.controller;

import com.per.blog.annotation.AuthToken;
import com.per.blog.common.Result;
import com.per.blog.common.StatusCode;
import com.per.blog.pojo.User;
import com.per.blog.service.UserService;
import com.per.blog.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2020/2/8 0008.
 */
@CrossOrigin
@RestController
@RequestMapping("/blog/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping(value="/login",method= RequestMethod.POST)
    public Result login(@RequestBody User user) {
        user = userService.login(user.getUserName(), DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        if (user != null) {
            String id = user.getId();
            String password = user.getPassword();
            String token = TokenUtil.tokenGenerate(id, password);
            redisTemplate.opsForValue().set(id, token);
            redisTemplate.expire(id, TokenUtil.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
            redisTemplate.opsForValue().set(token, id);
            redisTemplate.expire(token, TokenUtil.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
            Long currentTime = System.currentTimeMillis();
            redisTemplate.opsForValue().set(token + id, currentTime.toString());
            Map map = new HashMap();
            map.put("token",token);
            return new Result(true, StatusCode.OK,"登录成功", map);
        } else {
            return new Result(true, StatusCode.LOGINERROR,"登录失败");
        }
    }

    @RequestMapping(value="/register",method= RequestMethod.POST)
    public Result register(@RequestBody User user) {
        //md5加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        return new Result(true, StatusCode.OK,"注册成功", userService.addUser(user));
    }


    @RequestMapping(value="/userInfo/{id}",method= RequestMethod.GET)
    public Result userInfoById(@PathVariable String id) {
        return new Result(true, StatusCode.OK,"查询成功", userService.findUserById(id));
    }

    @RequestMapping(value="/userInfo",method= RequestMethod.GET)
    public Result userInfo(@RequestHeader("Auth-token") String token) {
        String id = redisTemplate.opsForValue().get(token).toString();
        return new Result(true, StatusCode.OK,"查询成功", userService.findUserById(id));
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    @AuthToken
    public Result test() {
        List<User> users = userService.findAll();
        return new Result(true,StatusCode.OK,"ok",users);
    }
}
