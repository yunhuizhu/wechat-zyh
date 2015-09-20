/**
 *
 */
package com.yuncia.wechat.pojo.wechat.json.msg;

import com.yuncia.wechat.pojo.wechat.json.WeChatJSON;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author MonKong
 * @Description 主动调用微信接口的返回消息bean
 * @date 2014年12月9日
 */

public class ReturnJSON extends WeChatJSON {
    @JsonProperty("errcode")
    private Integer errCode;
    @JsonProperty("errmsg")
    private String errMsg;

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "ReturnJSON [errCode=" + errCode + ", errMsg=" + errMsg + "]";
    }
}
