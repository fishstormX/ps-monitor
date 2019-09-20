package cn.fishmaple.psMonitor.annotation;


import java.lang.annotation.*;

import static java.lang.annotation.ElementType.METHOD;

@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TagMatches {
    TagMatched[] value();
}
