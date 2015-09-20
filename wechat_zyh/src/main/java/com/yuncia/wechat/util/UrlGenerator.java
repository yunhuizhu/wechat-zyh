package com.yuncia.wechat.util;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 生成对应业务的URL地址
 *
 * @author MonKong
 * @date 2014年12月18日
 */
@Component
public class UrlGenerator {
    // private static final Logger log = LogManager.getLogger();
    private static String resetAccessTokenUrl;
    private static String sendMessageUrl;
    private static String appId;
    private static String appSecret;

    // private static String encodingAesKey;
    // private static String token;

    @Autowired
    public UrlGenerator(@Value("${wechat.appId}") String appId,
                        @Value("${wechat.appSecret}") String appSecret,
                        @Value("${wechat.sendMessage.url}") String sendMessageUrl,
                        // @Value("${wechat.encodingAesKey}") String encodingAesKey,
                        // @Value("${wechat.token}") String token,
                        @Value("${wechat.resetAccessToken.url}") String resetAccessTokenUrl) {
        super();
        UrlGenerator.appId = appId;
        UrlGenerator.appSecret = appSecret;
        UrlGenerator.resetAccessTokenUrl = resetAccessTokenUrl;
        UrlGenerator.sendMessageUrl = sendMessageUrl;
        // UrlGenerator.encodingAesKey = encodingAesKey;
        // UrlGenerator.token = token;
    }

    /**
     * 启动后生成静态URL
     */
    @PostConstruct
    private void staticAccessTokenUrl() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("appid", appId);
        params.put("secret", appSecret);
        resetAccessTokenUrl = replaceParams(resetAccessTokenUrl, params);
    }

    // https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN
    public static String createSendMessageUrl(String accessToken) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("access_token", accessToken);
        return replaceParams(sendMessageUrl, params);
    }

    // https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
    public static String createAccessTokenUrl() {
        return resetAccessTokenUrl;
    }

    /**
     * 替换URL中的变量；变量名指定约定：变量名={参数名}
     *
     * @param params
     * @return
     */
    private static String replaceParams(String url, Map<String, String> params) {
        for (Map.Entry<String, String> en : params.entrySet()) {
            url = url.replace("{" + en.getKey() + "}", en.getValue());
        }
        return url;
    }

    /**
     * 把Map转为URL的查询字符串(问号开头)
     *
     * @param params
     * @return
     * @date 2014年12月18日
     */
    public static String convertToQueryStr(Map<String, String> params) {
        // 拼凑参数
        StringBuffer queryStr = new StringBuffer("");
        if (params != null) {
            boolean isFirst = true;
            for (Map.Entry<String, String> en : params.entrySet()) {
                Object value = en.getValue();
                if (value != null) {// 空值不转换
                    String kv = en.getKey() + "=" + value.toString();
                    if (isFirst) {
//                        queryStr.append("?");
                        isFirst = false;
                    } else {
                        queryStr.append("&");
                    }
                    queryStr.append(kv);
                }
            }
        }
        // logger.debug("converToQueryStr:" + queryStr);
        return queryStr.toString();
    }
}
