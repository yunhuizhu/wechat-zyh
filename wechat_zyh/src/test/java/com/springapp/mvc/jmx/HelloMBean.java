package com.springapp.mvc.jmx;

/**
 * Created by zhu on 2015/8/4.
 */
public interface HelloMBean {
    public String getName();
    public void setName(String name);
    public void printHello();
    public void printHello(String whoName);
}
