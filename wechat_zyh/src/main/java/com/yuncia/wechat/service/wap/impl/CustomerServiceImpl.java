package com.yuncia.wechat.service.wap.impl;

import com.yuncia.wechat.dao.CustomerOpenidMapper;
import com.yuncia.wechat.pojo.wap.CustomerOpenid;
import com.yuncia.wechat.pojo.wap.CustomerOpenidExample;
import com.yuncia.wechat.service.wap.CustomerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-4-29
 * Time: 上午11:22
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
//    @Autowired
//    private CustomerMapper customerMapper;
    @Autowired
    private CustomerOpenidMapper customerOpenidMapper;

    @Override
    public boolean checkUser(String email, String phone, String password) {
        boolean success = false;
//        password= MD5.compute(password); //md5转码
        if (StringUtils.isNotEmpty(email) || StringUtils.isNotEmpty(phone)) {
            /*List<Customer> customers = customerMapper.getCustomerByLogin(email, phone, password);
            if (customers != null && customers.size() > 0) {
                success = true;
            }*/
        }
        return success;
    }

    @Override
    public boolean bindWechat(String email, String phone, String openId) {
        boolean success = false;
        if (StringUtils.isEmpty(email) && StringUtils.isEmpty(phone)) {
            return success;
        }
        //查询是否对呀会员
       /* CustomerExample example1 = new CustomerExample();
        CustomerExample.Criteria criteria = example1.createCriteria();
        if (StringUtils.isNotEmpty(email)) {
            criteria.andEmailEqualTo(email);
        }
        if (StringUtils.isNotEmpty(phone)) {
            criteria.andPhoneNumEqualTo(phone);
        }
        List<Customer> customers = customerMapper.selectByExample(example1);
        //不是会员,不往下执行了
        if (customers != null && customers.size() > 0) {
            Customer customer = customers.get(0);
            //不是对呀会员更新为对呀会员
            if (!customer.getIsDuiya().equals("1")) {
                Customer customer1 = new Customer();
                customer1.setIsDuiya(1);
                customer1.setCustomerId(customer.getCustomerId());
                customerMapper.updateByPrimaryKeySelective(customer1);
            }
            //绑定微信号
            CustomerOpenid customerOpenid = new CustomerOpenid();
            customerOpenid.setCustomerid(customer.getCustomerId());
            customerOpenid.setOpenid(openId);
            if (customerOpenidMapper.insert(customerOpenid) > 0) {
                success = true;
            } else {
                success = false;
            }
        } else {
            return success;
        }*/
        return success;
    }

}
