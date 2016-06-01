import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Egor Doudkin on 5/30/16.
 */
public class JavaFutures {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService executor = Executors.newCachedThreadPool();

    // Start F1
    Future<Integer> f1 = executor.submit(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
      }
      System.out.println("F1 Done!");
      return 1;
    });

    // Start F2
    Future<Integer> f2 = executor.submit(() -> {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
      }
      System.out.println("F2 Done!");
      return 2;
    });

    // Awaiting F1 and F2 done and process results the both brought

    int f1result = -1, f2result = -1;
    boolean f1done = false, f2done = false;

    while (!f1done || !f2done) {
      if (f1.isDone()) {
        f1result = processResult(f1.get());
        f1done = true;
      }
      if (f2.isDone()) {
        f2result = processResult(f2.get());
        f2done = true;
      }
    }

    // Start F3 (depends on the results of F1 and F2)

    final int f1resultFixture = f1result,
        f2resultFixture = f2result;

    Future<Integer> f3 = executor.submit(() -> {
      Thread.sleep(1000);
      System.out.println("F3 Done!");
      return f1resultFixture + f2resultFixture;
    });

    // Sync await
    Integer result = f3.get();

    System.out.println("result = " + result);

  }

  static int processResult(int i) {
    return 10 *i;
  }
}
