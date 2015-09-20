/**
 *
 */
package com.yuncia.wechat.util;

import com.yuncia.wechat.sys.SysConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author MonKong
 * @Description 接口调用工具类
 * @date 2014年12月8日
 */
@SuppressWarnings("unused")
public class HttpsUtils {
    private static final Logger logger = LogManager.getLogger();
    private static int defaultTimeOut = 4;
    public static final String GET = "GET";
    public static final String POST = "POST";

    public static String doPost(String requestUrl, String requestBody,boolean isEncode)
            throws Exception {
        return doPost(requestUrl, requestBody, 0,isEncode);// 无超时
    }

    /**
     * 可以设置连接超时时间。对于微信业务特别有用。
     *
     * @param requestUrl
     * @param requestBody
     * @param params
     * @param timeout
     * @return
     * @throws java.io.IOException
     * @date 2014年12月12日
     */
    private static String doPost(String requestUrl, String requestBody,
                                 Integer timeout,boolean isEncode) throws Exception {
        return doRequest(requestUrl, POST, requestBody, timeout, null,isEncode);
    }

    /**
     * 使用默认的超时时间
     *
     * @param requestUrl
     * @param requestBody
     * @param params
     * @param timeout
     * @return
     * @throws Exception
     * @date 2014年12月12日
     */
    private static String doPostWithDefaultTimeOut(String requestUrl,
                                                   String requestBody, Map<String, String> params,boolean isEncode) throws Exception {
        return doRequest(requestUrl, POST, requestBody, defaultTimeOut, params,isEncode);
    }

    /**
     * 执行POST请求 用一句话描述该方法的作用
     *
     * @param requestUrl
     * @param requestBody
     * @param params
     * @return
     * @throws Exception
     * @date 2014年12月11日
     */
    public static String doPost(String requestUrl, String requestBody,
                                Map<String, Object> params,boolean isEncode) throws Exception {
        return doPost(requestUrl, requestBody, 0,isEncode);// 无超时
    }

    /**
     * 发起Get请求
     *
     * @param requestUrl
     * @param params     GET请求参数,null为无参数
     * @return 请求返回内容
     * @throws Exception
     * @date 2014年12月8日
     */
    public static String doGet(String requestUrl, Map<String, String> params,boolean isEncode)
            throws Exception {
        return doRequest(requestUrl, GET, null, 0, params,isEncode);// 无超时
    }

    private static String doGetWithDefaultTimeOut(String requestUrl,
                                                  Map<String, String> params,boolean isEncode) throws Exception {
        return doRequest(requestUrl, GET, null, defaultTimeOut, params,isEncode);// 4秒超时
    }

    /**
     * HTTP请求的核心方法
     *
     * @param method
     * @param requestUrl
     * @param requestBody
     * @param timeout
     * @param params
     * @return
     * @throws Exception
     * @date 2014年12月12日
     */
    private static String doRequest(String requestUrl, String method,
                                    String requestBody, int timeout, Map<String, String> params,boolean isEncode)
            throws Exception {
        InputStream in = null;
        try {
            String queryStr = UrlGenerator.convertToQueryStr(params);
            // URL编码
            if(isEncode){
                queryStr= URLEncoder
                        .encode(queryStr,SysConstant.CHARSET);
            }
            System.out.println("requestUrl + queryStr:"+requestUrl + queryStr);
            URLConnection conn = new URL(requestUrl + queryStr)
                    .openConnection();
            // 微信5秒超时设定，因此调用第三方接口建议4秒超时
            conn.setReadTimeout(timeout);// 0秒为不限超时时间
            conn.setDefaultUseCaches(false);
            conn.setUseCaches(false);
//            conn.setRequestProperty("content-type","text/plain;charset=utf-8");
//            conn.setRequestProperty("charset", "gbk");
            if (POST.equalsIgnoreCase(method)) {// POST特有逻辑
                conn.setDoOutput(true);// POST标志
                OutputStream out = conn.getOutputStream();
                out.write(requestBody.getBytes(SysConstant.CHARSET));// 写入内容到流缓存
                close(out);// 正式发起请求
            }
            in = conn.getInputStream();
            return readStreamAsStr(in);
        } finally {
            close(in);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(URLEncoder.encode("123~! 哈哈", SysConstant.CHARSET));
        System.out.println(URLDecoder.decode("123%7E%21+%E5%93%88%E5%93%88",
                SysConstant.CHARSET));
        System.out.println(URLDecoder.decode("123~! 哈哈", SysConstant.CHARSET));
    }

    /**
     * 把Map转为URL的查询字符串
     *
     * @param params
     * @return
     * @date 2014年12月8日
     */
    public static String convertToQueryStr(Map<String, Object> params) {
        // 拼凑参数
        StringBuffer queryStr = new StringBuffer("");
        if (params != null) {
            boolean isFirst = true;
            for (Map.Entry<String, Object> en : params.entrySet()) {
                Object value = en.getValue();
                if (value != null) {// 空值不转换
                    String kv = en.getKey() + "=" + value.toString();
                    if (isFirst) {
                        queryStr.append("?");
                        isFirst = false;
                    } else {
                        queryStr.append("&");
                    }
                    queryStr.append(kv);
                }
            }
        }
        logger.debug("converToQueryStr:" + queryStr);
        return queryStr.toString();
    }

    /**
     * 把流读取为字符串
     *
     * @param in
     * @return
     * @throws java.io.IOException
     * @date 2014年12月8日
     */
    private static String readStreamAsStr(InputStream in) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(in,
                SysConstant.CHARSET));
        StringBuffer content = new StringBuffer("");
        boolean isFirst = true;
        for (String str; (str = reader.readLine()) != null; ) {
            if (isFirst) {// 第一行不需要换行
                content.append(str);
                isFirst = false;
            } else {
                content.append(SysConstant.SEPARATOR + str);
            }
        }
        return content.toString();
    }

    /**
     * 关闭流
     *
     * @param stream
     * @throws java.io.IOException
     * @date 2014年12月9日
     */
    private static void close(Closeable stream) throws IOException {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                logger.error("流关闭失败！", new Exception());
                throw e;
            }
        } else {
            logger.error("流为null");
        }
    }
}
