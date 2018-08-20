package com.m5173.allureLove.common.base;

import com.m5173.allureLove.common.enums.ResponseCodes;
import com.m5173.allureLove.common.exception.BusinessException;
import org.slf4j.helpers.MessageFormatter;

public abstract class AbstractServiceResponse implements IServiceResponse{

    private int code = ResponseCodes.Success.getCode();     //默认成功
    private String message = ResponseCodes.Success.getMessage() ;

    /**
     * 返回错误码和错误信息
     * @param message
     */
    public void setErrorMessage(int code, String message){
        this.code = code;
        this.message = message;
    }

    /**
     * 返回错误码和错误信息
     * @param responseCodes
     */
    public void setErrorMessage(ResponseCodes responseCodes){
        this.code = responseCodes.getCode();
        this.message = responseCodes.getMessage();
    }

    /**
     * 返回错误码和错误信息
     * @param responseCodes
     */
    public void setErrorMessage(ResponseCodes responseCodes, String... args){
        this.code = responseCodes.getCode();
        this.message = MessageFormatter.arrayFormat(responseCodes.getMessage(), args).getMessage();
    }

    /**
     * 返回错误码和错误信息
     * @param e 异常
     */
    public void setErrorMessage(BusinessException e){
        this.code = e.getErrorCode();
        this.message = e.getMessage();
    }

    public void setSuccess(){
        setErrorMessage(ResponseCodes.Success);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
