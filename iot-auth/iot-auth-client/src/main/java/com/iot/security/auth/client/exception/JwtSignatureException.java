package com.iot.security.auth.client.exception;

public class JwtSignatureException extends Exception {
    public JwtSignatureException(String s) {
        super(s);
    }
}
