package com.felix.project.order.service.impl;

import com.alipay.api.AlipayApiException;
import com.felix.project.commodity.mapper.CommodityMapper;
import com.felix.project.commodity.model.Commodity;
import com.felix.project.commonConfig.util.AlipayUtil;
import com.felix.project.commonConfig.util.JsonUtil;
import com.felix.project.commonConfig.util.StaticProperties;
import com.felix.project.commonConfig.util.UUIDUtils;
import com.felix.project.order.mapper.OrderDetailMapper;
import com.felix.project.order.mapper.OrderMapper;
import com.felix.project.order.model.Order;
import com.felix.project.order.model.OrderDetail;
import com.felix.project.order.service.OrderService;
import com.felix.project.user.mapper.UserMapper;
import com.felix.project.user.mapper.UserPropertyMapper;
import com.felix.project.user.model.User;
import com.felix.project.user.model.UserProperty;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Author fangyong
 * @Date 2019/4/20 16:25
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderMapper orderMapper;

    @Resource
    OrderDetailMapper orderDetailMapper;

    @Resource
    UserPropertyMapper userPropertyMapper;

    @Resource
    CommodityMapper commodityMapper;

    @Resource
    UserMapper userMapper;

    @Override
    public String getAllOrder(String userId) {
        List<Order> orderList = orderMapper.getAllOrder(userId);
        System.out.println(orderList);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS, StaticProperties.RESPONSE_MESSAGE_SUCCESS, orderList);
    }

    @Override
    public String getNoTripOrder(String userId) {
        List<Order> orderList = orderMapper.getNoTripOrder(userId);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS, StaticProperties.RESPONSE_MESSAGE_SUCCESS, orderList);
    }

    @Override
    public String getObligationsOrder(String userId) {
        List<Order> orderList = orderMapper.getObligationsOrder(userId);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS, StaticProperties.RESPONSE_MESSAGE_SUCCESS, orderList);
    }

    @Override
    public String getToBeEvaluatedOrder(String userId) {
        List<Order> orderList = orderMapper.getToBeEvaluatedOrder(userId);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS, StaticProperties.RESPONSE_MESSAGE_SUCCESS, orderList);
    }

    @Override
    public String pay(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String property) throws IOException, AlipayApiException {
        AlipayUtil.aliPay(httpRequest, httpResponse, property);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS, StaticProperties.RESPONSE_MESSAGE_SUCCESS, "");
    }

    @Override
    public String generateOrder(String userId, String money) {
        UserProperty userProperty = userPropertyMapper.selectByPrimaryKey(userId);
        String propertyPO = userProperty.getProperty();
        int a = Integer.parseInt(propertyPO);
        int b = Integer.parseInt(money);
        String property = String.valueOf(a - b);
        userPropertyMapper.updateProperty(userId, property);
        String orderId = UUIDUtils.getUUID();
        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderDate(new Date());
        order.setOrderDetailId(UUIDUtils.getUUID());
        order.setUserId(userId);
        order.setOrderMoney(money);
        order.setOrderState("0");
        order.setOrderEvaluateState("0");
        orderMapper.insert(order);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS, StaticProperties.RESPONSE_MESSAGE_SUCCESS, "");
    }

    @Override
    public String evaluatedOrder(String orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        orderMapper.setOrderEvalutedState(order);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS, StaticProperties.RESPONSE_MESSAGE_SUCCESS, "");
    }

    @Override
    public String getAllOrderByAdmin() {
        List list = new ArrayList();
        List<Order> orderList = orderMapper.getAllOrderByAdmin();
        HashMap map = new HashMap();
        for(Order order : orderList){
            String orderDetailId = order.getOrderDetailId();
            String userId =order.getUserId();
            User user = userMapper.selectByPrimaryKey(userId);
            OrderDetail orderDetail = orderDetailMapper.selectByPrimaryKey(orderDetailId);
            String commodityId = orderDetail.getCommodityId();
            Commodity commodity = commodityMapper.selectByPrimaryKey(commodityId);
            map.put("orderId",order.getOrderId());
            map.put("orderDate",order.getOrderDate());
            map.put("orderState",order.getOrderState());
            map.put("userName",user.getUsername());
            map.put("commodityId",commodity.getCommodityId());
            map.put("commodityDescribe",commodity.getCommodityDescribe());
            map.put("commodityNumber",orderDetail.getCommodityNumber());
            map.put("orderMoney",order.getOrderMoney());
        }
        list.add(map);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,list);
    }
}
