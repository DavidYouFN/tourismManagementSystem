package com.felix.project.shopCar.service;

import com.felix.project.shopCar.model.ShopCar;

public interface ShopCarService {
    String addShopCar(ShopCar shopCar);

    String delShopCar(String commodityId);

    String updateShopCar(ShopCar shopCar);

    String getShopCarInfo();
}
