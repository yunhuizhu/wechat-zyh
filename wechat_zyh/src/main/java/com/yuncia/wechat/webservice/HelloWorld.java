package com.yuncia.wechat.webservice;

import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by zhu on 2015/6/25.
 */
@WebService
public interface HelloWorld {
    public String sayHello(String text);
}
