package com.felix.project.commodity.service.impl;

import com.felix.project.commodity.mapper.TypeMapper;
import com.felix.project.commodity.model.Type;
import com.felix.project.commodity.service.TypeService;
import com.felix.project.commonConfig.util.JsonUtil;
import com.felix.project.commonConfig.util.StaticProperties;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName TypeServiceImpl
 * @Description TODO
 * @Author fangyong
 * @Date 2019/4/18 15:54
 **/
@Service
public class TypeServiceImpl implements TypeService {

    @Resource
    TypeMapper typeMapper;

    @Override
    public String addServiceType(Type type) {
        typeMapper.insert(type);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,"");
    }

    @Override
    public String delServiceType(int typeId) {
        typeMapper.deleteByPrimaryKey(typeId);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,"");
    }

    @Override
    public String updateServiceType(Type type) {
        typeMapper.updateByPrimaryKey(type);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,"");
    }

    @Override
    public String getAllServiceType() {
        List<Type> typeList = typeMapper.getAllServiceType();
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,typeList);
    }
}
