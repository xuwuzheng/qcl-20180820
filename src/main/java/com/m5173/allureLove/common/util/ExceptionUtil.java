package com.m5173.allureLove.common.util;

import com.m5173.allureLove.common.enums.ResponseCodes;
import com.m5173.allureLove.common.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

public class ExceptionUtil {


    public static String getPrintStackTrace(Exception e,HttpServletRequest request) {
        return getErrorInfo(e, request);
    }


    public static String getPrintStackTrace(BusinessException e, HttpServletRequest request) {
        return getErrorInfo(e,request);
    }

    public static String getPrintStackTrace(BusinessException be){
        return getErrorInfo(be,null);
    }

    public static String getPrintStackTrace(Exception e){
        return getErrorInfo(e,null);
    }

    /**
     * 记录异常信息
     * @param be
     * @param request
     * @return
     */
    private static String getErrorInfo(Exception be, HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("---------------------------------------错误信息分割线-------------------------------------------------------");
        sb.append("\n");
        sb.append("请求时间："+ DateUtil.getFormatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
        sb.append("\n");
        if (request != null){
            String queryString = request.getQueryString();
            queryString = queryString != null ? (request.getRequestURI() + "?" + queryString) : request.getRequestURI();
            sb.append("url:"+queryString);
            sb.append("\n");
        }
        if (be instanceof  BusinessException){
            //BusinessException
            sb.append("错误码：");
            sb.append(((BusinessException) be).getErrorCode());
            sb.append("  错误信息：");
            sb.append(be.getMessage());
        }else {
            //other exception
            sb.append("错误信息:");
            sb.append(ResponseCodes.Error.getMessage());
            sb.append("\n");
            StringWriter sw = new StringWriter();
            be.printStackTrace(new PrintWriter(sw, true));
            sb.append("异常栈信息:"+sw.toString());
        }
        return sb.toString();
    }

}
