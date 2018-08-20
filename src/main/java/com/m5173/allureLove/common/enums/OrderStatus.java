package com.m5173.allureLove.common.enums;

/**
 * 订单状态
 */
public enum OrderStatus {
    /**
     * 待付款
     */
    WAIT_PAYMENT(1, "待付款"),
    /**
     * 待发货
     */
    WAIT_DELIVERY(2, "待发货"),
    /**
     * 待收货
     */
    DELIVERY(3, "待收货"),
    /**
     * 已取消
     */
    CANCELED(4, "已取消"),
    /**
     * 待评价
     */
    TO_BE_EVALUATED(10, "待评价")
    ;

    private int code;
    private String name;

    OrderStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 根据code值获取对应的枚举
     * @param code
     * @return
     */
    public static OrderStatus getTypeByCode(int code){
        for(OrderStatus type : OrderStatus.values()){
            if(type.getCode() == code){
                return type;
            }
        }

        throw new IllegalArgumentException("未能找到匹配的OrderState:" + code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
