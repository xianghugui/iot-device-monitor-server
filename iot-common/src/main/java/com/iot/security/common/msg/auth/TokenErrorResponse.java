package com.iot.security.common.msg.auth;

import com.iot.security.common.constant.RestCodeConstants;
import com.iot.security.common.msg.BaseResponse;


public class TokenErrorResponse extends BaseResponse {
    public TokenErrorResponse(String message) {
        super(RestCodeConstants.TOKEN_ERROR_CODE, message);
    }
}
