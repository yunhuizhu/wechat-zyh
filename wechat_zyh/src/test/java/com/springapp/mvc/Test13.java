package com.springapp.mvc;

import java.net.URLDecoder;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-5-29
 * Time: 下午10:50
 * To change this template use File | Settings | File Templates.
 */
public class Test13 {
    public static void main(String[] args) {
        String s="http%3A%2F%2Ffilm.qq.com%2Fweixin%2Ftrialvip.html%3Fuin%3D%26aid%3Dhlw.wxpay.web.flash%26ptag%3Dhlw.wxpay.web.flash";
        System.out.println(URLDecoder.decode(s));
    }
}
