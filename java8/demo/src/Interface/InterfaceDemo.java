package Interface;

import org.testng.annotations.Test;

public class InterfaceDemo {
    @Test
    public void test1() {
        SubClass sc =new SubClass();
        System.out.println(sc.getName());
    }
}
