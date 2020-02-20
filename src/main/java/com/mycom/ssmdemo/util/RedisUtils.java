package com.mycom.ssmdemo.util;

import com.mycom.ssmdemo.common.configuration.RedisConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-12 下午 09:57
 * @description：redis测试
 * @modified By：
 * @version: $
 */
@Component
public class RedisUtils {

    protected static Logger logger = LoggerFactory.getLogger(RedisUtils.class);
    /*
    private RedisUtils(){

    }
   */
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public boolean set(String key, Object value){
        boolean b = false;
        try {
            redisTemplate.opsForValue().set(key,value);
            b = true;
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return b;
    }

    public Object get(String key){
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    public boolean setExpire(String key, long time){
        if (time > 0){
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
        return true;
    }

    public boolean hasKey(String key){
        boolean hasKey = false;
        if (redisTemplate != null){
            try {
                hasKey = redisTemplate.hasKey(key);
            }catch (Exception e){
            }
        }
        return hasKey;
    }
}
