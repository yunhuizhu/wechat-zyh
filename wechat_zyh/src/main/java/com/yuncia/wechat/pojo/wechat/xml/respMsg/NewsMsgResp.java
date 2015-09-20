package com.yuncia.wechat.pojo.wechat.xml.respMsg;

import com.yuncia.wechat.pojo.wechat.MessageType;
import com.yuncia.wechat.pojo.wechat.xml.BaseResp;
import com.yuncia.wechat.pojo.wechat.xml.WeChatXML;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-5-12
 * Time: 上午11:53
 * To change this template use File | Settings | File Templates.
 */
public class NewsMsgResp extends BaseResp {
    @XStreamAlias("ArticleCount")
    private Integer articleCount;
    @XStreamAlias("Articles")
    private List<NewsItem> articles;



    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    public List<NewsItem> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsItem> articles) {
        this.articles = articles;
    }
}
