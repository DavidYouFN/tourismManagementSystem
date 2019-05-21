package com.felix.project.commodity.model;

import javax.validation.constraints.NotBlank;

public class Commodity {
    private String commodityId;

    @NotBlank(message="商品名不能为空")
    private String commodityName;

    private String commodityDescribe;

    @NotBlank(message="价格不能为空")
    private String commodityPrice;

    @NotBlank(message="商家联系方式不能为空")
    private String sellerContactInformation;

    @NotBlank(message="供应商不能为空")
    private String supplier;

    private String favorableRate;

    private String nofavorableRate;

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId == null ? null : commodityId.trim();
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName == null ? null : commodityName.trim();
    }

    public String getCommodityDescribe() {
        return commodityDescribe;
    }

    public void setCommodityDescribe(String commodityDescribe) {
        this.commodityDescribe = commodityDescribe == null ? null : commodityDescribe.trim();
    }

    public String getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(String commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public String getSellerContactInformation() {
        return sellerContactInformation;
    }

    public void setSellerContactInformation(String sellerContactInformation) {
        this.sellerContactInformation = sellerContactInformation == null ? null : sellerContactInformation.trim();
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }

    public String getFavorableRate() {
        return favorableRate;
    }

    public void setFavorableRate(String favorableRate) {
        this.favorableRate = favorableRate == null ? null : favorableRate.trim();
    }

    public String getNofavorableRate() {
        return nofavorableRate;
    }

    public void setNofavorableRate(String nofavorableRate) {
        this.nofavorableRate = nofavorableRate == null ? null : nofavorableRate.trim();
    }
}