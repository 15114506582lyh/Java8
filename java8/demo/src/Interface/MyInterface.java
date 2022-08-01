package Interface;

public interface MyInterface {
    default String getName() {
        return "呵呵呵";
    }
    //在Java 8中，接口中不仅可以有默认方法，还可以有静态方法
    public static void show() {
        System.out.println("接口中的静态方法");
    }
}
