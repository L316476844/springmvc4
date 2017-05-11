package org.jon.lv.utils;

import java.util.UUID;

/**
 * @Package org.jon.lv.utils.UUIDUtils
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/11 14:57
 * version V1.0.0
 */
public class UUIDUtils {
    /**
     * @Description: uuid 生成
     * @Title
     * @Author  lv bin
     * @Date 2016/10/27 13:38
     * @param
     * @return
     * @throws
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        return str.replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(getUUID());
    }
}
