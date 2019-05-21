package com.felix.project.shopCar.service.impl;

import com.felix.project.commonConfig.util.JsonUtil;
import com.felix.project.commonConfig.util.StaticProperties;
import com.felix.project.shopCar.mapper.ShopCarMapper;
import com.felix.project.shopCar.model.ShopCar;
import com.felix.project.shopCar.service.ShopCarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ShopCarServiceImpl
 * @Description TODO
 * @Author fangyong
 * @Date 2019/4/20 15:01
 **/
@Service
public class ShopCarServiceImpl implements ShopCarService {

    @Resource
    ShopCarMapper shopCarMapper;


    @Override
    public String addShopCar(ShopCar shopCar) {
        shopCarMapper.insert(shopCar);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,"");
    }

    @Override
    public String delShopCar(String commodityId) {
        shopCarMapper.deleteByPrimaryKey(commodityId);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,"");
    }

    @Override
    public String updateShopCar(ShopCar shopCar) {
        shopCarMapper.updateByPrimaryKey(shopCar);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,"");
    }

    @Override
    public String getShopCarInfo() {
        List<ShopCar> shopCarList = shopCarMapper.getShopCarInfo();
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,shopCarList);
    }
}
