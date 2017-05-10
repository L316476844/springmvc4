package org.jon.lv.redis;


import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class RedisUtil {
    private static RedisTemplate redisTemplate; // redis集群

    /**
     * 写入缓存
     * @param key
     * @param obj
     */
    public static void set(String key, Object obj) {
        redisTemplate.opsForValue().set(key, obj);
    }


    /**
     * 写入缓存
     * @param key
     * @param obj
     * @param expireSeconds 秒
     */
    public static void set(String key, Object obj, long expireSeconds) {
        redisTemplate.opsForValue().set(key, obj, expireSeconds, TimeUnit.SECONDS);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(final String key, Class<T> clazz) {
        return (T) redisTemplate.boundValueOps(key).get();
    }

    /**
     * 读取缓存
     * @param key
     * @return
     */
    public static Object get(final String key){
        return redisTemplate.boundValueOps(key).get();
    }

    /**
     * 删除，根据key精确匹配
     *
     * @param key
     */
    public static void del(final String... key) {
        redisTemplate.delete(Arrays.asList(key));
    }

    /**
     * 批量删除，根据key模糊匹配
     *
     * @param pattern
     */
    public static void delpn(final String... pattern) {
        for (String kp : pattern) {
            redisTemplate.delete(redisTemplate.keys(kp + "*"));
        }
    }

    /**
     * key是否存在
     *
     * @param key
     */
    public static boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    public static void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }
}
