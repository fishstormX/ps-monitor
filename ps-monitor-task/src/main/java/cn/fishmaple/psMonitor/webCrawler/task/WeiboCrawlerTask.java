package cn.fishmaple.psMonitor.webCrawler.task;

import cn.fishmaple.psMonitor.webCrawler.baidu.BaiduWebCrawler;
import cn.fishmaple.psMonitor.webCrawler.weibo.WeiboWebCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeiboCrawlerTask {

    @Autowired
    WeiboWebCrawler weiboWebCrawler;
    @Scheduled(cron = "10/20 * * * * *")
    public void scheduled() throws Exception {
        weiboWebCrawler.tophot();
    }
}
