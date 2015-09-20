package com.yuncia.wechat.sys;

import org.springframework.beans.factory.annotation.Value;

public class SysConstant {
    public static final String CHARSET = "UTF-8";
    public static final String SEPARATOR = System.getProperty("line.separator");
    public static String wechatUrl;
    public static String globalAccessToken;
    public static String prizeImageUrl;


    @Value("${wechat.sendMessage.url}")
    public void setWechatUrl(String wechatUrl) {
        SysConstant.wechatUrl = wechatUrl;
    }
    @Value("${prize.image.url}")
    public static void setPrizeImageUrl(String prizeImageUrl) {
        SysConstant.prizeImageUrl = prizeImageUrl;
    }
}
