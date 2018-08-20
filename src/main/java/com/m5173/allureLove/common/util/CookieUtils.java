package com.m5173.allureLove.common.util;

import com.alibaba.fastjson.JSONObject;
import com.m5173.allureLove.common.constants.CommonConstants;
import com.m5173.allureLove.common.enums.ResponseCodes;
import com.m5173.allureLove.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CookieUtils {

    private static final String DESKEY = "Y@j9^8Jv_cQW$45TrYv2UiWvO!";


    public static String saveCookie(HttpServletRequest request, HttpServletResponse response, JSONObject jsonObject) {

        String tokenParam = getTokenPara(jsonObject);
        try {
            tokenParam = URLEncoder.encode(tokenParam, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new BusinessException(ResponseCodes.CookieDecodeError);
        }
        Cookie cookie = getCookie(request, CommonConstants.TOKEN_NAME);
        if (cookie != null) {
            cookie.setValue(tokenParam);
        } else {
            // 重新new一个Cookie
            cookie = new Cookie(CommonConstants.TOKEN_NAME, tokenParam);
        }
        cookie.setPath("/");// 同一个域名所有url cookie共享
        cookie.setMaxAge(24*60*60);// 24小时后失效
        response.addCookie(cookie);
        return tokenParam;
    }

    public static String generateCookie(JSONObject jsonObject) {
        String tokenParam = getTokenPara(jsonObject);
        try {
            tokenParam = URLEncoder.encode(tokenParam, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new BusinessException(ResponseCodes.CookieDecodeError);
        }
        return tokenParam;
    }

    public static Cookie getCookie(HttpServletRequest request, String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                if (name.equals(cookies[i].getName())) {
                    return cookies[i];
                }
            }
        }
        return null;
    }

    public static JSONObject cookieToObject(String token){
        String value = token;
        String decrypt = null;
        try {
            value = URLDecoder.decode(value, "UTF-8");
            decrypt = DESHelper.decrypt(value, DESKEY);
        } catch (UnsupportedEncodingException e) {
            throw new BusinessException(ResponseCodes.CookieTransformError);
        } catch (Exception e) {
            throw new BusinessException(ResponseCodes.CookieDesDecodeError);
        }

        if (StringUtils.isNotBlank(decrypt)) {
            JSONObject jsonObject = JSONObject.parseObject(decrypt);
            return jsonObject;
        }
        return null;
    }

    private static String getTokenPara(JSONObject jsonObject){
        String encrypt = null;
        jsonObject.put("timestamp", System.currentTimeMillis());
        try {
            encrypt = DESHelper.encrypt(jsonObject.toString(), DESKEY);
        } catch (Exception e) {
            throw new BusinessException(ResponseCodes.CookieDesEncodeError);
        }
        return encrypt;
    }

    public static void deletePubCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookieUser = getCookie(request, CommonConstants.TOKEN_NAME);
        if (cookieUser != null) {
            cookieUser.setMaxAge(0);
            cookieUser.setPath("/");
            response.addCookie(cookieUser);
        }

        Cookie cookSessions = getCookie(request, CommonConstants.ONLY_ONE);
        if (cookSessions != null) {
            cookSessions.setMaxAge(0);
            cookSessions.setPath("/");
            response.addCookie(cookSessions);
        }
        UserContext.clean();
    }

    /**
     *
     * <p>清理上下文信息</p>
     * @author ztjie
     * @date 2015-11-5 下午1:11:06
     * @see
     */
    public static void cleanContext(){
        UserContext.clean();
    }


}
