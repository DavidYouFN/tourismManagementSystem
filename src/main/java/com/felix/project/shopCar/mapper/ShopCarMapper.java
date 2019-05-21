package com.felix.project.shopCar.mapper;

import com.felix.project.shopCar.model.ShopCar;

import java.util.List;

public interface ShopCarMapper {
    int deleteByPrimaryKey(String commodityId);

    int insert(ShopCar record);

    int insertSelective(ShopCar record);

    ShopCar selectByPrimaryKey(String commodityId);

    int updateByPrimaryKeySelective(ShopCar record);

    int updateByPrimaryKey(ShopCar record);

    List<ShopCar> getShopCarInfo();
}