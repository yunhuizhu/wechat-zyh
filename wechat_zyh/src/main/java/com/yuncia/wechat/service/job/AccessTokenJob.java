package com.yuncia.wechat.service.job;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.yuncia.wechat.pojo.wechat.json.msg.AccessTokenRespJSON;
import com.yuncia.wechat.sys.SysConstant;
import com.yuncia.wechat.util.DataConverUtils;
import com.yuncia.wechat.util.HttpsUtils;
import com.yuncia.wechat.util.UrlGenerator;

/**
 * 定时更新accesstoken
 *
 * @author MonKong
 * @date 2014年12月16日
 */
public class AccessTokenJob extends QuartzJobBean {
    private static final Logger log = LogManager.getLogger();

    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        log.info("开始获取微信AccessToken......");
        // 发起请求
        String respJson = null;
        try {
            respJson = HttpsUtils.doGet(UrlGenerator.createAccessTokenUrl() + "a",
                    null,true);
            log.debug(respJson);
        } catch (Exception e) {
            errorHandler("调用Http请求失败！", e, context);
        }
        // 解析返回数据
        AccessTokenRespJSON obj = DataConverUtils.JsonToObject(respJson,
                AccessTokenRespJSON.class);
        // 判断异常
        String accessToken = obj.getAccessToken();
        if (accessToken != null && !"".equals(obj.getAccessToken())) {
            // 更新全局accessToken
            SysConstant.globalAccessToken = accessToken;
            log.info("微信AccessToken获取成功.accessToken为："+accessToken);
        } else {
            errorHandler("返回信息异常：" + respJson + "|封装对象：" + obj, null, context);
        }
    }

    private void errorHandler(String msg, Exception e,
                              JobExecutionContext context) throws JobExecutionException {
        int count = context.getRefireCount();
        if (count < 3) {
            log.error("微信AccessToken获取【失败】!正在尝试第{}次重试... 错误信息：{} ", count + 1, msg);
            throw log.throwing(new JobExecutionException(e == null ? new JobExecutionException()
                    : e, true));
        } else {
            log.fatal("微信AccessToken获取【多次失败】!!!!！{}", msg);
            throw log.throwing(Level.FATAL, new JobExecutionException(e == null ? new Exception()
                    : e, false));
        }
    }
}
