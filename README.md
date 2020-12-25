# async-api-comparison

The goal is a practical comparison of various Java and Scala APIs of coordinating asyncronious execution in a functional manner, including
* tranforming the result of a completed async task (`map()`);
* chaining async tasks (`flatMap()`);
* combining multiple tasks (`zip()`).

* Java Future's
    https://github.com/gozela/async-api-comparison/blob/master/src/main/java/edoudkin/samples/JavaFutures.java
* Java CompletableFuture's
    https://github.com/gozela/async-api-comparison/blob/master/src/main/java/edoudkin/samples/JavaCompletableFutures.java
* ReactiveX / JaraRx
    https://github.com/gozela/async-api-comparison/blob/master/src/main/java/edoudkin/samples/RxObservables.java
* Scala Future's
    https://github.com/gozela/async-api-comparison/blob/master/src/main/scala/edoudkin/samples/ScalaFutures.scala

|Operation|Scala's Future|Java's Future|Java8's CompletableFuture| JavaRx Observable |
|---------|--------------|-------------|-------------------------|-------------------|
| Mapping result of the task | `Future.map()` | N/A | `CompletableFuture.thenApply()` | `Observable.map()` |
| Pipelining 2 tasks | `Future.flatMap()` | N/A | `CompletableFuture.thenCompose()` | `Observable.flatMap()` |
| Combining results of 2 independent tasks| `Future.zip()` | N/A | `CompletableFuture.thenCombine()` | `Observable.zipWith()`|
