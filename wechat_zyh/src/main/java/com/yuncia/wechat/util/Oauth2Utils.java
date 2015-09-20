package com.yuncia.wechat.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-4-30
 * Time: 下午5:11
 * To change this template use File | Settings | File Templates.
 */
public class Oauth2Utils {
    public static String getOpenIdByCode(String code){
        String  openId=null;
        try {
            String respJson = HttpsUtils.doGet(OtherSystemUtil.codeToOpenIdUrl.replace("{code}",code),
                     null,true);
            System.out.println("getOpenIdByCode respJson:"+respJson);
            JSONObject obj = JSON.parseObject(respJson);
            openId=obj.getString("openid");
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return openId;
    }
}
