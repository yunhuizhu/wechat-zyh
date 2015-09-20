package com.yuncia.wechat.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-5-4
 * Time: 上午10:21
 * To change this template use File | Settings | File Templates.
 */
public class OtherSystemUtil {
    private static Properties systeomProperties;
    static{
        Resource res1 = new ClassPathResource("/system.properties");
//        EncodedResource resource = new EncodedResource(res1,"GBK");
        try {
            systeomProperties=PropertiesLoaderUtils.loadProperties(res1);

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    public static Properties getSysteomProperties(){
            return   systeomProperties;
    }
    public static String appId=systeomProperties.
            getProperty("wechat.appId");
    public static String appSecret=systeomProperties.
            getProperty("wechat.appSecret");
    public static String codeToOpenIdUrl=systeomProperties.
            getProperty("wechat.codetoopenid.url").replace("{appid}",appId).
            replace("{secret}",appSecret);
    public static String customer_openid_url=systeomProperties.getProperty("customer.openid.url");
    public static String vote_openid_url=systeomProperties.getProperty("vote.openid.url");
    public static String  prizeExchange_openid_url= systeomProperties.getProperty("prizeExchange.openid.url");
    public static String  wechat_base_url= systeomProperties.getProperty("wechat.base.url");
    public static String  wechat_subscribe_msg= systeomProperties.getProperty("wechat.subscribe.msg");
    public static String  wechat_resetAccessToken_url= systeomProperties.getProperty("wechat.resetAccessToken.url");
    public static String  wechat_addMenu_url= systeomProperties.getProperty("wechat.addMenu.url");
    public static String  wechat_delMenu_url= systeomProperties.getProperty("wechat.delMenu.url");
    public static String  wechat_redirect_url= systeomProperties.getProperty("wechat.redirect.url");
    //新闻速递
    public static String  newsInfo_item1_picUrl= systeomProperties.getProperty("newsInfo.item1.picUrl");
    public static String  newsInfo_item1_url= systeomProperties.getProperty("newsInfo.item1.url");
}
