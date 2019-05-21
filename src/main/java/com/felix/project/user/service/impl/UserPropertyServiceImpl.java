package com.felix.project.user.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.felix.project.commonConfig.util.AlipayUtil;
import com.felix.project.commonConfig.util.JsonUtil;
import com.felix.project.commonConfig.util.StaticProperties;
import com.felix.project.user.mapper.UserMapper;
import com.felix.project.user.mapper.UserPropertyMapper;
import com.felix.project.user.model.User;
import com.felix.project.user.model.UserProperty;
import com.felix.project.user.service.UserPropertyService;
import com.felix.project.user.util.GetUUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.alipay.api.AlipayConstants.CHARSET_UTF8;

/**
 * @ClassName UserPropertyServiceImpl
 * @Description TODO
 * @Author fangyong
 * @Date 2019/4/21 14:56
 **/
@Service
public class UserPropertyServiceImpl implements UserPropertyService {

    @Resource
    UserPropertyMapper userPropertyMapper;

    @Resource
    UserMapper userMapper;




    @Override
    @Transactional
    public String addProperty(HttpServletRequest httpRequest, HttpServletResponse httpResponse,String userId, String property) throws IOException, AlipayApiException {
        AlipayUtil.aliPay(httpRequest,httpResponse,property);
        userPropertyMapper.addProperty(userId,property);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,"");
    }

    @Override
    public String getWalletInfo(String userId) {
        UserProperty userProperty = userPropertyMapper.selectByPrimaryKey(userId);
        String property = userProperty.getProperty();
        User userPO = userMapper.selectByPrimaryKey(userId);
        User user = new User();
        user.setUsername(userPO.getUsername());
        user.setGender(userPO.getGender());
        user.setId(userPO.getId());
        user.setPhone(userPO.getPhone());
        List list = new ArrayList();
        HashMap map = new HashMap();
        map.put("userData",user);
        map.put("propertyData",property);
        list.add(map);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,map);
    }
}
