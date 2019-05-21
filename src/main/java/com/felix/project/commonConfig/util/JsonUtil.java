package com.felix.project.commonConfig.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName JsonUtil
 * @Description TODO
 * @Author fangyong
 * @Date 2019/2/2 14:42
 **/
public class JsonUtil {

    public String JsonInfo(Integer state, String msg, Object data){
        Map<String,Object> map = new HashMap<>();
        map.put("state",state);
        map.put("msg",msg);
        map.put("data",data);
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }
}
