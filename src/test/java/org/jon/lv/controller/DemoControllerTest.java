package org.jon.lv.controller;

import com.alibaba.fastjson.JSON;
import org.jon.lv.JunitBaseMockMvcTest;
import org.jon.lv.dto.TestDTO;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @Package DemoControllerTest
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/10 13:06
 * version V1.0.0
 */
public class DemoControllerTest extends JunitBaseMockMvcTest {

    @Test
    public void testDemo() throws Exception {

        TestDTO dto = new TestDTO();
        dto.setId(1L);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/demo/unNeed/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
