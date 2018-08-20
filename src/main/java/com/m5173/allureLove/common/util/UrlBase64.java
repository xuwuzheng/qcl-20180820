package com.m5173.allureLove.common.util;

import com.m5173.allureLove.common.exception.BusinessException;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;


public class UrlBase64 {
    public final static String ENCODING = "UTF-8";

    public static String encoded(byte[] data) {
        try{
            byte[] b = Base64.encodeBase64URLSafe(data);
            return new String(b, ENCODING);
        }catch (UnsupportedEncodingException e){
            throw new BusinessException(e);
        }

    }

    public static byte[] decode(String data) {
        byte[] b = Base64.decodeBase64(data);
        return b;
    }

}