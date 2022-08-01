package Annotation;

import org.testng.annotations.Test;

public class AnnotationDemo {
    @MyAnnotation("Hello")
    @MyAnnotation("World")
    @Test
    public void test(){}
}
