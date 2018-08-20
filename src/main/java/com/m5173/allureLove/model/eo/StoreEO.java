package com.m5173.allureLove.model.eo;

import com.m5173.allureLove.common.base.BaseEntity;

import java.util.List;

public class StoreEO extends BaseEntity {
    private String storeName = "建德新安东路店";
    private String storeAddr = "建德市新安东路168号";
    private Long areaId = 330100L;
    private String sign = "1";
    private Integer type = 1;
    private Double lat = 29.486202;
    private Double lng = 119.29614;
    private List data;

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public Long getStoreId() {
        return 30L;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddr() {
        return storeAddr;
    }

    public void setStoreAddr(String storeAddr) {
        this.storeAddr = storeAddr;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

}