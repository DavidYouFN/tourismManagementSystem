package com.felix.project.commodity.model;

public class Picture {
    private Integer id;

    private String commodityId;

    private String commodityPicUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId == null ? null : commodityId.trim();
    }

    public String getCommodityPicUrl() {
        return commodityPicUrl;
    }

    public void setCommodityPicUrl(String commodityPicUrl) {
        this.commodityPicUrl = commodityPicUrl == null ? null : commodityPicUrl.trim();
    }
}