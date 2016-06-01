package edoudkin.samples;

import org.apache.commons.lang3.tuple.Pair;
import rx.Observable;

import java.util.function.Supplier;

/**
 * Created by Egor Doudkin on 5/31/16.
 */
public class RxObservables {
  public static void main(String[] args) {

    Observable<Integer> o1 = createObservable(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
      }
      System.out.println("F1 Done!");
      return 1;
    });

    Observable<Integer> o2 = createObservable(() -> {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
      }
      System.out.println("F2 Done!");
      return 2;
    });

    Observable<Integer> o3 = o1.map(RxObservables::processResult).zipWith(o2.map(RxObservables::processResult), Pair::of)
        .flatMap(p -> {

          return createObservable(() -> {
            try {
              Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("F3 Done!");
            return p.getLeft() + p.getRight();
          });

        });

    Integer result = o3.toBlocking().first();

    System.out.println("result = " + result);

  }

  static int processResult(int i) {
    return 10 * i;
  }

  private static <R> Observable<R> createObservable(Supplier<R> serviceCall) {
    return Observable.create(subscriber -> {
      if (subscriber.isUnsubscribed()) {
        return;
      }
      try {
        subscriber.onNext(serviceCall.get());
        subscriber.onCompleted();
      } catch (Exception e) {
        subscriber.onError(e);
      }
    });
  }
}
