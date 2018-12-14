package com.iot.util;

import cn.jsms.api.SendSMSResult;
import cn.jsms.api.ValidSMSResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jsms.api.common.SMSClient;
import cn.jsms.api.common.model.SMSPayload;
import org.springframework.stereotype.Service;

/*
 *
 * 功能描述: 短信验证工具类
 * @author FQ
 * @date 11/27/2018 1:53 PM
 * @param
 * @return
 */
@Service
public class SendSmsUtil {
    protected static final Logger LOG = LoggerFactory.getLogger(SendSmsUtil.class);
    private static final String APP_KEY = "06f571e43e8fc365e920b6e6";
    private static final String MASTER_SECRET = "359e60a6ce90707aaaed94f8";

    /**
     * 发送短信验证码
     * @param phone
     * @return
     */
    public String sendSMSCode(String phone) {
        SMSClient client = new SMSClient(MASTER_SECRET, APP_KEY);
        SMSPayload payload = SMSPayload.newBuilder()
                .setMobileNumber(phone)
                .setTempId(157940)
                .setTTL(60)
                .build();
        String result = null;
        try {
            SendSMSResult res = client.sendSMSCode(payload);
            result = res.toString();
            System.out.println(res.toString());
            LOG.info(res.toString());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
        return result;// 返回的是 json{msg_id:"4346777"}
    }

    /**
     * 验证码校验
     * @param msg_id
     * @param value
     * @return
     */
    public Boolean sendValidSMSCode(String msg_id, String value) {
        SMSClient client = new SMSClient(MASTER_SECRET, APP_KEY);
        Boolean valid = false;
        try {
            ValidSMSResult res = client.sendValidSMSCode(msg_id, value);
            valid = res.getIsValid();
            System.out.println(res.toString());
            LOG.info(res.toString());
        } catch (APIConnectionException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            e.printStackTrace();
            if (e.getErrorCode() == 50010) {
                // do something
            }
            System.out.println(e.getStatus() + " errorCode: " + e.getErrorCode() + " " + e.getErrorMessage());
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
        return valid;
    }

}
