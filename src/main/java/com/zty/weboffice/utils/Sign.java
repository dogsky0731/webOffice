package com.zty.weboffice.utils;

import client.AppAuthenticator;
import client.UaaAppAuthenticator;
import client.UaaAppConfigClient;
import constants.EnumResultCode;
import constants.UaaConstant;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import utils.DefaultResult;
import utils.IResult;

import java.util.Map;

@Component
public class Sign {
    @Qualifier
    private UaaAppConfigClient configClient;

    /**
     * 生成sign
     */
    public IResult<String> generateSign(String appId, String secret, Map<String, String[]> params) {
        AppAuthenticator authenticator = new UaaAppAuthenticator(UaaConstant.SIGN, null, UaaConstant.APPID);
        try {
            String[] appIds = params.get(UaaConstant.APPID);
            if (appIds == null || appIds.length != 1 || StringUtils.isEmpty(appIds[0])) {
                params.put(UaaConstant.APPID, new String[]{appId});
            }
            String sign = authenticator.generateSign(secret, params);
            return DefaultResult.successResult(sign);
        } catch (Exception e) {
            return DefaultResult.failResult(EnumResultCode.E_GENERATE_SIGN_FAIL.getInfo());
        }
    }
}
