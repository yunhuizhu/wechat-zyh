package com.yuncia.wechat.pojo.wechat.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * 微信接口的规律：
 * 微信的事件请求和服务器的被动响应都是XML格式。
 * 服务端主动调用微信接口和微信响应都是JSON格式。
 *
 * @author MonKong
 * @Description 微信接口XMLPOJO的基类, 拥有公共配置。微信JSON属性名称大部分是全小写，有部分有下划线分割，注意阅读开发文档
 * @date 2014年12月11日
 */
@JsonNaming(PropertyNamingStrategy.LowerCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class WeChatJSON {

}
