package org.jon.lv.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class Initialization {

    @Autowired
    private RedisTemplate redisTemplate; // redis集群

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        RedisUtil.setRedisTemplate(redisTemplate);
    }
}
