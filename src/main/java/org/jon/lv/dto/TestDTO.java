package org.jon.lv.dto;

import java.io.Serializable;

/**
 * @Package org.jon.lv.dto.TestDTO
 * @Description: TestDTO
 * Author lv bin
 * @date 2017/5/10 17:56
 * version V1.0.0
 */
public class TestDTO implements Serializable{
    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 内容
     */
    private String content;

    /**
     * 获取属性 id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 设置属性 id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取属性 名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置属性 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取属性 内容
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 设置属性 内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}
