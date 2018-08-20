package com.m5173.allureLove.model.eo;

import com.m5173.allureLove.common.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author 339664
 */
@Table(name = "t_user_address")
public class UserAddressEO extends BaseEntity {

    private String consignee;

    private String detail;

    @Column(name = "is_default")
    private Boolean isDefault;

    private String phone;

    @Column(name = "user_id")
    private Long userId;

    public Long getAddressId() {
        return getId();
    }

    public void setAddressId(Long addressId) {
        super.setId(addressId);
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}