package com.yuncia.wechat.service.job;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.yuncia.wechat.service.client.sender.WeChatMsgSender;

/**
 * 用一句话描述该类的作用
 *
 * @author MonKong
 * @date 2014年12月18日
 */
public class TestJob {
    // private WeChatMsgSender sender;
    private Logger log = LogManager.getLogger();

    public void execute2() {
        try {
            // System.out.println("信息发送结果："+sender.sendPost(new
            // TextMsgReqJSON("hah呵呵~~！","")));
            log.entry(); // trace级别的信息，单独列出来是希望你在某个方法或者程序逻辑开始的时候调用，和logger.trace("entry")基本一个意思
            log.debug("我是debug信息");
            log.info("我是info信息"); // info级别的信息
            log.warn("我是warn信息");
            log.error("Did it again!"); // error级别的信息，参数就是你输出的信息
            log.fatal("我是fatal信息");
            log.exit(); // 和entry()对应的结束方法，和logger.trace("exit");一个意思
            System.out.println("哈哈呵呵！@");
            System.err.println("哈哈呵呵！@");
        } catch (Exception e) {
            log.catching(e);
        }
    }

}
