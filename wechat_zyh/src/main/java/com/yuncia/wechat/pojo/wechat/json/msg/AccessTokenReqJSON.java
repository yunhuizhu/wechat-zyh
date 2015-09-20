package com.yuncia.wechat.pojo.wechat.json.msg;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import com.yuncia.wechat.pojo.wechat.json.WeChatJSON;

/**
 * @author MonKong
 * @Description 用一句话描述该类的作用
 * @date 2014年12月9日
 */
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class AccessTokenReqJSON extends WeChatJSON {
    private String grantType;
    private String appid;
    private String secret;
    private String accessToken;

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "AccessTokenReqJSON [grantType=" + grantType + ", appid="
                + appid + ", secret=" + secret + ", accessToken=" + accessToken
                + "]";
    }

}
