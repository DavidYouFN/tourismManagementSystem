package com.felix.project.commodity.service.impl;

import com.felix.project.commodity.mapper.PictureMapper;
import com.felix.project.commodity.mapper.SellerReleaseMapper;
import com.felix.project.commodity.model.Picture;
import com.felix.project.commodity.model.SellerRelease;
import com.felix.project.commodity.service.SellerReleaseService;
import com.felix.project.commonConfig.util.JsonUtil;
import com.felix.project.commonConfig.util.StaticProperties;
import com.felix.project.commonConfig.util.UUIDUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SellerReleaseImpl
 * @Description TODO
 * @Author fangyong
 * @Date 2019/4/21 13:42
 **/
@Service
public class SellerReleaseImpl implements SellerReleaseService {

    @Resource
    SellerReleaseMapper sellerReleaseMapper;

    @Resource
    PictureMapper pictureMapper;

    @Override
    public String addCommodityToStock(SellerRelease sellerRelease,String commodityId,String ImgURL) {
        String commodity1Id = UUIDUtils.getUUID();
        sellerRelease.setCommodityId(commodity1Id);
        sellerRelease.setState("0"); //0表示审核转态为未审核
        commodityId = commodity1Id;
        Picture picture = new Picture();
        picture.setCommodityId(commodityId);
        picture.setCommodityPicUrl(ImgURL);
        sellerReleaseMapper.insert(sellerRelease);
        pictureMapper.insert(picture);
        SellerRelease sellerRelease1 = sellerReleaseMapper.selectByPrimaryKey(commodity1Id);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,sellerRelease1);
    }

    @Override
    public String delCommodityToStock(String commodityId) {
        sellerReleaseMapper.deleteByPrimaryKey(commodityId);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,"");
    }

    @Override
    public String updateCommodityToStock(SellerRelease sellerRelease) {
        sellerRelease.setState("0");
        sellerReleaseMapper.updateByPrimaryKey(sellerRelease);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,"");
    }

    @Override
    public String getCommodityToStock(String commodityId) {
        SellerRelease sellerRelease = sellerReleaseMapper.selectByPrimaryKey(commodityId);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,sellerRelease);
    }

    @Override
    public String getAllCommodityToStock() {
        List<SellerRelease> sellerReleaseList = sellerReleaseMapper.getAllCommodityToStock();
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,sellerReleaseList);
    }
}
