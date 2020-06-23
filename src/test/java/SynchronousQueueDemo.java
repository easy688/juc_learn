import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                blockingQueue.put("a");
                System.out.println(Thread.currentThread().getName() + ":put_a");
                blockingQueue.put("b");
                System.out.println(Thread.currentThread().getName() + ":put_b");
                blockingQueue.put("c");
                System.out.println(Thread.currentThread().getName() + ":put_c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();
        new Thread(() -> {
            try {
                String a = blockingQueue.take();
                System.out.println(Thread.currentThread().getName() + ":take_a:" + a);
                TimeUnit.SECONDS.sleep(5);
                String b = blockingQueue.take();
                System.out.println(Thread.currentThread().getName() + ":take_b:" + b);
                TimeUnit.SECONDS.sleep(5);
                String c = blockingQueue.take();
                System.out.println(Thread.currentThread().getName() + ":take_c:" + c);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BBB").start();
    }
}
