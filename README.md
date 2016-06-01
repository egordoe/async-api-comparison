# async-api-comparison

The intention of this repository is to get a practical comparison of different Java and Scala frameworks of organizing collaborative work of multiple asynchronous tasks, including
* mapping task result (`map()`)
* starting a new async task as the initial task is done (`flatMap()`)
* zipping the results of 2 tasks (`zip()`)
* etc.

The comparison is made by implementing the same sample scenario (described below) and it focuses only on the stylistic facet of the API, mainly concerning of aspects such as code readability, maintainability, verboseness, how error prone the code we get, etc.)

The sample scenario is very basic.

![Alt](http://dl.dropboxusercontent.com/u/100788417/images/async_frameworks.jpeg)

It consists of 3 stages

* 2 initial tasks F1 and F2 which produce some integers
* As result of F1 or F2 is available, the corresponding result is mapped to some other value (multiplied by 10, for instance)
* Finally, as the both results of F1 and F2 are available and mapped, they are combined together and they both passed as an input to third task F3

The scenario above is currently implemented using 

* Java's Future 
    https://github.com/gozela/async-api-comparison/blob/master/src/main/java/edoudkin/samples/JavaFutures.java
* Java's CompletableFuture
    https://github.com/gozela/async-api-comparison/blob/master/src/main/java/edoudkin/samples/JavaCompletableFutures.java
* ReactiveX / JaraRx
    https://github.com/gozela/async-api-comparison/blob/master/src/main/java/edoudkin/samples/RxObservables.java
* Scala's Future 
    https://github.com/gozela/async-api-comparison/blob/master/src/main/scala/edoudkin/samples/ScalaFutures.scala

|Operation|Scala's Future|Java's Future|Java8's CompletableFuture| JavaRx Observable |
|---------|--------------|-------------|-------------------------|-------------------|
| Mapping result of the task | `Future.map()` | N/A | `CompletableFuture.thenApply()` | `Observable.map()` |
| Pipelining 2 tasks | `Future.flatMap()` | N/A | `CompletableFuture.thenCompose()` | `Observable.flatMap()` |
| Combining results of 2 independent tasks| `Future.zip()` | N/A | `CompletableFuture.thenCombine()` | `Observable.zipWith()`|
