import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockQueueDemo {
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue = null;

    public BlockQueueDemo(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() {
        String data = null;
        boolean retValue;
        if (flag) {
            try {
                data = atomicInteger.incrementAndGet() + "";
                retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
                if (retValue) {
                    System.out.println(Thread.currentThread().getName() + "插入队列成功" + data);
                } else {
                    System.out.println(Thread.currentThread().getName() + "插入队列失败" + data);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    public void myConsume() {
        String result = null;
        if (flag) {
            try {
                result = blockingQueue.poll(2L, TimeUnit.SECONDS);
                if (null == result || "".equalsIgnoreCase(result)) {
                    flag = false;
                    System.out.println(Thread.currentThread().getName() + "队列获取数据失败");
                    return;
                }
                System.out.println(Thread.currentThread().getName() + "队列获取数据成功：" + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    public void stop() {
        flag = false;
    }

    public static void main(String[] args) {
        BlockQueueDemo blockQueueDemo = new BlockQueueDemo(new ArrayBlockingQueue<>(10));
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                blockQueueDemo.myProd();

            }, "Prod").start();
            new Thread(() -> {
                blockQueueDemo.myConsume();
            }, "Consume").start();
        }
        try {
            TimeUnit.SECONDS.sleep(5);
            blockQueueDemo.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
