
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadExecutorsDemo {
    public static void main(String[] args) {
        /**
         * 创建线程的3种方式
         */
        //ExecutorService executorService= Executors.newFixedThreadPool(10);
        //ExecutorService executorService1= Executors.newSingleThreadExecutor();
        //ExecutorService executorService2= Executors.newCachedThreadPool();
        /**
         * 生产中实际用的方法
         */
        ExecutorService executorService3 = new ThreadPoolExecutor(2,
                5,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        //四种拒绝策略，默认抛出异常AbortPolicy()
        //第二种返回给调用者CallerRunsPolicy()
        //第三种抛弃等待时间最久的DiscardOldestPolicy()
        //第四种直接丢弃DiscardPolicy()
        for (int i = 0; i < 8; i++) {
            executorService3.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "执行成功");
            });
        }
    }
}
