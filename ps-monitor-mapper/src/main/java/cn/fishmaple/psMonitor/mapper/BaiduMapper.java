package cn.fishmaple.psMonitor.mapper;

import cn.fishmaple.psMonitor.mapper.object.WebCrawler;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface BaiduMapper {
    @Insert("insert into webCrawler (timeline,json,origin) values (#{timeline},#{json},2)")
    public void insertOne(WebCrawler webCrawler);
    @Select("select json from webCrawler where origin=2 order by id desc limit 1")
    public String getLast();
}
