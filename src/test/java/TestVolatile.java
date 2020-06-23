import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class TestVolatile {

    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.add();
            }
        }).start();
        new Thread(() -> {
            for (int j = 0; j < 10; j++) {
                data.reduce();
            }
        }).start();
    }
}

class Data {
    ReentrantLock reentrantLock = new ReentrantLock();
    Condition c1 = reentrantLock.newCondition();
    AtomicBoolean atomicBoolean = new AtomicBoolean(true);

    public void add() {
        try {
            reentrantLock.lock();
            while (atomicBoolean.get()) {
                System.out.println(Thread.currentThread().getName() + "----------1");
                c1.await();
            }
            System.out.println(Thread.currentThread().getName() + "add():" + atomicBoolean.get());
            atomicBoolean.set(true);
            c1.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void reduce() {
        try {
            reentrantLock.lock();
            while (!atomicBoolean.get()) {
                System.out.println(Thread.currentThread().getName() + "----------2");
                c1.await();
            }
            System.out.println(Thread.currentThread().getName() + "reduce():" + atomicBoolean.get());
            atomicBoolean.set(false);
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
           reentrantLock.unlock();
        }

    }

}