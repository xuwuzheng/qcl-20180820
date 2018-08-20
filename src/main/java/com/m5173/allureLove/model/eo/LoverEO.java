package com.m5173.allureLove.model.eo;

import com.m5173.allureLove.common.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author 339664
 */
@Table(name = "t_lover")
public class LoverEO extends BaseEntity {

    private String nike;

    private String detail;

    private String tag;

    private String property;

    private Integer popular;

    @Column(name = "like_number")
    private Integer likeNumber;

    private String pic;

    private Integer sex;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    @Column(name = "create_time")
    private Date createTime;

    public String getNike() {
        return nike;
    }

    public void setNike(String nike) {
        this.nike = nike;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Integer getPopular() {
        return popular;
    }

    public void setPopular(Integer popular) {
        this.popular = popular;
    }

    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}