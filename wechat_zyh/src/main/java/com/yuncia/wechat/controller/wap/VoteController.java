package com.yuncia.wechat.controller.wap;

import com.yuncia.wechat.pojo.wap.Customer;
import com.yuncia.wechat.service.wap.CustomerService;
import com.yuncia.wechat.util.SessionUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-5-5
 * Time: 上午10:05
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/vote")
public class VoteController {
    @Autowired
    private CustomerService customerService;
    @RequestMapping(value = "/toVote")
    public ModelAndView toVote(String exchangeSubmit,String openid,HttpServletRequest request) {
        if(StringUtils.isNotEmpty(openid)){
            SessionUtil.setSessionOpenId(request,openid);
        }else{
            openid=SessionUtil.getSessionOpenId(request);
        }
        ModelAndView mv = new ModelAndView();
       /* Customer customer=customerService.getBind(openid);
        if(customer!=null&&customer.getCustomerId()!=null){
            SessionUtil.setSessionCustomerID(request, customer.getCustomerId().toString());
        }*/
        mv.setViewName("/wap/votemain");
        return mv;
    }
}
