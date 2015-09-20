package com.yuncia.wechat.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuncia.wechat.sys.SysConstant;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {
    private static final Logger log = LogManager.getLogger();

    @RequestMapping("/test2")
    @ResponseBody
    public String test2() {
        System.out.println("TestController.test2()");
        return "<xml>\n" +
                "  <toUserName>oJWuvuGDR-H9Fjv7Lnw7zJuJRffg</toUserName>\n" +
                "  <fromUserName>gh_3a31de5e242c</fromUserName>\n" +
                "  <createTime>12123</createTime>\n" +
                "  <msgType>news</msgType>\n" +
                "  <articleCount>1</articleCount>\n" +
                "  <articles>\n" +
                "    <item>\n" +
                "      <title>百度标题</title>\n" +
                "      <description>百度描述</description>\n" +
                "      <picUrl>http://st.123662.gov.cn/skin/style/fzgb/mz/images-mz-01/style-piclist-02/ico01.jpg</picUrl>\n" +
                "      <url>http://www.baidu.com</url>\n" +
                "    </item>\n" +
                "  </articles>\n" +
                "</xml>";
    }

    @RequestMapping("/test1")
    public String test1(ModelMap model2, String code, String str) throws Exception {
        System.out.println("TestController.test11()");
        try {
            if (code != null) {
                System.out.println("原：" + code);
                System.out.println(new String(code.getBytes("ISO-8859-1"),
                        SysConstant.CHARSET));
            }
            throw new Exception("23123123");
        } catch (UnsupportedEncodingException e) {
            log.catching(e);
        }
        return "/index.jsp";
    }

    @RequestMapping("/test3")
    public String test3(ModelMap model2, String code, String str) throws Exception {
        System.out.println("TestController.test13()");

//        bizentityVipListService.updateByPrimaryKey("1");
//        bizentityVipList=bizentityVipListService.selectByPrimaryKey("1");
//
//        System.out.println("bizentityVipList : "+ bizentityVipList.getBizVIPListID());
        return "/index.jsp";
    }

   /* @RequestMapping(value = "/listBizentityVip.do", method = RequestMethod.GET)
    public
    @ResponseBody
    String listBizentityVip(HttpServletRequest request, Bizentityviplist bizentityVipList,String pageSize, String pageNumber) {
        int pageSize2 = 10;   //每页数据量
        int pageNumber2 = 1;  //页码
        int dispalyStart = 0; //每页的开始序号
        PageMyBatis<Bizentityviplist> pageMyBatis = null;
        Map<String, Object> jsonMap = new HashMap<String, Object>();

        if (StringUtils.isEmpty(pageSize)) {
            pageSize2 = Integer.parseInt(pageSize);
        }
        if (!StringUtils.isEmpty(pageNumber)) {
            pageNumber2 = Integer.parseInt(pageNumber);
        }
        dispalyStart = (pageNumber2 - 1) == 0 ? 0 : ((pageNumber2 - 1) * pageSize2);
        PagingCriteria pagingCriteria = PagingCriteria.createCriteria(pageSize2, dispalyStart, pageNumber2);
//        pageMyBatis = bizentityVipListService.listByPageNoBizentity(pagingCriteria, bizentityVipList);
        jsonMap.put("total", pageMyBatis.getTotal());
        jsonMap.put("rows", pageMyBatis);
        String json= JSONObject.fromObject(jsonMap).toString();
        return json;
    }*/

}
