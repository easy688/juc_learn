import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkDemo extends RecursiveTask<Integer> {
    private static final Integer TX = 10;
    private int begin;
    private int end;
    private int result;

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public ForkDemo(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int middle = (begin + end) / 2;
        if (end - begin <= TX) {
            for (int i = begin; i <= end; i++) {
                result += i;
            }
        } else {
            ForkDemo forkDemo1 = new ForkDemo(begin, middle);
            ForkDemo forkDemo2 = new ForkDemo(middle + 1, end);
            forkDemo1.fork();
            forkDemo2.fork();
            result = forkDemo1.join() + forkDemo2.join();
        }

        return result;
    }

    public static void main(String[] args) {
        ForkDemo forkDemo = new ForkDemo(0, 100);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(forkDemo);
        try {
            System.out.println(forkJoinTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            forkJoinPool.shutdown();
        }

    }
}
