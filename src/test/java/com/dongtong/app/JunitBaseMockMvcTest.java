package com.dongtong.app;

import org.jon.lv.redis.RedisUtil;
import org.jon.lv.utils.UUIDUtils;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ContextConfiguration("classpath*:config/*.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class JunitBaseMockMvcTest {

    public MockMvc mockMvc;

    public String tokenKey = "";
    public String DEFAULT_TOKEN_NAME = "X-token";
    public String VERSION = "v1.0.0";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        tokenKey = "UNIT_TEST_" + UUIDUtils.getUUID();

        Long currentUserId = 12L;

        RedisUtil.set(tokenKey, currentUserId, 1800);
    }
}
