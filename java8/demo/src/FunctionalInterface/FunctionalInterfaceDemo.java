package FunctionalInterface;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceDemo {
    public Integer operation(Integer num,MyFun mf){
        return mf.getValue(num);
    }
    public String function(String str,MyFunction mf){
        return mf.getValue(str);
    }
    public Long mf(Long l1,Long l2,MF<Long,Long> mf){
        return mf.getVAlue(l1,l2);
    }
    @Test
    public void test1(){
        Integer num = operation(100,(x)->x*x);
        System.out.println(num);
        System.out.println(operation(100,(y)->y+200));
    }
    @Test
    public void test2(){
        String str1=function("abcdef",x->x.toUpperCase());
        String str2=function("我是李宇航",y->y.substring(2,5));
        System.out.println(str1);
        System.out.println(str2);
    }
    @Test
    public void test3(){
        System.out.println(mf(5L,6L,(x,y)->(x+y)));
        System.out.println(mf(5L,6L,(x,y)->(x*y)));
    }
    //Consumer<T>：消费型接口，消费就是有去无回
    @Test
    public void test4() {
        happy(10000, (m) -> System.out.println("咱们东哥喜欢大学生，每次消费：" + m + "元"));//使用Lambda表达式对接口进行实现
    }

    public void happy(double money, Consumer<Double> con) {
        con.accept(money);
    }
    //Supplier<T>：供给型接口，就是给你产生一些对象，并返回给你
    @Test
    public void test5() {
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
        for (Integer num : numList) {
            System.out.println(num);
        }
    }

    //需求：产生一些指定个数的整数，并放入集合中
    public List<Integer> getNumList(int num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < num; i++) {
            Integer n = sup.get();
            list.add(n);
        }
        return list;
    }
    @Test
    public void test6() {
        String newStr = strHandler("\t\t\t   我是李宇航啊    ", (str) -> str.trim());
        System.out.println(newStr);

        String subStr = strHandler("我是李宇航啊", (str) -> str.substring(2, 5));
        System.out.println(subStr);
    }

    //Function<T, R>：函数型接口
//需求：专门用于处理字符串
    public String strHandler(String str, Function<String, String> fun) {
        return fun.apply(str);
    }
    @Test
    public void test7() {
        List<String> list = Arrays.asList("Hello", "liyuhang", "Lambda", "www", "ok");
        List<String> strList = filterStr(list, (s) -> s.length() > 3);
        for (String str : strList) {
            System.out.println(str);
        }
    }

    //Predicate<T>：断言型接口，用于做一些判断操作
//需求：还是处理字符串，将满足指定条件的字符串放入集合中去
    public List<String> filterStr(List<String> list, Predicate<String> pre) {
        List<String> strList = new ArrayList<String>();
        for (String str : list) {
            if (pre.test(str)) {
                strList.add(str);
            }
        }
        return strList;
    }
}
