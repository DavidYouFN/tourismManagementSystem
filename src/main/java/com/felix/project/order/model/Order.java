package com.felix.project.order.model;

import java.util.Date;

public class Order {
    private String orderId;

    private String orderMoney;

    private String orderDetailId;

    private String userId;

    private Date orderDate;

    private String orderState;

    private String orderEvaluateState;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId == null ? null : orderDetailId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getOrderEvaluateState(int i) {
        return orderEvaluateState;
    }

    public void setOrderEvaluateState(String orderEvaluateState) {
        this.orderEvaluateState = orderEvaluateState;
    }
}