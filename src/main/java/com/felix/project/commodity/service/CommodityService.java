package com.felix.project.commodity.service;

import com.felix.project.commodity.model.Commodity;
import com.felix.project.commodity.model.SellerRelease;

public interface CommodityService {
    String addCommodity(Commodity commodity);

    String delCommodity(String commodityId);

    String updateCommodity(Commodity commodity);

    String getCommodityInfo(String commodityId);

    String getCommodityInfoByType(Integer typeId);

    String getCommodityInfoByName(String commodityName);

    String getImg();

    String getCommodityDetail(String commodityId);
}
