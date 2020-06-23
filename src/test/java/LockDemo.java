import java.util.concurrent.TimeUnit;

public class LockDemo implements Runnable {
    private String lockA;
    private String lockB;

    public LockDemo(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "lockA尝试获取lockB");
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "lockB尝试获取lockA");
            }
        }
    }

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new LockDemo(lockA, lockB)).start();
        new Thread(new LockDemo(lockB, lockA)).start();
        //jps -l 查看java进程
        //jstack pid（进程号）查看java虚拟机堆栈信息


    }
}
