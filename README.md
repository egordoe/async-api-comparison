# async-api-comparison

The intention of this repository is to get a practical comparison of different Java and Scala frameworks of organizing collaborative work of multiple asynchronous tasks, like
* mapping task result 
* starting a new async tasks as the initial task is done
* zipping the results of 2 tasks
* etc.

The comparison is made by implementing the same scenario (is described below) and the comparison focuses only on the stylistic facet of the APIs, mainly concerning of the stylistic aspect of the frameworks (code readability, maintainability, verboseness, how error prone it is, etc.)

The sample scenario is very basic but still able to reveal some shortcomings that frameworks have.

![Alt](http://dl.dropboxusercontent.com/u/100788417/images/async_frameworks.jpeg)

The scenario consists of 3 asynchronous stages

* Start asynchronously 2 parallel tasks F1 and F2
* As either F1 or F2 is done, the task result is processed in some way
* Finally, as the both results of F1 and F2 are processed, they are combined together by one more asynchronous task  

The scenario above is currently implemented using 

* Java's Future 
    https://github.com/gozela/async-api-comparison/blob/master/src/main/java/JavaFutures.java
* Java's CompletableFuture
    https://github.com/gozela/async-api-comparison/blob/master/src/main/java/JavaCompletableFutures.java
* ReactiveX / JaraRx
    https://github.com/gozela/async-api-comparison/blob/master/src/main/java/RxObservables.java
* Scala's Future 
    https://github.com/gozela/async-api-comparison/blob/master/src/main/scala/ScalaFutures.scala

