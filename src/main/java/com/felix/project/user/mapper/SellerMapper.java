package com.felix.project.user.mapper;

import com.felix.project.user.model.Seller;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SellerMapper {
    int deleteByPrimaryKey(String sellerId);

    int insert(Seller record);

    int insertSelective(Seller record);

    Seller selectByPrimaryKey(String sellerId);

    int updateByPrimaryKeySelective(Seller record);

    int updateByPrimaryKey(Seller record);

    List<Seller> getSellerList();

    int getSellerCount();
}