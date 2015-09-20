package com.yuncia.wechat.service.client.event.impl.event;

import com.yuncia.wechat.pojo.wechat.MessageType;
import com.yuncia.wechat.pojo.wechat.xml.event.ClickEventReqXML;
import com.yuncia.wechat.pojo.wechat.xml.msg.TextMsgRespXML;
import com.yuncia.wechat.pojo.wechat.xml.respMsg.NewsItem;
import com.yuncia.wechat.pojo.wechat.xml.respMsg.NewsMsgResp;
import com.yuncia.wechat.service.client.event.WeChatService;
import com.yuncia.wechat.service.wap.CustomerService;
import com.yuncia.wechat.util.DataConverUtils;
import com.yuncia.wechat.util.OtherSystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-4-27
 * Time: 下午5:20
 * To change this template use File | Settings | File Templates.
 */
@Service
public class MenuClickEventServiceImpl extends
        WeChatService<ClickEventReqXML, TextMsgRespXML> {
    @Autowired
    private CustomerService customerService;

    @Override
    public String service(ClickEventReqXML req, TextMsgRespXML resp)
            throws Exception {
        String resultXml="";
        System.out.println("req.getEventKey():" + req.getEventKey());
        if (req.getEventKey().equals("newsInfo")) { //新闻速递
            resultXml=getNewsInfo(req,"newsInfo",1);
        } else if (req.getEventKey().equals("notification")) { //通知公告
            resultXml=getNewsInfo(req,"notification",5);
        } else if (req.getEventKey().equals("consult")) { //咨询热点
            resultXml=getNewsInfo(req,"consult",7);
        } else if (req.getEventKey().equals("businessSearch")) { //业务查询
            resultXml=getNewsInfo(req,"businessSearch",8);
        } else if (req.getEventKey().equals("businessManage")) { //业务办理
            resultXml=getNewsInfo(req,"businessManage",6);
        }
        return resultXml;
    }
    private String getNewsInfo(ClickEventReqXML req,String clickKey,Integer count) {
        String result="";
        String openid=req.getFromUserName();
        NewsMsgResp newsMsgResp=new NewsMsgResp();
        newsMsgResp.setFromUserName(req.getToUserName());
        newsMsgResp.setToUserName(req.getFromUserName());
        newsMsgResp.setCreateTime(new Date().getTime());
        newsMsgResp.setMsgType(MessageType.news);
        //图文消息信息
        List<NewsItem> items=new ArrayList<NewsItem>();
        for(int i=0;i<count;i++){
            NewsItem newsItem1=new NewsItem();
//        newsItem1.setDescription("通知公告");
            newsItem1.setPicUrl(OtherSystemUtil.getSysteomProperties().getProperty(clickKey+".item"+i+".picUrl"));
            newsItem1.setTitle(OtherSystemUtil.getSysteomProperties().getProperty(clickKey+".item"+i+".title"));
            newsItem1.setUrl(OtherSystemUtil.getSysteomProperties().getProperty(clickKey+".item"+i+".url").replace("${openid}",req.getToUserName()));
            items.add(newsItem1);
        }
        newsMsgResp.setArticles(items);
        newsMsgResp.setArticleCount(items.size());
        result=DataConverUtils.xstremObjectToXML(newsMsgResp,new NewsItem(),"item");
        return result;
    }



}
