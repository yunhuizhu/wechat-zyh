package com.yuncia.wechat.webservice.impl;


import com.yuncia.wechat.webservice.HelloWorld;
import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutInterceptors;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * Created by zhu on 2015/6/25.
 */
@WebService(endpointInterface = "com.yuncia.wechat.webservice.HelloWorld")
@InInterceptors(interceptors = "org.apache.cxf.interceptor.LoggingInInterceptor")
@OutInterceptors(interceptors = "org.apache.cxf.interceptor.LoggingOutInterceptor")
public class HelloWorldImpl implements HelloWorld {
    @Override
    public String sayHello(String text){
        System.out.println("sayHi called");
        return "Hello " + text;
    }

}
