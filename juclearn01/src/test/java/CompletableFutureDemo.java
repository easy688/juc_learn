import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {
    public static void main(String[] args) {

        CompletableFuture<Void> completableFuture=CompletableFuture.runAsync(()->{
             System.out.println("123");
        });
        CompletableFuture<Integer> completableFuture1=CompletableFuture.supplyAsync(()->1024);
        try {
            completableFuture.get();
            System.out.println("hhh:"+completableFuture1.get());
            System.out.println(completableFuture1.whenComplete((t, u)->{
                int i=10/0;
                System.out.println("已完成:"+t+";"+u);
            }).exceptionally(t->404).get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
