package com.m5173.allureLove.model.eo;

import com.m5173.allureLove.common.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author 339664
 */
@Table(name = "t_order_sku")
public class OrderSkuEO extends BaseEntity {

    @Column(name = "sku_id")
    private Long skuId;

    @Column(name = "sku_price")
    private BigDecimal skuPrice;

    @Column(name = "sku_name")
    private String skuName;

    @Column(name = "sku_logo")
    private String skuLogo;

    private Integer amount;

    @Column(name = "order_no")
    private  String orderNo;

    private  Integer status;

    public Long getOrderSkuId() {
        return getId();
    }

    public void setOrderSkuId(Long orderSkuId) {
        super.setId(orderSkuId);
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public BigDecimal getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(BigDecimal skuPrice) {
        this.skuPrice = skuPrice;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSkuLogo() {
        return skuLogo;
    }

    public void setSkuLogo(String skuLogo) {
        this.skuLogo = skuLogo;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}