package Methods;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.testng.annotations.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

public class MethodsDemo {
    @Test
    public void test1(){
        //不使用方法引用时
        PrintStream ps1 = System.out;
        Consumer<String> con = (method) -> System.out.println(method);
        con.accept("我是李宇航");
        //使用方法引用时
        Consumer<String> con1 = ps1::println;
        con1.accept("我和我的祖国");
    }
    //第二种语法格式：类::静态方法名
    @Test
    public void test2() {
        ////不使用方法引用时
        Comparator<Integer> co1 = (x, y) -> Integer.compare(x, y);
        Comparator<Integer> co2 = (x, y) -> Integer.compare(x, y);
        Comparator<Integer> co3 = (x, y) -> Integer.compare(x, y);
        System.out.println(co1.compare(50, 20) + "，" + co2.compare(50, 50) + "，" + co3.compare(20, 50));
        //使用方法引用时
        Comparator<Integer> com1 = Integer::compare;
        Comparator<Integer> com2 = Integer::compare;
        Comparator<Integer> com3 = Integer::compare;
        System.out.println(com1.compare(50, 20) + "，" + com2.compare(50, 50) + "，" + com3.compare(20, 50));
    }
    //第三种语法格式：类::实例方法名
    @Test
    public void test3(){
        //不使用方法引用时
        BiPredicate<Integer,Integer> bp=(x, y)->x.equals(y);
        System.out.println(bp.test(5,5));
        //使用方法引用时
        BiPredicate<String,String> bp1=String::equals;
        System.out.println(bp1.test("李宇航","我和我的祖国"));
    }
    //带参的构造器引用
    @Test
    public void test4(){
        //不使用构造器引用
        Supplier<Employee> sup=()->new Employee();
        Employee emp1=sup.get();
        System.out.println(emp1);
        //使用构造器引用
        Supplier<Employee> sup1=Employee::new;
        Employee emp2=sup.get();
        System.out.println(emp2);
    }
    @Test
    public void test5() {
        //不使用构造器引用
        Function<Integer, Employee> fun = (x) -> new Employee(x);
        Employee emp1 = fun.apply(1);
        System.out.println(emp1);
        //使用构造器引用
        BiFunction<Integer, Integer, Employee> bf = Employee::new;
        Employee emp2 = bf.apply(1, 18);
        System.out.println(emp2);
    }
    @Test
    public void test6() {
        //不使用数组引用
        Function<Integer, String[]> fun = (x) -> new String[x];
        String[] strs1 = fun.apply(10);
        System.out.println(strs1.length);
        //使用数组引用
        Function<Integer, String[]> fun2 = String[]::new;
        String[] strs2 = fun.apply(20);
        System.out.println(strs2.length);
    }
}