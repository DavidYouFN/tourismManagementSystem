package com.felix.project.order.service;

import com.alipay.api.AlipayApiException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface OrderService {
    String getAllOrder(String userId);

    String getNoTripOrder(String userId);

    String getObligationsOrder(String userId);

    String getToBeEvaluatedOrder(String userId);

    String pay(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String property) throws IOException, AlipayApiException;

    String generateOrder(String userId,String money);

    String evaluatedOrder(String orderId);

    String getAllOrderByAdmin();

    String getOrderCount();

    String getOrderCountOfToday();

    String getOrderCountOfSevenDay(String item);
}
