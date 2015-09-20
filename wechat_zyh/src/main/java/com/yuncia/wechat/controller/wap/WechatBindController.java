package com.yuncia.wechat.controller.wap;

import com.yuncia.wechat.service.wap.CustomerService;
import com.yuncia.wechat.util.DataConverUtils;
import com.yuncia.wechat.util.Oauth2Utils;
import com.yuncia.wechat.util.OtherSystemUtil;
import com.yuncia.wechat.util.SessionUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-4-29
 * Time: 下午3:15
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/customer")
public class WechatBindController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/toBind")
    public ModelAndView toBind(String openid, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        //有可能从其他地方跳转到该链接
        if(StringUtils.isNotEmpty(openid)){
            SessionUtil.setSessionOpenId(request,openid);
        }else{
            openid=SessionUtil.getSessionOpenId(request);
        }
        mv.setViewName("/wap/customerbinding");
        mv.addObject("openid", openid);
        return mv;
    }

    @RequestMapping(value = "/wechatBinding")
    @ResponseBody
    public String bind(String email, String mobile, String password, String openId,HttpServletRequest req) {
        if(openId==null){
            openId=SessionUtil.getSessionOpenId(req);
        }
        Boolean result = true;
        String msg = "";
        //验证通过则绑定微信号
        if (customerService.checkUser(email, mobile, password)) {
           /* if(customerService.getBind(openId)==null) {
            if (!customerService.bindWechat(email, mobile, openId)) {
                result=false;
                msg = "绑定失败";
            }else{
                result=true;
                msg = "绑定成功";
            }
            }else{
                result=false;
                msg = "已经绑定微信号了，不能再绑定";
            }*/
        } else {
            result=false;
            msg = "用户名或密码错误";
        }
        Map tempMap=new HashMap();
        tempMap.put("success",result);
        tempMap.put("msg",msg);
        String tempResult="";
        tempResult=DataConverUtils.ObjectToJson(tempMap);
        System.out.println("tempResult:"+tempResult);
        return tempResult;
    }
    @RequestMapping(value = "/getCurrentUserInfo")
    @ResponseBody
    public String getCurrentUserInfo(HttpServletRequest req) {
        String uid=SessionUtil.getSessionCustomerID(req);
        String voteInfoAddr=SessionUtil.getSessionVoteInfoAddr(req)==null?"":SessionUtil.getSessionVoteInfoAddr(req);
        String voteCommentAddr=SessionUtil.getSessionVoteCommentAddr(req)==null?"":SessionUtil.getSessionVoteCommentAddr(req);
        Boolean result = true;
        String msg = "";
        result=true;
        msg="有会员id";
        if(StringUtils.isEmpty(uid)){
            uid="-1";//投票专用，匿名用户
        }
        Map tempMap=new HashMap();
        tempMap.put("success",result);
        tempMap.put("msg",msg);
        tempMap.put("uid",uid);
        tempMap.put("voteInfoAddr",voteInfoAddr);
        tempMap.put("voteCommentAddr",voteCommentAddr);
//        tempMap.put("picViewUrl", OtherSystemUtil.vote_picView_Url);
        String tempResult="";
        tempResult=DataConverUtils.ObjectToJson(tempMap);
        System.out.println("tempResult:"+tempResult);
        return tempResult;
    }
    @RequestMapping(value = "/setCurrentUserInfo")
    @ResponseBody
    public String setCurrentUserInfo(HttpServletRequest req,String voteInfoAddr,String voteCommentAddr) {
        Boolean result = true;
        String msg = "";
        result=true;
        msg="设置投票信息";
        if(StringUtils.isNotEmpty(voteInfoAddr)){
           SessionUtil.setSessionVoteInfoAddr(req,voteInfoAddr);
        }
        if(StringUtils.isNotEmpty(voteCommentAddr)){
            SessionUtil.setSessionVoteCommentAddr(req, voteCommentAddr);
        }
        Map tempMap=new HashMap();
        tempMap.put("success",result);
        tempMap.put("msg",msg);
        String tempResult="";
        tempResult=DataConverUtils.ObjectToJson(tempMap);
        return tempResult;
    }
}
