package Lamda;

import org.testng.annotations.Test;

import java.util.*;

public class Lamdademo {
    List<Employee> employees = Arrays.asList(
            new Employee(100, "张三", 18, 9999.99),
            new Employee(101, "李四", 38, 5555.99),
            new Employee(102, "王五", 50, 6666.66),
            new Employee(103, "赵六", 16, 3333.33),
            new Employee(104, "田七", 18, 7777.77)
    );

    public List<Employee> filterEmployee(List<Employee> list, MyPredicate mp) {
        List<Employee> emps = new ArrayList<Employee>();
        for (Employee employee : list)
            if (mp.test(employee))
                emps.add(employee);
        return emps;
    }

    @Test
    /*-----使用Lamda表达式-----*/
    public void test4() {
        List<Employee> list = filterEmployee(employees, (MyPredicate<Employee>) e -> e.getSalary() >= 5000);
        list.forEach(System.out::println);
    }
    @Test
    public void test5(){
        Collections.sort(employees,(e1,e2)->{
            if(e1.getAge()==e2.getAge())
                return e1.getName().compareTo(e2.getName());
            else
                return Integer.compare(e1.getAge(),e2.getAge());
        });
        for(Employee emp:employees){
            System.out.println(emp);
        }
    }
}











/*-----获取年龄不小于35岁的员工信息-----*/
/*    public List<Employee> filterEmployees(List<Employee> list) {
        List<Employee> emps = new ArrayList<Employee>();
        for (Employee emp : list)
            if (emp.getAge() >= 35)
                emps.add(emp);
        return emps;
    }*/

/*-----获取薪水不低于5000元的员工信息-----*/

/*    public List<Employee> filterEmployees2(List<Employee> list) {
        List<Employee> emps = new ArrayList<Employee>();
        for (Employee emp : list)
            if (emp.getSalary() >= 5000)
                emps.add(emp);
        return emps;
    }
    @Test
    public void test() {
        List<Employee> list = filterEmployees(employees);
        for (Employee employee : list)
            System.out.println(employee);
    }*/


/*-----使用策略设计模式优化处理-----*/
/*-----按照策略执行方法-----*/

/*    @Test

    public void test3(){
        List<Employee> list=filterEmployee(employees, new MyPredicate<Employee>() {
            *//*-----使用匿名内部类实现接口-----*//*
            @Override
            public boolean test(Employee t) {
                return t.getSalary()>=5000;
            }
        });
        for(Employee employee:list)
            System.out.println(employee);
    }*/
/*   public void test2() {

 *//*-----获取年龄不小于35岁的员工信息-----*//*

        List<Employee> list1 = filterEmployee(employees, new FilterEmployeeByAge());
        for (Employee employee : list1)
            System.out.println(employees);
        *//*-----获取薪水不低于5000元的员工信息-----*//*
        List<Employee> list2 = filterEmployee(employees, new FilterEmployeeBySalary());
        for (Employee employee : list2)
            System.out.println(employee);
    }*/
/*    public void test5(){
        employees.stream()
                .filter((e) ->e.getSalary()>=5000).limit(2)
                .forEach(System.out::println);
        System.out.println("-------------------------------------");
        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }*/




