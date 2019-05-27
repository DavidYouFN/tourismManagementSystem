package com.felix.project.commodity.service.impl;

import com.felix.project.commodity.mapper.CommodityMapper;
import com.felix.project.commodity.mapper.PictureMapper;
import com.felix.project.commodity.model.Commodity;
import com.felix.project.commodity.model.Picture;
import com.felix.project.commodity.model.SellerRelease;
import com.felix.project.commodity.service.CommodityService;
import com.felix.project.commonConfig.util.JsonUtil;
import com.felix.project.commonConfig.util.StaticProperties;
import com.felix.project.commonConfig.util.UUIDUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CommodityServiceImpl
 * @Description TODO
 * @Author fangyong
 * @Date 2019/4/18 15:09
 **/
@Service
public class CommodityServiceImpl implements CommodityService {

    @Resource
    CommodityMapper commodityMapper;

    @Resource
    PictureMapper pictureMapper;


    @Override
    public String addCommodity(Commodity commodity) {
        commodity.setCommodityId(UUIDUtils.getUUID());
        commodityMapper.insert(commodity);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,"");
    }

    @Override
    public String delCommodity(String commodityId) {
        commodityMapper.deleteByPrimaryKey(commodityId);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,"");
    }

    @Override
    public String updateCommodity(Commodity commodity) {
        commodityMapper.updateByPrimaryKey(commodity);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,"");
    }

    @Override
    public String getCommodityInfo(String commodityId) {
        Commodity commodity = commodityMapper.selectByPrimaryKey(commodityId);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,commodity);

    }

    @Override
    public String getCommodityInfoByType(Integer typeId) {
        return null;
    }

    @Override
    public String getCommodityInfoByName(String commodityName) {
        List<Commodity> commodityList = commodityMapper.getCommodityInfoByName(commodityName);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,commodityList);
    }

    @Override
    public String getImg() {
        List<Picture> pictureList = pictureMapper.getImg();
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,pictureList);
    }

}
