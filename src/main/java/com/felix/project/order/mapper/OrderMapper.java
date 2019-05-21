package com.felix.project.order.mapper;

import com.felix.project.order.model.Order;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> getAllOrder(String userId);

    List<Order> getNoTripOrder(String userId);

    List<Order> getObligationsOrder(String userId);

    List<Order> getToBeEvaluatedOrder(String userId);

    int setOrderEvalutedState(Order order);

    List<Order> getAllOrderByAdmin();
}