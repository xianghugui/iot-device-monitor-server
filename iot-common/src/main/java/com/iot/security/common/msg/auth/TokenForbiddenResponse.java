package com.iot.security.common.msg.auth;

import com.iot.security.common.constant.RestCodeConstants;
import com.iot.security.common.msg.BaseResponse;


public class TokenForbiddenResponse  extends BaseResponse {
    public TokenForbiddenResponse(String message) {
        super(RestCodeConstants.TOKEN_FORBIDDEN_CODE, message);
    }
}
