package com.felix.project.commonConfig.util;

import java.util.UUID;

/**
 * @ClassName UUIDUtils
 * @Description TODO
 * @Author fangyong
 * @Date 2019/4/13 21:04
 **/
public class UUIDUtils {
    public UUIDUtils() {
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
