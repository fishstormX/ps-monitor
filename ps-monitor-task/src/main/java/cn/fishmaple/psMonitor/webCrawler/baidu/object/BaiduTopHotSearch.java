package cn.fishmaple.psMonitor.webCrawler.baidu.object;

import cn.fishmaple.psMonitor.annotation.HtmlDataField;

import java.util.Objects;

/**
 * 微博热搜
 * */
public class BaiduTopHotSearch {

    @HtmlDataField(selector = ".keyword .list-title",ridPrefix = 0)
    private String subject;
    @HtmlDataField(selector = ".last .icon-rise,.last .icon-fall")
    private String count;
    @HtmlDataField(selector = ".first .num-top,.first .num-normal",ridPrefix = 0)
    private String rank;
    private String status;
    @HtmlDataField(selector = ".keyword .list-title",ridPrefix = 0)
    private String url;
    private String thirdMid;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getThirdMid() {
        return thirdMid;
    }

    public void setThirdMid(String thirdMid) {
        this.thirdMid = thirdMid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        BaiduTopHotSearch that = (BaiduTopHotSearch) o;
        return subject.equals(that.subject) &&
                count.equals(that.count) &&
                rank.equals(that.rank) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, count, rank, url);
    }
}
