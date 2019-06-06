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
import java.text.SimpleDateFormat;
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
        Order order1 = orderMapper.selectByPrimaryKey(orderId);
        String orderDeatilId = order1.getOrderDetailId();
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS, StaticProperties.RESPONSE_MESSAGE_SUCCESS,orderDeatilId);
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
        HashMap map = new HashMap();
        List<Order> orderList = orderMapper.getAllOrderByAdmin();
        for(Order order : orderList){
            HashMap map1 = new HashMap();
            String orderDetailId = order.getOrderDetailId();
            String userId =order.getUserId();
            User user = userMapper.selectByPrimaryKey(userId);
            OrderDetail orderDetail = orderDetailMapper.selectByPrimaryKey(orderDetailId);
            String commodityId = orderDetail.getCommodityId();
            Commodity commodity = commodityMapper.selectByPrimaryKey(commodityId);
            map1.put("orderId",order.getOrderId());
            map1.put("orderDate",order.getOrderDate());
            map1.put("orderState",order.getOrderState());
            map1.put("userName",user.getUsername());
            map1.put("commodityId",commodity.getCommodityId());
            map1.put("commodityDescribe",commodity.getCommodityDescribe());
            map1.put("commodityNumber",orderDetail.getCommodityNumber());
            map1.put("orderMoney",order.getOrderMoney());
            list.add(map1);
        }
        map.put("orderInfo",list);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,map);
    }

    @Override
    public String getOrderCount() {
        int count = orderMapper.getOrderCount();
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,count);
    }

    @Override
    public String getOrderCountOfToday() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String orderDate = dateFormat.format(date);
        int count = orderMapper.getOrderCountOfToday(orderDate);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,count);
    }

    @Override
    public String getOrderCountOfSevenDay(String item) {
        int count = orderMapper.getOrderCountOfToday(item);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,count);
    }

    @Override
    public String generateOrderDetail(OrderDetail orderDetail) {
        orderDetailMapper.insert(orderDetail);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,"");
    }
}
