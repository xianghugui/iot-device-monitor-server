package com.iot.security.common.exception.auth;


import com.iot.security.common.constant.CommonConstants;
import com.iot.security.common.exception.BaseException;


public class ClientTokenException extends BaseException {
    public ClientTokenException(String message) {
        super(message, CommonConstants.EX_CLIENT_INVALID_CODE);
    }
}
