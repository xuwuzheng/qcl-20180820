package com.m5173.allureLove.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.m5173.allureLove.common.base.BaseResponse;
import com.m5173.allureLove.common.enums.ResponseCodes;
import com.m5173.allureLove.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by Administrator on 2018/4/27.
 */
public class HttpUtil {
    protected static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);
    /**
     * http put请求
     * @param url 请求地址
     * @param reqJsonStr  请求参数
     * @return 请求返回结果
     */
    public static BaseResponse httpRequest(String url, String reqJsonStr, HttpMethod method){
        LOGGER.info("请求路径："+url);
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = null;
        if(StringUtils.isNotEmpty(reqJsonStr)){
            entity = new HttpEntity<>(reqJsonStr,headers);
            LOGGER.info("请求参数："+reqJsonStr);
        }
        ResponseEntity<String> exchange;
        try {
            exchange = template.exchange(url, method, entity, String.class);
        }catch (Exception e){
            throw new BusinessException(ResponseCodes.InvokeInterfaceFail, e.getMessage());
        }
        String body = exchange.getBody();
        LOGGER.info("返回信息body："+body);
        JSONObject result = JSONObject.parseObject(body);
        return convertResult(result);
    }

    private static BaseResponse convertResult(JSONObject result){
        BaseResponse<Object> baseResponse= new BaseResponse<>();

        int code = result.getIntValue("code");
        if(code == 0){
            baseResponse.setSuccess();
            baseResponse.setData(result.get("data"));
        }else{
            baseResponse.setErrorMessage(ResponseCodes.InvokeInterfaceReturnFail, String.valueOf(code), result.getString("msg"));
        }
        return baseResponse;
    }
    //get 请求 请求参数json格式传入进行拼接？后的参数信息
    public static String convertGetRequest(String json){
        if(StringUtils.isNotBlank(json)) {
            Map mapTypes = JSON.parseObject(json);
            StringBuffer requestStr = new StringBuffer();
            requestStr.append("?");
            for (Object obj : mapTypes.keySet()) {
                requestStr.append(obj).append("=").append(mapTypes.get(obj)).append("&");
            }
            if (StringUtils.isNotBlank(requestStr)) {
                return requestStr.substring(0, requestStr.length() - 1);
            }
        }
        return "";
    }

}
