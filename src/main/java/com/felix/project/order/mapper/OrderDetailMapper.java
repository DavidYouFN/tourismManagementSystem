package com.felix.project.order.mapper;

import com.felix.project.order.model.OrderDetail;

public interface OrderDetailMapper {
    int deleteByPrimaryKey(String orderDetailId);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(String orderDetailId);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);
}