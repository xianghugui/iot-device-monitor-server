package com.iot.security.common.exception.auth;


import com.iot.security.common.constant.CommonConstants;
import com.iot.security.common.exception.BaseException;


public class ClientForbiddenException extends BaseException {
    public ClientForbiddenException(String message) {
        super(message, CommonConstants.EX_CLIENT_FORBIDDEN_CODE);
    }

}
