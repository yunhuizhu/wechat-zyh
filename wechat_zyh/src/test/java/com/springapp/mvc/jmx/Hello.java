package com.springapp.mvc.jmx;

/**
 * Created by zhu on 2015/8/4.
 */
public class Hello implements HelloMBean{
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void printHello() {
        System.out.println("Hello World, " + name);
    }
    public void printHello(String whoName) {
        System.out.println("Hello , " + whoName);
    }
}
