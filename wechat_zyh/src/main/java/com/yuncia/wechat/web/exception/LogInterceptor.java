package com.yuncia.wechat.web.exception;

/**
 * Created by zhu on 2015/6/17.
 */
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

/**
 * Spring 统一日志处理实现类
 * @author Andy Chan
 *
 */
public class LogInterceptor implements MethodInterceptor
{

    public Object invoke(MethodInvocation invocation) throws Throwable
    {
        Logger loger = Logger.getLogger(invocation.getClass());

        loger.info(invocation.getMethod() + ":BEGIN!");// 方法前的操作
        Object obj = invocation.proceed();// 执行需要Log的方法
        loger.info(invocation.getMethod() + ":END!");// 方法后的操作

        return obj;
    }

}
