package cn.fishmaple.psMonitor.util;

import cn.fishmaple.psMonitor.annotation.HtmlDataField;
import cn.fishmaple.psMonitor.annotation.TagMatched;
import cn.fishmaple.psMonitor.annotation.TagMatches;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class WebCrawlerUtil {
    public static String[] cutStringByElement(String htmlContent,String... tagParsers){
        Document document = Jsoup.parse(htmlContent);
        String[] result = new String[tagParsers.length];
        for(int i=0 ;i<tagParsers.length ; i++){
            Elements elements = document.select(tagParsers[i]);
            result[i] = elements.text();
        }
        return result;
    }

    public static List TransFormByParser(String htmlContent,Class goalClass,String montorField) throws Exception {
        Document document = Jsoup.parse(htmlContent);
        Field[] fields = goalClass.getDeclaredFields();
        List list = new ArrayList<>();

        try {
            Field mainField = goalClass.getDeclaredField(montorField);
            HtmlDataField htmlMainDataField = mainField.getAnnotation(HtmlDataField.class);
            TagMatched[] tagMatches = mainField.getAnnotationsByType(TagMatched.class);
            //先获取main整合
            buildList(list, document, htmlMainDataField,mainField,goalClass);
            for(Field field:fields){
                HtmlDataField htmlDataField = field.getAnnotation(HtmlDataField.class);
                if(htmlDataField == null){
                    break;
                }
                buildList(list, document, htmlDataField,field,goalClass);
            }
        } catch (NoSuchFieldException e) {
            throw new Exception("请确认montorField正确");
        }
        return list;


    }



    private static void buildList(List list,Document htmlDocument,HtmlDataField htmlDataField,Field field,Class goalClass){
        Elements elements = htmlDocument.select(htmlDataField.selector());
        int prefixCodeCount = htmlDataField.ridPrefix();
        int suffixCodeGoal = elements.size()-htmlDataField.ridSuffix();
        for(int i =0;i<elements.size();i++){
            if(prefixCodeCount>0){
                prefixCodeCount--;
                continue;
            }else if(i>=suffixCodeGoal){
                break;
            }
            String text;
            if(htmlDataField.attr().equals("")){
                text = elements.get(i).text();
            }else{
                text = elements.get(i).attr(htmlDataField.attr());
            }
            Object obj = null;
            try {
                obj = goalClass.newInstance();
                ObjectReflectUtil.setParam(obj,field,text);
                if(list.isEmpty()){
                    list.add(obj);
                }else{
                    ObjectReflectUtil.setParam(list.get(i-htmlDataField.ridPrefix()),field,text);
                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String args[]){
        String s = HttpExchangeUtil.getHtml("https://s.weibo.com/top/summary?cate=realtimehot","utf-8");

    }
}
