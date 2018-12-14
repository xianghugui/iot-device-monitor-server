package com.iot.security.auth.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
@Data
public class KeyConfiguration {
    @Value("${jwt.rsa-secret}")
    private String userSecret;
    @Value("${client.rsa-secret}")
    private String serviceSecret;
    private byte[] userPubKey;
    private byte[] userPriKey;
    private byte[] servicePriKey;
    private byte[] servicePubKey;

    public String getUserSecret() {
        return userSecret;
    }

    public String getServiceSecret() {
        return serviceSecret;
    }

    public void setUserPubKey(byte[] userPubKey) {
        this.userPubKey = userPubKey;
    }

    public void setUserPriKey(byte[] userPriKey) {
        this.userPriKey = userPriKey;
    }

    public void setServicePriKey(byte[] servicePriKey) {
        this.servicePriKey = servicePriKey;
    }

    public void setServicePubKey(byte[] servicePubKey) {
        this.servicePubKey = servicePubKey;
    }

    public byte[] getUserPubKey() {
        return userPubKey;
    }

    public byte[] getUserPriKey() {
        return userPriKey;
    }

    public byte[] getServicePriKey() {
        return servicePriKey;
    }

    public byte[] getServicePubKey() {
        return servicePubKey;
    }
}
