package ab.jvmthreading

import java.util.concurrent.{LinkedBlockingQueue, RejectedExecutionHandler, ThreadPoolExecutor, TimeUnit}
import scala.concurrent.Future


/**
  * It throws RejectedExecutionException when number of submit(ted) or execut(ed) Tasks exceeds ThreadPool capacity.
  */
object ExecSvc_RejectedExecutionApp extends App with Loger {

  import scala.concurrent.ExecutionContext.Implicits.global

  showT(s"Starting App...")
  val nThreads = 3
  val nTasks = (nThreads * 2) + 1
  /**
    * IMPORTANT: ThreadPool capacity is a sum of 'maximumPoolSize' and BlockingQueue size.
    * BlockingQueue holds pending tasks.
    */
  val blockingQueueSize = nThreads
  val exService = new ThreadPoolExecutor(
    nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(nThreads))

  Future {
    showT("Starting Future...")
    exService.setRejectedExecutionHandler(new RejectedExecutionHandler with Loger {
      override def rejectedExecution(r: Runnable, executor: ThreadPoolExecutor): Unit = {
        /** Executed on the caller's Thread (eg. main or Future's thread) */
        r match {
          case t: Tazk =>
            errorT(s"Tazk failure  ${t.id}")
          case _ => println("NOT RUNNABLE :((((((")
        }
      }
    })

    for (i <- 0 until nTasks) {
      //    exService.submit(new Task(i))
      exService.execute(new Tazk(i))
    }
    showT("Completed Future !")
  }

  while (true) {
    Thread.sleep(700)
    showT("...main here")
  }
}

//class RejectionHandler extends RejectedExecutionHandler {
//  override def rejectedExecution(r: Runnable, executor: ThreadPoolExecutor): Unit = ???
//}