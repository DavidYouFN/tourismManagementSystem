package com.felix.project.commodity.controller;

import com.felix.project.commodity.model.Commodity;
import com.felix.project.commodity.model.SellerRelease;
import com.felix.project.commodity.model.Type;
import com.felix.project.commodity.service.CommodityService;
import com.felix.project.commodity.service.SellerReleaseService;
import com.felix.project.commodity.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName CommodityController
 * @Description TODO
 * @Author fangyong
 * @Date 2019/4/18 15:06
 **/
@RestController
@RequestMapping("/commodity")
@Api(tags = "商品API")
public class CommodityController {

    @Resource
    CommodityService commodityService;

    @Resource
    TypeService typeService;

    @Resource
    SellerReleaseService sellerReleaseService;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CommodityController.class);

    /**
     * @Author fangyong
     * @Description 商品添加
     * @Date 2019/4/18 15:11 
     * @Param 
     * @return 
     **/
    @ApiOperation(value = "商品添加" ,  notes="商品添加")
    @RequestMapping(value = "/addCommodity",method = {RequestMethod.POST, RequestMethod.GET})
    public String addCommodity(Commodity commodity){
        return commodityService.addCommodity(commodity);
    }

    /**
     * @Author fangyong
     * @Description 商品删除
     * @Date 2019/4/18 15:28
     * @Param
     * @return
     **/
    @ApiOperation(value = "商品删除" ,  notes="商品删除")
    @RequestMapping(value = "/delCommodity",method = {RequestMethod.POST, RequestMethod.GET})
    public String delCommodity(String commodityId){
        return commodityService.delCommodity(commodityId);
    }

    /**
     * @Author fangyong
     * @Description 商品修改
     * @Date 2019/4/18 15:31
     * @Param
     * @return
     **/
    @ApiOperation(value = "商品修改" ,  notes="商品修改")
    @RequestMapping(value = "/updateCommodity",method = {RequestMethod.POST, RequestMethod.GET})
    public String delCommodity(Commodity commodity){
        return commodityService.updateCommodity(commodity);
    }

    /**
     * @Author fangyong
     * @Description 商品查询
     * @Date 2019/4/18 15:38
     * @Param
     * @return
     **/
    @ApiOperation(value = "商品查询" ,  notes="商品查询")
    @RequestMapping(value = "/getCommodityInfo",method = {RequestMethod.POST, RequestMethod.GET})
    public String getCommodityInfo(String commodityId){
        return commodityService.getCommodityInfo(commodityId);
    }

    /**
     * @Author fangyong
     * @Description 添加服务类型
     * @Date 2019/4/18 15:45 
     * @Param 
     * @return 
     **/
    @ApiOperation(value = "添加服务类型" ,  notes="添加服务类型")
    @RequestMapping(value = "/addServiceType",method = {RequestMethod.POST, RequestMethod.GET})
    public String addServiceType(Type type){
        return typeService.addServiceType(type);
    }

    /**
     * @Author fangyong
     * @Description 删除服务类型
     * @Date 2019/4/18 15:58
     * @Param
     * @return
     **/
    @ApiOperation(value = "删除服务类型" ,  notes="删除服务类型")
    @RequestMapping(value = "/delServiceType",method = {RequestMethod.POST, RequestMethod.GET})
    public String delServiceType(int typeId){
        return typeService.delServiceType(typeId);
    }

    /**
     * @Author fangyong
     * @Description 修改服务类型
     * @Date 2019/4/18 16:00
     * @Param
     * @return
     **/
    @ApiOperation(value = "修改服务类型" ,  notes="修改服务类型")
    @RequestMapping(value = "/updateServiceType",method = {RequestMethod.POST, RequestMethod.GET})
    public String updateServiceType(Type type){
        return typeService.updateServiceType(type);
    }

    /**
     * @Author fangyong
     * @Description 统计服务类型
     * @Date 2019/4/18 16:00
     * @Param
     * @return
     **/
    @ApiOperation(value = "统计服务类型" ,  notes="统计服务类型")
    @RequestMapping(value = "/getAllServiceType",method = {RequestMethod.POST, RequestMethod.GET})
    public String getAllServiceType(){
        return typeService.getAllServiceType();
    }

    /**
     * @Author fangyong
     * @Description 添加商品库存
     * @Date 2019/4/21 13:38 
     * @Param 
     * @return 
     **/
    @ApiOperation(value = "添加商品库存" ,  notes="添加商品库存")
    @RequestMapping(value = "/addCommodityToStock",method = {RequestMethod.POST, RequestMethod.GET})
    public String addCommodityToStock(SellerRelease sellerRelease,String commodityId,String ImgURL){
        return sellerReleaseService.addCommodityToStock(sellerRelease,commodityId,ImgURL);
    }

    /**
     * @Author fangyong
     * @Description 删除商品库存
     * @Date 2019/4/21 13:38
     * @Param
     * @return
     **/
    @ApiOperation(value = "删除商品库存" ,  notes="删除商品库存")
    @RequestMapping(value = "/delCommodityToStock",method = {RequestMethod.POST, RequestMethod.GET})
    public String delCommodityToStock(String commodityId){
        return sellerReleaseService.delCommodityToStock(commodityId);
    }

    /**
     * @Author fangyong
     * @Description 更新商品库存
     * @Date 2019/4/21 13:38
     * @Param
     * @return
     **/
    @ApiOperation(value = "更新商品库存" ,  notes="更新商品库存")
    @RequestMapping(value = "/updateCommodityToStock",method = {RequestMethod.POST, RequestMethod.GET})
    public String updateCommodityToStock(SellerRelease sellerRelease){
        return sellerReleaseService.updateCommodityToStock(sellerRelease);
    }

    /**
     * @Author fangyong
     * @Description 查看商品库存
     * @Date 2019/4/21 13:38
     * @Param
     * @return
     **/
    @ApiOperation(value = "查看商品库存" ,  notes="查看商品库存")
    @RequestMapping(value = "/getCommodityToStock",method = {RequestMethod.POST, RequestMethod.GET})
    public String getCommodityToStock(String commodityId){
        return sellerReleaseService.getCommodityToStock(commodityId);
    }

    /**
     * @Author fangyong
     * @Description 查看所有库存商品
     * @Date 2019/4/21 13:49
     * @Param
     * @return
     **/
    @ApiOperation(value = "查看所有库存商品" ,  notes="查看所有库存商品")
    @RequestMapping(value = "/getAllCommodityToStock",method = {RequestMethod.POST, RequestMethod.GET})
    public String getAllCommodityToStock(){
        return sellerReleaseService.getAllCommodityToStock();
    }

    /**
     * @Author fangyong
     * @Description 根据类型查看商品
     * @Date 2019/4/25 15:28 
     * @Param 
     * @return 
     **/
    @ApiOperation(value = "根据类型查看商品" ,  notes="根据类型查看商品")
    @RequestMapping(value = "/getCommodityInfoByType",method = {RequestMethod.POST, RequestMethod.GET})
    public String getCommodityInfoByType(Integer typeId){
        return commodityService.getCommodityInfoByType(typeId);
    }
}
