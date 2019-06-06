package com.felix.project.order.controller;

import com.alipay.api.AlipayApiException;
import com.felix.project.commonConfig.util.StaticProperties;
import com.felix.project.order.model.OrderDetail;
import com.felix.project.order.service.OrderService;
import com.felix.project.user.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author fangyong
 * @Date 2019/4/20 16:24
 **/
@RestController
@RequestMapping("/order")
@Api(tags = "订单API")
public class OrderController {

    @Resource
    OrderService orderService;

    /**
     * @return
     * @Author fangyong
     * @Description 查看全部订单
     * @Date 2019/4/20 16:26
     * @Param
     **/
    @ApiOperation(value = "查看全部订单", notes = "查看全部订单")
    @RequestMapping(value = "/getAllOrder", method = {RequestMethod.POST, RequestMethod.GET})
    public String getAllOrder(String userId) {
        return orderService.getAllOrder(userId);
    }

    /**
     * @return
     * @Author fangyong
     * @Description 查看未出行订单
     * @Date 2019/4/20 16:42
     * @Param
     **/
    @ApiOperation(value = "查看未出行订单", notes = "查看未出行订单")
    @RequestMapping(value = "/getNoTripOrder", method = {RequestMethod.POST, RequestMethod.GET})
    public String getNoTripOrder(String userId) {
        return orderService.getNoTripOrder(userId);
    }

    /**
     * @return
     * @Author fangyong
     * @Description 查看待付款订单
     * @Date 2019/4/20 16:42
     * @Param
     **/
    @ApiOperation(value = "查看待付款订单", notes = "查看待付款订单")
    @RequestMapping(value = "/getObligationsOrder", method = {RequestMethod.POST, RequestMethod.GET})
    public String getObligationsOrder(String userId) {
        return orderService.getObligationsOrder(userId);
    }

    /**
     * @return
     * @Author fangyong
     * @Description 查看待评价订单
     * @Date 2019/4/20 16:42
     * @Param
     **/
    @ApiOperation(value = "查看待评价订单", notes = "查看待评价订单")
    @RequestMapping(value = "/getToBeEvaluatedOrder", method = {RequestMethod.POST, RequestMethod.GET})
    public String getToBeEvaluatedOrder(String userId) {
        return orderService.getToBeEvaluatedOrder(userId);
    }

    /**
     * @return
     * @Author fangyong
     * @Description 通过支付宝接口支付订单
     * @Date 2019/4/25 13:24
     * @Param
     **/
    @ApiOperation(value = "支付", notes = "支付")
    @RequestMapping(value = "/pay", method = {RequestMethod.POST, RequestMethod.GET})
    public String alipayToOrder(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
                                @RequestParam(value = "property", required = false) String property) throws AlipayApiException, IOException {
        return orderService.pay(httpRequest, httpResponse, property);
    }

    /**
     * @return
     * @Author fangyong
     * @Description 支付成功后生成订单, 用户余额相应减少
     * @Date 2019/4/25 13:52
     * @Param
     **/
    @ApiOperation(value = "生成订单", notes = "生成订单")
    @RequestMapping(value = "/generateOrder", method = {RequestMethod.POST, RequestMethod.GET})
    public String generateOrder(HttpServletRequest request, String money) {
        HttpSession session = request.getSession();
        User userInfo = (User) session.getAttribute(StaticProperties.SESSION_USER);
        String userId = userInfo.getId();
        return orderService.generateOrder(userId, money);
    }

    /**
     * @Author fangyong
     * @Description 生成订单详情
     * @Date 2019/5/29 10:16
     * @Param
     * @return
     **/
    @ApiOperation(value = "生成订单详情", notes = "生成订单详情")
    @RequestMapping(value = "/generateOrderDetail", method = {RequestMethod.POST, RequestMethod.GET})
    public String generateOrderDetail(OrderDetail orderDetail){
        return orderService.generateOrderDetail(orderDetail);
    }

    /**
     * @return
     * @Author fangyong
     * @Description 评价订单，修改订单评价状态为1,初试默认为0，表示未评价
     * @Date 2019/4/25 14:39
     * @Param
     **/
    @ApiOperation(value = "评价订单", notes = "评价订单")
    @RequestMapping(value = "/evaluatedOrder", method = {RequestMethod.POST, RequestMethod.GET})
    public String evaluatedOrder(String orderId) {
        return orderService.evaluatedOrder(orderId);
    }

    /**
     * @return
     * @Author fangyong
     * @Description 管理员获取全部订单
     * @Date 2019/5/19 19:54
     * @Param
     **/
    @ApiOperation(value = "管理员获取全部订单", notes = "管理员获取全部订单")
    @RequestMapping(value = "/getAllOrderByAdmin", method = {RequestMethod.POST, RequestMethod.GET})
    public String getAllOrderByAdmin() {
        return orderService.getAllOrderByAdmin();
    }
    
    /**
     * @Author fangyong
     * @Description 获取订单数量
     * @Date 2019/5/25 23:51 
     * @Param 
     * @return 
     **/
    @ApiOperation(value = "获取订单数量" ,  notes="获取订单数量")
    @RequestMapping(value = "/getOrderCount",method = {RequestMethod.POST, RequestMethod.GET})
    public String getOrderCount(){
        return orderService.getOrderCount();
    }

    /**
     * @Author fangyong
     * @Description 获取当日新增订单数量
     * @Date 2019/5/26 1:37
     * @Param
     * @return
     **/
    @ApiOperation(value = "获取当日新增订单数量" ,  notes="获取当日新增订单数量")
    @RequestMapping(value = "/getOrderCountOfToday",method = {RequestMethod.POST, RequestMethod.GET})
    public String getOrderCountOfToday(){
        return orderService.getOrderCountOfToday();
    }
    
    /**
     * @Author fangyong
     * @Description 获取一星期的订单数量
     * @Date 2019/5/26 12:35 
     * @Param 
     * @return 
     **/
    @ApiOperation(value = "获取一星期的订单数量" ,  notes="获取一星期的订单数量")
    @RequestMapping(value = "/getOrderCountOfDate",method = {RequestMethod.POST, RequestMethod.GET})
    public String getOrderCountOfSevenDay(String item){
        return orderService.getOrderCountOfSevenDay(item);
    }
}
