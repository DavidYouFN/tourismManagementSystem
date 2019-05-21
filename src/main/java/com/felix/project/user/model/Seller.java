package com.felix.project.user.model;

import com.felix.project.user.validate.IsMobile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Seller {
    private String sellerId;

    @NotBlank(message="商家名不能为空")
    private String sellerName;

    @NotBlank(message="店铺名不能为空")
    private String shopName;

    @NotBlank(message="店铺地区不能为空")
    private String shopArea;

    @NotBlank(message="联系方式不能为空")
    @IsMobile
    private String phone;

    @NotBlank(message="邮箱不能为空")
    @Pattern(regexp="[a-za-z0-9._%+-]+@[a-za-z0-9.-]+\\.[a-za-z]{2,4}", message="邮件格式错误")
    private String email;

    @NotBlank(message="服务类型不能为空")
    private String serviceType;

    @NotBlank(message="资产不能为空")
    private String assets;

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId == null ? null : sellerId.trim();
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName == null ? null : sellerName.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopArea() {
        return shopArea;
    }

    public void setShopArea(String shopArea) {
        this.shopArea = shopArea == null ? null : shopArea.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }

    public String getAssets() {
        return assets;
    }

    public void setAssets(String assets) {
        this.assets = assets;
    }
}