package cn.fishmaple.psMonitor.webCrawler.task;

import cn.fishmaple.psMonitor.webCrawler.baidu.BaiduWebCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BaiduCrawlerTask {

    @Autowired
    BaiduWebCrawler baiduWebCrawler;
    @Scheduled(cron = "0/20 * * * * *")
      public void scheduled() throws Exception {
        baiduWebCrawler.tophot();
    }
}
