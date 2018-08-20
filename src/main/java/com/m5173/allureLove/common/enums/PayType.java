package com.m5173.allureLove.common.enums;

/**
 * 支付和提现类型
 * @author 339664
 */
public enum PayType {

	WEI_XIN(1, "微信"),
	ALY_PAY(2, "支付宝"),
	BANK_CARD(3, "银行卡");

	private int code;
	private String name;

	PayType(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getCode() {
		return this.code;
	}
	
	/**
	 * 是否为支付或提现类型
	 * @param code
	 * @return
	 */
	public static boolean hasPayType(int code) {
		for(PayType type : PayType.values()){
			if(type.getCode() == code) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 根据code获取支付或提现类型
	 * @param code
	 * @return
	 */
	public static PayType getTypeByCode(int code) {
		for(PayType type : PayType.values()){
			if(type.getCode() == code) {
				return type;
			}
		}
		throw new IllegalArgumentException("未能找到匹配的PayType:" + code);
	}
}
