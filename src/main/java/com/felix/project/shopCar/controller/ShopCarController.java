package com.felix.project.shopCar.controller;

import com.felix.project.shopCar.model.ShopCar;
import com.felix.project.shopCar.service.ShopCarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName ShopCarController
 * @Description TODO
 * @Author fangyong
 * @Date 2019/4/20 14:59
 **/
@RestController
@RequestMapping("/shopCar")
@Api(tags = "购物车API")
public class ShopCarController {

    @Resource
    ShopCarService shopCarService;

    /**
     * @Author fangyong
     * @Description 添加购物车
     * @Date 2019/4/20 15:03
     * @Param
     * @return
     **/
    @ApiOperation(value = "添加购物车" ,  notes="添加购物车")
    @RequestMapping(value = "/addShopCar",method = {RequestMethod.POST, RequestMethod.GET})
    public String addShopCar(ShopCar shopCar){
        return shopCarService.addShopCar(shopCar);
    }

    /**
     * @Author fangyong
     * @Description 删除购物车
     * @Date 2019/4/20 15:50
     * @Param
     * @return
     **/
    @ApiOperation(value = "删除购物车" ,  notes="删除购物车")
    @RequestMapping(value = "/delShopCar",method = {RequestMethod.POST, RequestMethod.GET})
    public String delShopCar(String commodityId){
        return shopCarService.delShopCar(commodityId);
    }

    /**
     * @Author fangyong
     * @Description 更新购物车
     * @Date 2019/4/20 15:54
     * @Param
     * @return
     **/
    @ApiOperation(value = "更新购物车" ,  notes="更新购物车")
    @RequestMapping(value = "/updateShopCar",method = {RequestMethod.POST, RequestMethod.GET})
    public String updateShopCar(ShopCar shopCar){
        return shopCarService.updateShopCar(shopCar);
    }

    /**
     * @Author fangyong
     * @Description 查看购物车
     * @Date 2019/4/20 15:56
     * @Param
     * @return
     **/
    @ApiOperation(value = "查看购物车信息" ,  notes="查看购物车信息")
    @RequestMapping(value = "/getShopCarInfo",method = {RequestMethod.POST, RequestMethod.GET})
    public String getShopCarInfo(){
        return shopCarService.getShopCarInfo();
    }


}
