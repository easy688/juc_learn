import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public  class Test {

        public static void main(String[] args) {
            Data1 data = new Data1();
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

    class Data1 {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition c1 = reentrantLock.newCondition();
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        public void add() {
            try {
                reentrantLock.lock();
                while (atomicBoolean.get()) {
                    c1.await();
                }
                System.out.println(Thread.currentThread().getName() + "----------1");
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
                    c1.await();
                }
                System.out.println(Thread.currentThread().getName() + "----------2");
                atomicBoolean.set(false);
                c1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }

        }

    }
