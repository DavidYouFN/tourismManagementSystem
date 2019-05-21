package com.felix.project.user.service.impl;

import com.felix.project.commonConfig.util.JsonUtil;
import com.felix.project.commonConfig.util.StaticProperties;
import com.felix.project.user.mapper.AdminMapper;
import com.felix.project.user.model.Admin;
import com.felix.project.user.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName AdminServiceImpl
 * @Description TODO
 * @Author fangyong
 * @Date 2019/5/13 22:04
 **/
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    AdminMapper adminMapper;

    @Override
    public String getAdminInfo() {
        return null;
    }

    @Override
    public String adminLogin(String adminName, String adminPassword) {
        Admin admin = adminMapper.adminLogin(adminName, adminPassword);
        if (admin == null) {
            return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS, StaticProperties.RESPONSE_MESSAGE_SUCCESS, "");
        }
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_FAIL,StaticProperties.RESPONSE_MESSAGE_FAIL,"");
    }
}
