package Interface;

public class SubClass implements MyFun,MyInterface{
    @Override
    public String getName(){
        return MyFun.super.getName();
//        return MyInterface.super.getName();
    }
}
