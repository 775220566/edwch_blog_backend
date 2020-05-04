package com.per.blog.util;

import org.springframework.util.DigestUtils;

/**
 * Created by Administrator on 2020/2/8 0008.
 */
public class TokenUtil {
    /**
     * 设置删除标志为真
     */
    public static final Integer DEL_FLAG_TRUE = 1;

    /**
     * 设置删除标志为假
     */
    public static final Integer DEL_FLAG_FALSE = 0;

    /**
     * redis存储token设置的过期时间
     */
    public static final Long TOKEN_EXPIRE_TIME = Long.valueOf(60 * 2);

    /**
     * 设置可以重置token过期时间的时间界限
     */
    public static final Long TOKEN_RESET_TIME = Long.valueOf(1000 * 100);

    public static String tokenGenerate(String... strings) {
        long   timestamp = System.currentTimeMillis();
        String tokenMeta = "";
        for (String s : strings) {
            tokenMeta = tokenMeta + s;
        }
        tokenMeta = tokenMeta + timestamp;
        String token = DigestUtils.md5DigestAsHex(tokenMeta.getBytes());
        return token;
    }
}
