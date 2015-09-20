package com.springapp.mvc;

import com.yuncia.wechat.jms.produser.Produser440000;
import com.yuncia.wechat.service.wap.CustomerService;
import com.yuncia.wechat.util.DataConverUtils;
import com.yuncia.wechat.util.OtherSystemInvoke;
import com.yuncia.wechat.util.OtherSystemUtil;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-4-28
 * Time: 上午10:52
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class BizentityVipTest extends BaseServiceTest{

    @Autowired
    private CustomerService customerService;
    @Autowired
    private Produser440000 produser440000;
    @Autowired
    private ActiveMQQueue sessionAwareQueue440000;

    @Test
    public void testBiz(){
//        boolean result=customerService.checkUser("chengwei_lv@yahoo.com.cn","","23456");
//        System.out.println("结果："+result) ;
          produser440000.sendMessage(sessionAwareQueue440000,"测试1231");
    }

    @Test
    public void testHttp(){
        Map<String,Object> params=new HashMap();
        params.put("receiver","zhuyh");
        params.put("phone","15815814646");
        params.put("address","广州 天河12312__121!!!%%");
        params.put("provinceId","125");
        params.put("cityId","1453");
        params.put("id","342");
        params.put("uid","269945");
        params.put("isWechat","true");
        OtherSystemInvoke.exchangeGift(params);
    }
    @Test
    public void testUtil(){
        //System.out.println("OtherSystemUtil.codeToOpenIdUrl:"+OtherSystemUtil.codeToOpenIdUrl);
        System.out.println("OtherSystemUtil.codeToOpenIdUrl:"+OtherSystemUtil.codeToOpenIdUrl);
        Map<String,String > resultMap = new HashMap();
        resultMap.put("sucess","true");
        resultMap.put("msg","http://localhost:8080/");
        String json= DataConverUtils.ObjectToJson(resultMap);
        System.out.println("json:"+json);
    }
    @Test
    public void testTest() throws UnsupportedEncodingException {
        //System.out.println("OtherSystemUtil.codeToOpenIdUrl:"+OtherSystemUtil.codeToOpenIdUrl);
        /*Properties props=System.getProperties();
        Iterator iter=props.keySet().iterator();
        while(iter.hasNext()){
            String key=(String)iter.next();
            System.out.println(key+" = "+ props.get(key));
        }*/
        String s1="中国人1232";
        String gbkS1= new String(s1.getBytes(),"GBK");
        String utf8S1= new String(s1.getBytes(),"UTF-8");
        System.out.println("gbk:"+gbkS1);
        System.out.println("utf8S1:"+gbkS1);
        System.out.println("String csn = Charset.defaultCharset().name();:"+ Charset.defaultCharset().name());
        System.out.println("System.getProperty(\"file.encoding\")"+ System.getProperty("file.encoding"));
    }

}
