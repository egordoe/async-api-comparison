package edoudkin.samples;

import org.apache.commons.lang3.tuple.Pair;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by Egor Doudkin on 6/1/16.
 */
public class JavaCompletableFutures {
  public static void main(String[] args) throws ExecutionException, InterruptedException {

    CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
      }
      System.out.println("F1 Done!");
      return 1;
    });

    CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
      }
      System.out.println("F2 Done!");
      return 2;
    });

    CompletableFuture<Integer> f3 = f1.thenApply(JavaCompletableFutures::processResult).thenCombine(
        f2.thenApply(JavaCompletableFutures::processResult), Pair::of).thenCompose(p -> {
      return CompletableFuture.supplyAsync(() -> {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println("F3 Done!");
        return p.getLeft() + p.getRight();
      });
    });

    Integer result = f3.get();

    System.out.println("result = " + result);
  }

  static int processResult(int i) {
    return 10 *i;
  }

}
