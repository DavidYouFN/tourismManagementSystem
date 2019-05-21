package com.felix.project.user.service.impl;

import com.felix.project.commonConfig.util.JsonUtil;
import com.felix.project.commonConfig.util.MD5Util;
import com.felix.project.commonConfig.util.StaticProperties;
import com.felix.project.commonConfig.util.UUIDUtils;
import com.felix.project.user.mapper.SellerMapper;
import com.felix.project.user.model.Seller;
import com.felix.project.user.service.SellerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SellerServiceImpl
 * @Description TODO
 * @Author fangyong
 * @Date 2019/4/14 18:36
 **/
@Service
public class SellerServiceImpl implements SellerService {

    @Resource
    SellerMapper sellerMapper;


    @Override
    public String getSellerList() {
        List<Seller> sellerList = sellerMapper.getSellerList();
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,sellerList);
    }

    @Override
    public String delSeller(String sellerId) {
        sellerMapper.deleteByPrimaryKey(sellerId);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,"");
    }

    @Override
    public String modifySeller(Seller seller) {
        sellerMapper.updateByPrimaryKey(seller);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,"");
    }

}
