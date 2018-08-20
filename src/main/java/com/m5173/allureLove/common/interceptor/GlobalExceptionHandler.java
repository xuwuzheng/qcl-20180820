package com.m5173.allureLove.common.interceptor;

import com.m5173.allureLove.common.exception.BusinessException;
import com.m5173.allureLove.common.base.BaseResponse;
import com.m5173.allureLove.common.enums.ResponseCodes;
import com.m5173.allureLove.common.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public BaseResponse defaultErrorHandler(HttpServletRequest request, Exception e){
        BaseResponse response = new BaseResponse();
        LOGGER.error(ExceptionUtil.getPrintStackTrace(e,request));
        response.setErrorMessage(ResponseCodes.Error);    //这个异常就是程序内部的异常
        return response;
    }


    @ExceptionHandler(value = BusinessException.class)
    public BaseResponse baseExceptionHandler(HttpServletRequest request, BusinessException e){
        BaseResponse response = new BaseResponse();
        response.setErrorMessage(e);
        LOGGER.error(ExceptionUtil.getPrintStackTrace(e, request));
        return response;
    }



}
