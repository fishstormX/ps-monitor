# ps-monitor
抓取百度、新浪热点数据的爬虫,尚在完善中

目前自带有新浪和百度的热搜爬虫，爬取静态页面的元数据，暂时没做处理

使用爬虫的方法

1. 定义一个爬取对象，使用@HtmlDataField 定义爬取的字段，其中的字段
   - selector：String 代表jsoup的css选择器，目前仅支持此种方式的标签内容选取
   - attr：String 不定义此字段为取标签的text()，否则是取定义的标签属性值
   - ridPrefix：int 跳过符合selector的开始几个标签
   - ridSuffix：int 跳过符合selector的最后几个标签
   - goalCount： int 爬取指定数目的数据，优先级高于ridSuffix
   - blankIgnore：boolean 标签跳过策略，true则表示跳过标签不留空，false则占位
2. （可选）使用一个或多个@TagMatched 定义标签过滤规则，其中的字段
   - priority：int 保留字段，目前没有启用
   - matchValue：要匹配的正则表达式
   - attr：String 标签匹配属性，默认是取标签的text
   - blacklist：boolean 黑白名单，true则表示匹配黑名单，符合正则的标签跳过，false则是只取符合正则的标签  
 @TagMatched的跳过策略与@HtmlDataField保持一致
3. 获取到想要解析的html片段，推荐使用restTemplate：
    ```java
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.getForObject(url,String.class);
   ```
4. 选取一个初始变量，使用WebCrawlerUtil转换爬取对象：
    ```java
       List<BaiduTopHotSearch> list= WebCrawlerUtil.TransFormByParser(hotTopic, BaiduTopHotSearch.class,"rank");
    ```
    
 ### 示例   
例如：爬取符合表达式的前10条含有“神仙”的百度搜索热点数据，定义爬取对象：
```java
public class BaiduTopHotSearch {
    @HtmlDataField(selector = ".keyword .list-title",ridPrefix = 0,goalCount= 10)
    @TagMatched(matchValue=".*神仙.*",attr="title")
    private String subject;
    @HtmlDataField(selector = ".last .icon-rise,.last .icon-fall")
    private String count;
    @HtmlDataField(selector = ".first .num-top,.first .num-normal",ridPrefix = 0)
    private String rank;
    private String status;
    @HtmlDataField(selector = ".keyword .list-title",ridPrefix = 0)
    private String url;
    private String thirdMid;
}
```
