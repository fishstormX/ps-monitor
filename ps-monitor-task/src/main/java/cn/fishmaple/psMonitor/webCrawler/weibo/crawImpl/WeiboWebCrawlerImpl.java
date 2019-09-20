package cn.fishmaple.psMonitor.webCrawler.weibo.crawImpl;

import cn.fishmaple.psMonitor.mapper.WeiboMapper;
import cn.fishmaple.psMonitor.mapper.object.WebCrawler;
import cn.fishmaple.psMonitor.util.HttpExchangeUtil;
import cn.fishmaple.psMonitor.util.WebCrawlerUtil;
import cn.fishmaple.psMonitor.webCrawler.weibo.WeiboWebCrawler;
import cn.fishmaple.psMonitor.webCrawler.weibo.object.WeiboTopHotSearch;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class WeiboWebCrawlerImpl implements WeiboWebCrawler {
    @Autowired
    WeiboMapper weiboMapper;
    @Override
    public void tophot() throws Exception {
        String hotTopic = HttpExchangeUtil.getHtml("https://s.weibo.com/top/summary?cate=realtimehot","utf-8");
        List<WeiboTopHotSearch> list= WebCrawlerUtil.TransFormByParser(hotTopic, WeiboTopHotSearch.class,"rank");
        WebCrawler webCrawler =new WebCrawler();
        webCrawler.setJson(JSON.toJSONString(list));
        webCrawler.setTimeline(System.currentTimeMillis());
        List<WeiboTopHotSearch> list1 = JSON.parseArray(weiboMapper.getLast()).toJavaList(WeiboTopHotSearch.class);
        if(list.get(0).equals(list1.get(0))){
            System.out.println("重复w");
        }else {
            System.out.println("翻新的w");
            weiboMapper.insertOne(webCrawler);
        }
    }

    public static void main(String args[]) throws Exception {
        String hotTopic = HttpExchangeUtil.getHtml("https://s.weibo.com/top/summary?cate=realtimehot","utf-8");
        List<WeiboTopHotSearch> list= WebCrawlerUtil.TransFormByParser(hotTopic, WeiboTopHotSearch.class,"rank");

    }
}
