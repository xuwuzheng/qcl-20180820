package com.m5173.allureLove.model.eo;

import com.m5173.allureLove.common.base.BaseEntity;

import javax.persistence.Table;

/**
 * @author 339664
 */
@Table(name = "t_upvote")
public class UpvoteEO extends BaseEntity {

    private String text;

    private Integer type;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}