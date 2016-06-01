import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}

/**
 * Created by Egor Doudkin on 5/30/16.
 */
object ScalaFutures extends App {
  import ExecutionContext.Implicits.global

  // Start F1
  val f1: Future[Int] = Future {
    Thread.sleep(1000)
    println("F1: Done!")
    1
  }

  // Start F2
  val f2: Future[Int] = Future {
    Thread.sleep(2000)
    println("F2: Done!")
    2
  }

  def processResult(input: Int) = input*10

  // Combining F1 and F2 :: COMPREHENSIONS
  val f3_1: Future[Int] = for {
    res1 <- f1.map(processResult)
    res2 <- f2
    res3 <- Future { Thread.sleep(1000); println("F3: Done! _1"); res1 + res2 }
  } yield res3

  // Combining F1 and F2 :: CLEAN FUNCTIONAL STYLE
  val f3_2: Future[Int] = f1.map(processResult).flatMap { res1 =>
    f2.map(processResult).flatMap { res2 =>
      Future { Thread.sleep(1000); println("F3: Done! _2"); res1 + res2 }
    }
  }

  println(Await.result(f3_1, 4 seconds))
  println(Await.result(f3_2, 4 seconds))

}
