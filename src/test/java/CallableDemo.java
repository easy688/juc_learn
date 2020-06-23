import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 1024;
    }

    public static void main(String[] args) {
        Callable callable = new CallableDemo();
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        new Thread(futureTask, "有返回值的线程").start();
        System.out.println(futureTask.isDone());
        try {
            Integer num = futureTask.get();
            System.out.println("返回值：" + num + futureTask.isDone());
        } catch (InterruptedException e) {
            e.printStackTrace();

        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
    }
}
