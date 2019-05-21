package com.felix.project.user.service.impl;

import com.felix.project.user.model.Shop;
import com.felix.project.commonConfig.util.*;
import com.felix.project.user.mapper.SellerMapper;
import com.felix.project.user.mapper.ShopMapper;
import com.felix.project.user.model.Seller;
import com.felix.project.user.model.User;
import com.felix.project.user.mapper.UserMapper;
import com.felix.project.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author fangyong
 * @Date 2019/4/11 17:23
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    ShopMapper shopMapper;

    @Resource
    SellerMapper sellerMapper;

    @Override
    public String userRegister(User user) {
        User userPO = userMapper.getUserByUsername(user.getUsername());
        if (userPO != null){
            return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_FAIL,StaticProperties.RESPONSE_MESSAGE_FAIL,"");
        }
        user.setId(UUIDUtils.getUUID());
        user.setPassword(MD5Util.makeMD5(user.getPassword()));
        user.setBirth(new Date());
        userMapper.insert(user);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,"");
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public String modifyPassword(String username, String password) {
        userMapper.modifyPassword(username,MD5Util.makeMD5(password));
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,"");
    }

    @Override
    public String addShop(Shop shop, Seller seller) {
        Seller sellerPO = new Seller();
        sellerPO.setSellerId(UUIDUtils.getUUID());
        sellerPO.setAssets(seller.getAssets());
        sellerPO.setEmail(seller.getEmail());
        sellerPO.setPhone(seller.getPhone());
        sellerPO.setSellerName(seller.getSellerName());
        sellerPO.setShopName(seller.getShopName());
        sellerPO.setShopArea(seller.getShopArea());
        sellerPO.setServiceType(seller.getServiceType());
        shopMapper.insert(shop);
        sellerMapper.insert(sellerPO);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,"");
    }

    @Override
    public String getUserList() {
        List<User> userList = userMapper.getUserList();
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,userList);
    }

}
