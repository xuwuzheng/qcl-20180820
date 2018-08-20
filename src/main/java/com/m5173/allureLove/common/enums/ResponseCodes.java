/************************************************************************************
 *  Copyright 2014 WZITech Corporation. All rights reserved.
 *	
 *	模	块：		ResponseCodes
 *	包	名：		com.wzitech.gamegold.common.enums
 *	项目名称：	gamegold-common
 *	作	者：		HeJian
 *	创建时间：	2014-1-12
 *	描	述：		
 *	更新纪录：	1. HeJian 创建于 2014-1-12 下午5:10:36
 * 				
 ************************************************************************************/
package com.m5173.allureLove.common.enums;

/**
 * 服务响应码枚举类
 * @author HeJian
 *
 */
public enum ResponseCodes {
	Success(200, "成功"),
	Error(01, "服务器发生异常，请重试"),
	InvalidAuthkey(10, "非法的用户认证码，请重新登录"),
	UserNotLogin(11, "用户未登录"),
	ImageTypeWrong(12, "上传的图片格式不对，必须是jpg格式"),
	Undefined(13, "未定义"),
	IllegalArgument(14,"非法参数"),
	NotPermissions(15, "无权限"),
	ConvertMapError(16,"转换成Map错误"),
	EmptyToken(17, "令牌不能为空"),
	InvalidToken(18, "无效的令牌"),
	AccessNotAllow(19, "无权限访问"),
	TypeMismatchAccessError(20, "类型不匹配异常"),
	CookieDecodeError(21, "cookie解码错误"),
	CookieTransformError(22, "cookie转换异常"),
	CookieDesDecodeError(23, "des解码异常"),
	CookieDesEncodeError(24, "des加密异常"),
	EmptyInfo(25, "{}不能为空"),
	NoThisUser(26, "无该用户"),
	InvalidPassword(27, "密码错误"),
	OperationFailed(28, "{}失败"),
	NotExist(29, "{}不存在"),
	Disable(30, "该用户已被禁用"),
	NotValid(31, "{}无效"),
	AccountRegistered(32, "用户名已经被注册"),
	NickNameRegistered(33, "昵称已经被使用"),
	Enable(34, "该用户已被启用"),
	InvalidOldPassword(34, "旧密码错误"),
	InvokeInterfaceReturnFail(35, "调用接口返回状态码：{}, 错误消息：{}"),
	NotDeleteParent(36, "该父类存在子类,不能删除"),
	InvokeInterfaceFail(37, "调用接口失败：{}"),

	ChangeTypeError(38, "{}转换异常"),
	UndefinedCart(39, "未找到对应ID为{}的相关购物车信息"),

	//商品异常

	;

	/**
	 * 返回码
	 */
	private int code;
	
	/**
	 * 返回码说明
	 */
	private String message;
	
	ResponseCodes(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	/**
	 * 通过code获取对应的ResponseCodes
	 * @param code 错误码
	 * @return 响应码对应的ResponseCodes枚举
	 */
	public static ResponseCodes getResponseByCode(int code){
		for(ResponseCodes responseCode : ResponseCodes.values()){
			if(responseCode.getCode() == code) {
				return responseCode;
			}
		}
		
		throw new IllegalArgumentException("未能找到匹配的ResponseCodes:" + code);
	}
	
	public static ResponseCodes responseByCode(int code){
		for(ResponseCodes responseCode : ResponseCodes.values()){
			if(responseCode.getCode() == code) {
				return responseCode;
			}
		}
		return ResponseCodes.Error;
	}

	public int getCode() {
		return code;
	}

	/**
	 * 获取编码对应消息
	 * @return
	 */
	public String getMessage(){
		return this.message;
	}
}
