package com.m5173.allureLove.model.eo;

import com.m5173.allureLove.common.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "t_category")
public class CategoryEO  extends BaseEntity {
    /**
     * 父级ID
     */
    @Column(name = "parent_id")
    private Integer parentId;

    private String name;

    @Column(name = "has_next")
    private String hasNext;

    /**
     * 获取父级ID
     *
     * @return parent_id - 父级ID
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父级ID
     *
     * @param parentId 父级ID
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @return has_next
     */
    public String getHasNext() {
        return hasNext;
    }

    /**
     * @param hasNext
     */
    public void setHasNext(String hasNext) {
        this.hasNext = hasNext;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}