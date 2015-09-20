package com.yuncia.wechat.util;

import com.yuncia.wechat.pojo.wap.MzTaxNews;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-5-15
 * Time: 上午11:49
 * To change this template use File | Settings | File Templates.
 */
public class JsoupUtil {
    public static List<MzTaxNews> toNewsInfo(String htmlUrl) throws IOException {
        List<MzTaxNews> mzTaxNewses=new ArrayList<MzTaxNews>();
        Document doc = Jsoup.connect(htmlUrl).timeout(5000).get();
        Element element= doc.getElementById("DIV1");
        if(element!=null){
            Document doc2=Jsoup.parse(element.html());
            List<Element> elements=doc2.select("tr").subList(0,4);
            for(Element e1:elements){
                MzTaxNews mzTaxNews=new MzTaxNews();
                Element aHrefE=e1.select("a[href~=/mz.*]").get(0);
//                System.out.println("aHrefE.html="+aHrefE.html());
//                System.out.println("aHrefE.title="+aHrefE.attr("title"));
//                System.out.println("aHrefE.href="+aHrefE.attr("href"));
                Element timeE=e1.select("td").last();
                System.out.println("timeE.html="+timeE.html());
                if(aHrefE!=null){
                   mzTaxNews.setNewtitle(aHrefE.attr("title"));
                   mzTaxNews.setNewbody(aHrefE.attr("href"));
                }
                if(timeE!=null){
                    mzTaxNews.setNewtime(timeE.text());
                }
                mzTaxNewses.add(mzTaxNews);
            }
        }
        return  mzTaxNewses;
    }
    public static void main(String[] args){
        try {
            JsoupUtil.toNewsInfo("http://mz.123662.gov.cn/portal/fzgb/mz/L2S0JUYPDFHXPNDXMLSTBNHT5291YUW8.htm") ;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
