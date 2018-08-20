/************************************************************************************
 *  Copyright 2012 WZITech Corporation. All rights reserved.
 *	
 *	模	块：		RuntimeConstants
 *	包	名：		com.wzitech.chaos.framework.common
 *	项目名称：	chaos-common 
 *	作	者：		Shawn
 *	创建时间：	2012-4-13
 *	描	述：		系统运行时常量定义类
 *	更新纪录：	1. Shawn 创建于 2012-4-13 下午4:15:17
 * 				
 ************************************************************************************/
package com.m5173.allureLove.common.constants;

import com.m5173.allureLove.common.util.JsonMapper;

/**
 * 系统运行时常量定义类
 * @author Shawn
 *
 */
public class CommonConstants {

	/**
	 * 默认的jsonMapper
	 */
	public static JsonMapper jsonMapper = JsonMapper.nonEmptyMapper();

	/**
	 * 用户自定义session
	 */
	public static final String TOKEN_NAME = "_wemall";

	/**
	 * 用户ID-请求认证HTTP HEAD标签
	 */
	public static final String SERVICE_REQUEST_HEADER_UID = "uid";

	/**
	 * 浏览器session
	 */
	public static final String ONLY_ONE = "JSESSIONID";

	/**
	 * HTTP请求间隔号
	 */
	public static final String HTTP_INTERVAL = "/";



}
