package com.m5173.allureLove.model.eo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2018/5/14.
 */
public class PreviewOrderEO {
    private List coupons;
    //订单价格
    private BigDecimal payPrice;
    //收货地址
    private UserAddressEO address;

    private Integer freight;

    private BigDecimal goodsPrice;

    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freight) {
        this.freight = freight;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public List getCoupons() {
        return coupons;
    }

    public void setCoupons(List coupons) {
        this.coupons = coupons;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public UserAddressEO getAddress() {
        return address;
    }

    public void setAddress(UserAddressEO address) {
        this.address = address;
    }
}
