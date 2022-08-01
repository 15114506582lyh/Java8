package Annotation;

import java.lang.annotation.Repeatable;

@Repeatable(MyAnnotations.class)
public @interface MyAnnotation {
    String value() default "李宇航";
}
