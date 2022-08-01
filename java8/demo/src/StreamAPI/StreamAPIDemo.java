package StreamAPI;

import org.testng.annotations.Test;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPIDemo {
    List<Employee> employees = Arrays.asList(
            new Employee(100, "张三", 18, 9999.99, Status.FREE),
            new Employee(101, "李四", 38, 5555.99, Status.BUSY),
            new Employee(102, "王五", 50, 6666.66, Status.VOCATION),
            new Employee(103, "赵六", 16, 3333.33, Status.FREE),
            new Employee(104, "田七", 38, 7777.77, Status.BUSY)
    );
    //1. 可以通过Collection系列集合提供的stream()方法（获取的是串行流）或者paralleStream()方法（获取的是并行流）获取流对象
    List<String> list = new ArrayList<String>();
    //流也可以带泛型
    Stream<String> stream1 = list.stream();
    //2. 通过Arrays类中的静态方法stream()获取数组流
    Employee[] emps = new Employee[10];
    Stream<Employee> stream2 = Arrays.stream(emps);
    //3. 通过Stream类中的静态方法of()获取流
    Stream<String> stream3 = Stream.of("aa", "bb", "cc");

    //4. 创建无限流
    //迭代（第一种创建无限流的方式）
    /*
     * 第一个参数是种子
     * 第二个参数是一个一元运算的操作，即Lambda表达式
     */
    @Test
    public void test01() {
        Stream<Integer> stream = Stream.iterate(0, x -> (x + 2));
        stream.forEach(System.out::println);
    }

    @Test
    public void test02() {
        Stream.generate(() -> Math.random()).forEach(System.out::println);
    }

    //演示filter(Predicate p)
    @Test
    public void test001() {
        Stream<Employee> steam = employees.stream()
                .filter((e) -> {
//                    System.out.println("Stream API的中间操作");
                    return e.getAge() > 35;
                });
        /*
         * 我为了让大家看结果，我就得来一个终止操作，不然的话，以上中间操作是没有任何结果的！
         * 因为中间操作没有执行，所以是没有任何结果的（中间操作它是不会执行任何操作的）。
         * 只有当你执行终止操作以后，所有的中间操作才一次性全部处理（一次性执行全部内容，这个过程我们就称为"惰性求值"，或者称为"延迟加载"）
         */
        steam.forEach(System.out::println);
    }

    @Test
    public void test003() {
        employees.stream()
                .filter(e -> {
                    System.out.println("短路");
                    return e.getSalary() > 5000;
                })
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void test004() {
        employees.stream()
                .filter(e -> (e.getSalary() > 5000))
                .skip(2)
                .forEach(System.out::println);
    }
    @Test
    public void test005(){
        employees.stream()
                .filter(e->(e.getAge())<35)
                .distinct()
                .forEach(System.out::println);
    }
    //在讲解flatMap(Function f)之前，我们可以在类中先编写一个方法，用于解析字符串，并把字符串中的一个一个的字符给单独提取出来，放到集合中
    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<Character>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }
    @Test
    public void test006(){
        List<String> list= Arrays.asList("aaa","bbb","ccc","ddd","eee");
        list.stream()
                .map(str->str.toUpperCase())
                .forEach(System.out::println);
        Stream<Character> sm=list.stream()
                .flatMap(StreamAPIDemo::filterCharacter);
        sm.forEach(System.out::println);
    }
    //演示sorted()，自然排序
    @Test
    public void test008() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");

        list.stream()
                .sorted()
                .forEach(System.out::println);
        employees.stream()
                .sorted((e1,e2)->{
                    if(e1.getAge()==e2.getAge())
                        return e1.getName().compareTo(e2.getName());
                    else
                        return e1.getAge().compareTo(e2.getAge());
                }).forEach(System.out::println);
    }
    @Test
    public void test9(){
        boolean b1 = employees.stream()
                .allMatch(e->e.getStatus().equals(Status.FREE));
        System.out.println(b1);
    }
    @Test
    public void test10(){
        boolean b1 = employees.stream()
                .anyMatch(e->e.getStatus().equals(Status.FREE));
        System.out.println(b1);
    }
    @Test
    public void test11(){
        boolean b1 = employees.stream()
                .noneMatch(e->e.getStatus().equals(Status.FREE));
        System.out.println(b1);
    }
    @Test
    public void test12(){
        Optional<Employee> op=employees.stream()
                .sorted((e1,e2)-> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        System.out.println(op.get());
    }
    @Test
    public void test13(){
        Optional<Employee> op=employees.parallelStream()
                .filter(e->e.getStatus().equals(Status.FREE))
                .findAny();
        System.out.println(op.get());
    }
    @Test
    public void test14(){
        long count= employees.stream()
                .count();
        System.out.println(count);
    }
    @Test
    public void test15(){
        Optional<Employee> op = employees.stream()
                .max((e1,e2)-> Double.compare(e1.getSalary(),e2.getSalary()));
        System.out.println(op.get());
    }
    @Test
    public void test16(){
        Optional<Employee> op = employees.stream()
                .min((e1,e2)->Double.compare(e1.getSalary(),e2.getSalary()));
        System.out.println(op.get());
    }
    @Test
    public void test17(){
        List<Integer> list= Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum=list.stream()
                .reduce(0,(x,y)->(x+y));
        System.out.println(sum);
    }
    @Test
    public void test18(){
        List<Integer> list= Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Optional<Integer> op= list.stream()
                .reduce(Integer::sum);
        System.out.println(op.get());
    }
    @Test
    public void test19(){
        List<String> list=employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);
    }
    @Test
    public void test20(){
        Set<String> set=employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);
    }
    @Test
    public void test21(){
        HashSet<String> hs= employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        hs.forEach(System.out::println);
    }
    @Test
    public void test22(){
        Long count= employees.stream()
                .collect(Collectors.counting());
        System.out.println(count);
    }
    @Test
    public void test23(){
        Double avg= employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avg);
    }
    @Test
    public void test24(){
        Double sum=employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);
    }
    @Test
    public void test25(){
        Optional<Employee> max= employees.stream()
                .collect(Collectors.maxBy((e1,e2)->Double.compare(e1.getSalary(),e2.getSalary())));
        System.out.println(max.get());
    }
    @Test
    public void test26(){
        Optional<Employee> max= employees.stream()
                .collect(Collectors.minBy((e1,e2)->Double.compare(e1.getSalary(),e2.getSalary())));
        System.out.println(max.get());
    }
    @Test
    public void test27(){
        Map<Status,Map<String,List<Employee>>> map= employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus,Collectors.groupingBy(e->{
                    if (e.getAge()<35)
                        return "青年";
                    else if (e.getAge() < 50)
                        return "中年";
                    else
                        return "老年";
                })));
            System.out.println(map);
    }
    @Test
    public void test28(){
        Map<Boolean,List<Employee>> map=employees.stream()
                .collect(Collectors.partitioningBy(e->e.getSalary()>8000));
        System.out.println(map);
    }
    @Test
    public void test29(){
        DoubleSummaryStatistics dss= employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("薪水总和："+dss.getSum());
        System.out.println("平均薪水："+dss.getAverage());
        System.out.println("最高薪水："+dss.getMax());
        System.out.println("最低薪水："+dss.getMin());
    }
    @Test
    public void test30(){
        String str= employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining());
        System.out.println(str);
    }
    @Test
    public void test31(){
        Integer[] num=new Integer[] {1,2,3,4,5};
        Arrays.stream(num)
                .map(x->x*x)
                .forEach(System.out::print);
    }
}
