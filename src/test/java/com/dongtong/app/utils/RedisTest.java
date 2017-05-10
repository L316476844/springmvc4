package com.dongtong.app.utils;

import com.dongtong.app.JunitBaseMockMvcTest;
import org.jon.lv.redis.RedisUtil;
import org.junit.Test;

/**
 * @Description: RedisTest
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/9 16:09
 * version V1.0.0
 */
public class RedisTest extends JunitBaseMockMvcTest {

    @Test
    public void testSetRedis(){
        RedisUtil.set("customerapp_test_key", "测试redis", 5000L);
    }

    @Test
    public void testGetRedis(){
        System.out.println("------------------------------------" + RedisUtil.get("customerapp_test_key"));
    }
}
