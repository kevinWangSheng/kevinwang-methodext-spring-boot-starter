package cn.kevinwang.methodext.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wang
 * @create 2024-01-18-18:57
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DoMethodext {
    String method() default "";
    String returnJson() default "";
}
