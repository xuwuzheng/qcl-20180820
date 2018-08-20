package com.m5173.allureLove.common.exception;

import com.m5173.allureLove.common.enums.ResponseCodes;
import org.slf4j.helpers.MessageFormatter;

public class BusinessException extends RuntimeException {

    private int errorCode;

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    private BusinessException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }

    public BusinessException(ResponseCodes responseCodes) {
        this(responseCodes.getCode(), responseCodes.getMessage());
    }

    public BusinessException(ResponseCodes responseCodes, String... args) {
        this(responseCodes.getCode(), MessageFormatter.arrayFormat(responseCodes.getMessage(), args).getMessage());
    }

    public BusinessException(Throwable t) {
        super(t);
        this.errorCode = ResponseCodes.Error.getCode();
    }

}