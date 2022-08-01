package Date;

import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 这里就以传统日期时间格式化为例，看看它存在什么多线程安全问题？
 * @author liayun
 *
 */
public class DateDemo {

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        //定义好如下一个任务（task），该任务就是专门用于格式化一个时间或者日期的
        Callable<Date> task = new Callable<Date>() {

            @Override
            public Date call() throws Exception {
                return sdf.parse("20191207");
            }

        };

        //创建一个长度为10的线程池
        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<Date>> results = new ArrayList<Future<Date>>();

        //分10次去访问以上定义好的任务（task），然后它就会返回一个结果（叫Future），结果我都给它放在上面的集合里面
        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(task));
        }

        for (Future<Date> future : results) {
            System.out.println(future.get());
        }
    }
    @Test
    public void test1() {
        LocalDateTime ldt = LocalDateTime.now();//获取当前系统日期时间
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.of(2017, 10, 19, 13, 22, 33);//构造一个指定日期时间（哪年哪月哪日  几时几分几秒）的对象
        System.out.println(ldt2);

        //注意，不管做什么样的改变，它都将产生一个全新的实例
        LocalDateTime ldt3 = ldt.plusYears(2);//将当前日期时间加上2年
        System.out.println(ldt3);

        LocalDateTime ldt4 = ldt.minusMonths(2);//将当前日期时间减去2个月
        System.out.println(ldt4);

        System.out.println(ldt.getYear());		  //输出当前日期时间的年份
        System.out.println(ldt.getMonthValue());  //输出当前日期时间的月份
        System.out.println(ldt.getDayOfMonth());  //输出当前日期时间的月份天数（即几号，）
        System.out.println(ldt.getHour());        //输出当前日期时间的时
        System.out.println(ldt.getMinute());	  //输出当前日期时间的分
        System.out.println(ldt.getSecond());      //输出当前日期时间的秒
    }
    @Test
    public void test2() {
        Instant ins1 = Instant.now();//默认获取的是以UTC时区（世界协调时间，也叫格林威治时间）为基础的（当前时间的）时间戳
        System.out.println(ins1);//因为中国在东八区，所以这句代码输出的时间跟我的电脑时间是不同的

        //做一个偏移量的运算
        OffsetDateTime odt = ins1.atOffset(ZoneOffset.ofHours(8));//既然中国在东八区，故要偏移8个小时，这样子获取到的时间才是自己电脑的时间
        System.out.println(odt);//带偏移量的时间和日期

        System.out.println(ins1.toEpochMilli());//转成对应的毫秒值，如果是当前时间的时间戳，结果跟System.currentTimeMillis()是一样的

        //对时间戳做一个稍微的改变
        Instant ins2 = Instant.ofEpochSecond(60);
        System.out.println(ins2);//此时，得到的是1970-01-01T00:01:00Z，也即Unix元年过去1分钟之后的时间戳
    }
    @Test
    public void test3() {
        Instant ins1 = Instant.now();
        try {
            Thread.sleep(3000);//中间睡一会
        } catch (InterruptedException e) {

        }
        Instant ins2 = Instant.now();

        //计算两个时间戳之间的间隔
        Duration duration = Duration.between(ins1, ins2);
        // System.out.println(duration);//PT3S
        System.out.println(duration.toMillis());//获取毫秒

        System.out.println("---------------------------------");

        LocalTime lt1 = LocalTime.now();
        try {
            Thread.sleep(3000);//中间睡一会
        } catch (InterruptedException e) {

        }
        LocalTime lt2 = LocalTime.now();

        //计算两个时间之间的间隔
        Duration duration2 = Duration.between(lt1, lt2);
        // System.out.println(duration2);//可能会输出PT3S或者输出PT3.001S，至于多出来的0.001秒其实就是除去线程睡眠时间之外执行计算时间间隔那句代码消耗的时间
        System.out.println(duration2.toMillis());//可能会输出3000或者输出3001
    }
    @Test
    public void test4() {
        //起始时间指定为2019年1月1日
        LocalDate ld1 = LocalDate.of(2019, 1, 1);
        //终止时间指定为当前时刻
        LocalDate ld2 = LocalDate.now();

        Period period = Period.between(ld1, ld2);
        System.out.println(period);//可能会输出P3Y7M，Y代表年，M代表月，D代表日，说明起始时间和当前时刻之间的日期间隔是0年11个月零17天

        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }
    @Test
    public void test5() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = ldt.withDayOfMonth(10);//将当前系统日期时间中的月份天数指定为10
        System.out.println(ldt2);

        //通过时间校正器，就可以指定一些特殊的操作了，比如说下一个周日、下一个工作日、下一个结婚纪念日
        //下一个周日
        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt3);
    }
    @Test
    public void test7() {
        //在Java 8中，支持多少时区
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);
    }

}
