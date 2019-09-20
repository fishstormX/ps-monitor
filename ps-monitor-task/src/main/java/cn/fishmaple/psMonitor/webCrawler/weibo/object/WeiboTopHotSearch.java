package cn.fishmaple.psMonitor.webCrawler.weibo.object;

import cn.fishmaple.psMonitor.annotation.HtmlDataField;

import java.util.Objects;

/**
 * 微博热搜
 * */
public class WeiboTopHotSearch {


    @HtmlDataField(selector = ".td-02 a",ridPrefix = 1)
    private String subject;
    @HtmlDataField(selector = ".td-02 span")
    private String conut;
    @HtmlDataField(selector = ".td-01",ridPrefix = 1,ridSuffix = 1)
    private String rank;
    @HtmlDataField(selector = ".td-03",ridPrefix = 1)
    private String status;
    private String url;
    private String thirdMid;
    private Long on_boardTime;

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

    public String getConut() {
        return conut;
    }

    public void setConut(String conut) {
        this.conut = conut;
    }

    public String getThirdMid() {
        return thirdMid;
    }

    public void setThirdMid(String thirdMid) {
        this.thirdMid = thirdMid;
    }

    public Long getOn_boardTime() {
        return on_boardTime;
    }

    public void setOn_boardTime(Long on_boardTime) {
        this.on_boardTime = on_boardTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        WeiboTopHotSearch that = (WeiboTopHotSearch) o;
        if(that.status.equals("荐")){
            return true;
        }
        return Objects.equals(subject, that.subject) &&
                Objects.equals(conut, that.conut) &&
                Objects.equals(rank, that.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, conut, rank);
    }
}
