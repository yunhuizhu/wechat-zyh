package com.springapp.mvc;

import com.yuncia.wechat.util.OtherSystemUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-4-3
 * Time: 上午10:32
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class HttpUtil extends BaseServiceTest{
    private long startTime = 0L;
    private long endTime = 0L;
    private String getTokenUri= OtherSystemUtil.wechat_resetAccessToken_url.replace("{appid}",
            OtherSystemUtil.appId).replace("{secret}", OtherSystemUtil.appSecret);
    private String menuUri;
    private String delMenu;
    private String wechatRedirect;
    HttpClient httpclient = new DefaultHttpClient();

    public HttpUtil() throws IOException {
        menuUri = OtherSystemUtil.wechat_addMenu_url.replace("{access_token}",getToken());
        delMenu= OtherSystemUtil.wechat_delMenu_url.replace("{access_token}",getToken());
        wechatRedirect=OtherSystemUtil.wechat_redirect_url.replace("{appid}", OtherSystemUtil.appId);
    }

    public  HttpEntity doPost(String url,HttpEntity httpEntity){
           return null;
    }
    @Test
    public  void test() throws IOException {
//        System.out.println("token:"+getToken());
        System.out.println("OtherSystemUtil.wechat_subscribe_msg:" + OtherSystemUtil.wechat_subscribe_msg);
    }
    public String getToken() throws IOException {
        HttpGet httpget = new HttpGet(getTokenUri);
        HttpResponse response = null;
        response = httpclient.execute(httpget);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
        {
            String strResult = EntityUtils.toString(response.getEntity());
            JSONObject obj = JSON.parseObject(strResult);
            return obj.getString("access_token");

        }
        return "";
    }
    @Test
    public void addMenu() throws IOException {
        HttpPost httpPost = new HttpPost(menuUri);
        StringEntity se = new StringEntity(getJson(),HTTP.UTF_8);
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        httpPost.setEntity(se);
        HttpResponse response = null;
        startTime = System.currentTimeMillis();
        response = httpclient.execute(httpPost);
        endTime = System.currentTimeMillis();
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode:" + statusCode);
        System.out.println("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
        if(statusCode != HttpStatus.SC_OK){
            System.out.println("Method failed:" + response.getStatusLine());
            int status = 1;
        }

        //Read the response body
      String body=EntityUtils.toString(response.getEntity());
        System.out.println("body:"+body);
    }
    @Test
    public void delMenu() throws IOException {
        HttpGet httpget = new HttpGet(delMenu);
        HttpResponse response = null;
        response = httpclient.execute(httpget);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
        {
            String strResult = EntityUtils.toString(response.getEntity());
            JSONObject obj = JSON.parseObject(strResult);
            System.out.println(obj);

        }
    }
    public String getJson(){
        AddMenuButton button =new AddMenuButton();
        ArrayList<AddMenuButton> destList=new ArrayList<AddMenuButton>();

        ArrayList<AddMenuButton> subButtonList0=new ArrayList<AddMenuButton>();
        AddMenuButton button1 =new AddMenuButton();
        AddMenuButton m0sub1 =new AddMenuButton();
        m0sub1.setType("click");
        m0sub1.setName("新闻速递");
        m0sub1.setKey("newsInfo");
        subButtonList0.add(m0sub1);
        AddMenuButton m0sub2 =new AddMenuButton();
        m0sub2.setType("click");
        m0sub2.setName("通知公告");
        m0sub2.setKey("notification");
        subButtonList0.add(m0sub2);
        AddMenuButton m0sub3 =new AddMenuButton();
        m0sub3.setType("click");
        m0sub3.setName("咨询热点");
        m0sub3.setKey("consult");
        subButtonList0.add(m0sub3);
        button1.setName("地税新闻");
        button1.setSub_button(subButtonList0);
        destList.add(button1);
        ArrayList<AddMenuButton> subButtonList1=new ArrayList<AddMenuButton>();
        AddMenuButton sub2 =new AddMenuButton();
        sub2.setType("click");
        sub2.setName("业务查询");
        sub2.setKey("businessSearch");
        subButtonList1.add(sub2);
        AddMenuButton sub1 =new AddMenuButton();
        sub1.setType("click");
        sub1.setName("业务办理");
        sub1.setKey("businessManage");
        subButtonList1.add(sub1);
        AddMenuButton button2=new AddMenuButton();
        button2.setName("涉税业务");
        button2.setSub_button(subButtonList1);
        destList.add(button2);
        //3
  /*      ArrayList<AddMenuButton> subButtonList2=new ArrayList<AddMenuButton>();
        AddMenuButton m2sub1 =new AddMenuButton();
        m2sub1.setType("click");
        m2sub1.setName("礼品兑换");
        m2sub1.setKey("prizeExchange");
        subButtonList2.add(m2sub1);
        AddMenuButton m2sub2 =new AddMenuButton();
        m2sub2.setType("click");
        m2sub2.setName("会员绑定");
        m2sub2.setKey("customerBind");
        subButtonList2.add(m2sub2);
        AddMenuButton button3=new AddMenuButton();
        button3.setName("会员");
        button3.setSub_button(subButtonList2);
        destList.add(button3);*/
        ArrayList<AddMenuButton> subButtonList2=new ArrayList<AddMenuButton>();
        AddMenuButton m2sub2 =new AddMenuButton();
        m2sub2.setType("view");
        m2sub2.setName("绑定微信");
        String redirect= URLEncoder.encode("http://45.33.54.201/zyh-wechat/customer/toBind.do");
        redirect=wechatRedirect.replace("${REDIRECT_URI}",redirect) ;
        m2sub2.setUrl(redirect);
        subButtonList2.add(m2sub2);
        AddMenuButton m2sub1 =new AddMenuButton();
        m2sub1.setType("view");
        m2sub1.setName("使用帮助");
        String redirect2= URLEncoder.encode("http://45.33.54.201/zyh-wechat/help/toHelp.do");
        redirect2=wechatRedirect.replace("${REDIRECT_URI}",redirect2) ;
        m2sub1.setUrl(redirect2);
        subButtonList2.add(m2sub1);
        AddMenuButton button3=new AddMenuButton();
        button3.setName("更多服务");
        button3.setSub_button(subButtonList2);
        destList.add(button3);
        Map jsonMap=new HashMap();
        jsonMap.put("button",destList);
        String json = JSONObject.toJSONString(jsonMap) ;
        System.out.println(json);
        return json;




    }
    @Test
    public void testJson(){
        AddMenuButton button =new AddMenuButton();
        ArrayList<AddMenuButton> destList=new ArrayList<AddMenuButton>();
        AddMenuButton button1 =new AddMenuButton();
        button1.setType("view");
        button1.setName("对呀投票");
        button1.setUrl("http://www.baidu.com/");
        destList.add(button1);
        ArrayList<AddMenuButton> subButtonList1=new ArrayList<AddMenuButton>();
        AddMenuButton sub1 =new AddMenuButton();
        sub1.setType("click");
        sub1.setName("历史调查");
        sub1.setKey("historyResearch");
        subButtonList1.add(sub1);
        AddMenuButton sub2 =new AddMenuButton();
        sub2.setType("click");
        sub2.setName("立即参与");
        sub2.setKey("acceptJoinin");
        subButtonList1.add(sub2);
        AddMenuButton button2=new AddMenuButton();
        button2.setName("e声调查");
        button2.setSub_button(subButtonList1);
        destList.add(button2);
        //3
        ArrayList<AddMenuButton> subButtonList2=new ArrayList<AddMenuButton>();
        AddMenuButton m2sub1 =new AddMenuButton();
        m2sub1.setType("view");
        m2sub1.setName("礼品兑换");
        m2sub1.setUrl("http://www.baidu.com/");
        subButtonList2.add(m2sub1);
        AddMenuButton m2sub2 =new AddMenuButton();
        m2sub2.setType("view");
        m2sub2.setName("会员绑定2");
        String redirect= URLEncoder.encode("http://yuncia.oicp.net/zhuyh/wechatBind/toBind.do");
        redirect=wechatRedirect.replace("${REDIRECT_URI}",redirect) ;
        m2sub2.setUrl(redirect);
        subButtonList2.add(m2sub2);
        AddMenuButton button3=new AddMenuButton();
        button3.setName("礼品兑换");
        button3.setSub_button(subButtonList2);
        destList.add(button3);
        Map jsonMap=new HashMap();
        jsonMap.put("button",destList);
        String json = JSONObject.toJSONString(jsonMap) ;
        System.out.println(json);




    }
}
