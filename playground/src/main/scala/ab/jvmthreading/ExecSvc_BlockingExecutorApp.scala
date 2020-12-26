package ab.jvmthreading

import java.util.concurrent.{LinkedBlockingQueue, RejectedExecutionHandler, ThreadPoolExecutor, TimeUnit}

import ab.jvmthreading.ExecSvc_RejectedExecutionApp.showT

import scala.concurrent.Future


/**
  * It throws RejectedExecutionException when number of submit(ted) or execut(ed) Tasks exceeds ThreadPool capacity.
  */
object ExecSvc_BlockingExecutorApp extends App with Loger {

  import scala.concurrent.ExecutionContext.Implicits.global

  val nThreads = 2
  val nTasks = (nThreads * 2) + 3
  showT(s"Starting App with $nTasks Tasks ...")

  val blockingQueueSize = nThreads
  val queue = new LinkedBlockingQueue[Runnable](nThreads)
  val exService = new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, queue)

  dispatch(1)
//  dispatch(2)

  while (true) {
    Thread.sleep(1500)
    showT("...main thread running...")
  }

  def dispatch(dispatchId: Int): Future[Unit] = Future {
    showT(s"Starting Future $dispatchId...")
    exService.setRejectedExecutionHandler(new RejectedExecutionHandler with Loger {
      override def rejectedExecution(r: Runnable, executor: ThreadPoolExecutor): Unit = {
        /** Executed on the caller's Thread (eg. main or Future's thread) */
        r match {
          case t: Tazk =>
            errorT(s"Tazk failure  ${t.id}")

            executor.getQueue().put(r)

            errorT(s"Tazk re-put  ${t.id}")

            /**
              * If rejectedExecutionHandler blocks, then it blocks caller.
              * However ThreadPool is thread-safe, hence it will not affect other callers submitting new tasks
              */
//            Thread.sleep(100000)
          case _ => println("NOT RUNNABLE :((((((")
        }
      }
    })

    for (i <- 1 to nTasks) {
//      Thread.sleep(600)
      //    exService.submit(new Task(i))
      exService.execute(new Tazk(i))
    }
    showT("Completed Future !")
  }
}
