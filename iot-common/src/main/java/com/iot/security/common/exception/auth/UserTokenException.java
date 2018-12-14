package com.iot.security.common.exception.auth;


import com.iot.security.common.constant.CommonConstants;
import com.iot.security.common.exception.BaseException;


public class UserTokenException extends BaseException {
    public UserTokenException(String message) {
        super(message, CommonConstants.EX_USER_INVALID_CODE);
    }
}
