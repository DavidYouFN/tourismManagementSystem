package com.felix.project.commodity.mapper;

import com.felix.project.commodity.model.SellerRelease;

import java.util.List;

public interface SellerReleaseMapper {
    int deleteByPrimaryKey(String commodityId);

    int insert(SellerRelease record);

    int insertSelective(SellerRelease record);

    SellerRelease selectByPrimaryKey(String commodityId);

    int updateByPrimaryKeySelective(SellerRelease record);

    int updateByPrimaryKey(SellerRelease record);

    List<SellerRelease> getAllCommodityToStock();
}