package SerialAndParallel;

import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

public class SerialAndParallelDemo {
    @Test
    public void test(){
        Instant start = Instant.now();//使用Java8中的新特性计算时间，得到一个时间戳
        LongStream.rangeClosed(0, 10000000000L)
                .parallel()
                .reduce(0, Long::sum);
        Instant end = Instant.now();
        System.out.println(Duration.between(start,end).toMillis());
    }
}
