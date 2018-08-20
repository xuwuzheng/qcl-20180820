package com.m5173.allureLove.model.request;

public class LoverListRequest {

    private String id;

    private String nike;

    private String detail;

    private String tag;

    private String property;

    private String pic;

    private Integer sex;

    private Integer userId;

    private Integer page;

    private Integer pageSize;

    private String queryKey;

    private String sortKey;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getQueryKey() {
        return queryKey;
    }

    public void setQueryKey(String queryKey) {
        this.queryKey = queryKey;
    }

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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }
}