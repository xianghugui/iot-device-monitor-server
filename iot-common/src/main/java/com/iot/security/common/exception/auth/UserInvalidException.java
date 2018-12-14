package com.iot.security.common.exception.auth;


import com.iot.security.common.constant.CommonConstants;
import com.iot.security.common.exception.BaseException;


public class UserInvalidException extends BaseException {
    public UserInvalidException(String message) {
        super(message, CommonConstants.EX_USER_PASS_INVALID_CODE);
    }
}
