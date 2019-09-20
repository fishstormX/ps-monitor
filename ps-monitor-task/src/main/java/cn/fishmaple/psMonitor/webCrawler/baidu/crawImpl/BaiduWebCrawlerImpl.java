package cn.fishmaple.psMonitor.webCrawler.baidu.crawImpl;

import cn.fishmaple.psMonitor.mapper.BaiduMapper;
import cn.fishmaple.psMonitor.mapper.object.WebCrawler;
import cn.fishmaple.psMonitor.util.HttpExchangeUtil;
import cn.fishmaple.psMonitor.util.WebCrawlerUtil;
import cn.fishmaple.psMonitor.webCrawler.baidu.BaiduWebCrawler;
import cn.fishmaple.psMonitor.webCrawler.baidu.object.BaiduTopHotSearch;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BaiduWebCrawlerImpl implements BaiduWebCrawler {

    @Autowired
    BaiduMapper baiduMapper;

    @Override
    public void tophot() throws Exception {
        String hotTopic = HttpExchangeUtil.getHtml("http://top.baidu.com/buzz?b=1","GBK");
        List<BaiduTopHotSearch> list= WebCrawlerUtil.TransFormByParser(hotTopic, BaiduTopHotSearch.class,"rank");
        WebCrawler webCrawler =new WebCrawler();
        webCrawler.setJson(JSON.toJSONString(list));
        webCrawler.setTimeline(System.currentTimeMillis());
        List<BaiduTopHotSearch> list1 = JSON.parseArray(baiduMapper.getLast()).toJavaList(BaiduTopHotSearch.class);
        if(list.equals(list1)){
            System.out.println("重复");
        }else {
            System.out.println("翻新的");
            baiduMapper.insertOne(webCrawler);
        }
    }

}
