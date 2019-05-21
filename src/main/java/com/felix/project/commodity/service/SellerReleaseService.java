package com.felix.project.commodity.service;

import com.felix.project.commodity.model.SellerRelease;

public interface SellerReleaseService {
    String addCommodityToStock(SellerRelease sellerRelease,String commodityId,String ImgURL);

    String delCommodityToStock(String commodityId);

    String updateCommodityToStock(SellerRelease sellerRelease);

    String getCommodityToStock(String commodityId);

    String getAllCommodityToStock();
}
