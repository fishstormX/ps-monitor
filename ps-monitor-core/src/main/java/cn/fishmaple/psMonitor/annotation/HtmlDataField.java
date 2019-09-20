package cn.fishmaple.psMonitor.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.annotation.ElementType.FIELD;

/**
 * @author 鱼鱼
 */
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface HtmlDataField {

    public String selector() default "";
    //如选择器为空，则按此顺序注入属性
    //如果是标签属性，则为获取属性，默认为获取值
    public String attr() default "";
    //忽略前n个标签的值
    public int ridPrefix() default 0;
    //忽略后n个标签的值
    public int ridSuffix() default 0;
    public int goalCount() default 0;
    //被忽略/跳过标签占位，默认不占位
    public boolean blankIgnore() default false;

}
