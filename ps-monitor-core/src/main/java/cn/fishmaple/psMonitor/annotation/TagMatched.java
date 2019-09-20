package cn.fishmaple.psMonitor.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TagMatched {
    //保留字段
    public int priority() default 0;
    public String matchValue() default "";
    public String attr() default "";
    public boolean blacklist() default false;
}

