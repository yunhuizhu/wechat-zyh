package com.yuncia.wechat.service.wap;

import com.yuncia.wechat.pojo.wap.CustomerOpenid;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-4-29
 * Time: 上午11:20
 * To change this template use File | Settings | File Templates.
 */
public interface CustomerService {
    /**
     * 邮箱或者手机号必须有一个，否则返回false。false表示验证不通过
     *
     * @param email
     * @param phone
     * @param password
     * @return
     */
    public boolean checkUser(String email, String phone, String password);

    /**
     * 绑定微信号，email,phone其中必须输入一个。不能两个都不输入.
     *
     * @param email
     * @param phone
     * @param openId
     * @return
     */
    public boolean bindWechat(String email, String phone, String openId);

}
