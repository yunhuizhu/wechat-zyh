package com.yuncia.wechat.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by zhu on 2015/7/20.
 * 为了让普通类使用spring管理的注入类。
 *类似SensitiveWordMapper sensitiveWordMapper =
 * (SensitiveWordMapper) SpringContextUtil.getBean("sensitiveWordMapper");
 */
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext context;

    @SuppressWarnings("static-access")
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.context = applicationContext;
    }

    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
