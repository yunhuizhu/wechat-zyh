package com.yuncia.wechat.util;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-5-4
 * Time: 上午10:33
 * To change this template use File | Settings | File Templates.
 */
public class OtherSystemInvoke {
    public static String exchangeGift(Map<String, Object> params) {
        String result="";
        try {
            String response="";
            response=HttpsUtils.convertToQueryStr(params).replace("?","");
            System.out.println("file.encoding:" + System.getProperty("file.encoding"));
//            response=new String(response.getBytes("GBK"), "UTF-8");
//            result = HttpsUtils.doPost((String) OtherSystemUtil.exchange_gift_url, response, null, false);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return result;
    }
}
