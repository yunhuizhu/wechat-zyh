package com.yuncia.wechat.pojo.wechat.json.msg;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author MonKong
 * @Description 调用获取token接口返回的信息
 * @date 2014年12月11日
 */
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class AccessTokenRespJSON extends ReturnJSON {
    private String accessToken;
    private Integer expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public String toString() {
        return "AccessTokenRespJSON [accessToken=" + accessToken
                + ", expiresIn=" + expiresIn + "]" + super.toString();
    }

}
