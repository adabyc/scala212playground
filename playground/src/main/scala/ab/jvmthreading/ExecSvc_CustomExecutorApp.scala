package ab.jvmthreading.custom

import java.util.concurrent.{BlockingQueue, LinkedBlockingQueue, RejectedExecutionHandler, ThreadPoolExecutor, TimeUnit}

import ab.jvmthreading.{Loger, Tazk}

import scala.concurrent.Future

/**
  * Custom ThreadPoolExecutor gives an option to override beforeExecute and afterExecute hooks.
  * Those are executed in the Thread that is about to run Runnable!
  */
class CustomThreadPool(corePoolSize: Int, maximumPoolSize: Int,
                       keepAliveTime: Long, timeUnit: TimeUnit, workQueue: BlockingQueue[Runnable]) extends
  ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, workQueue) with Loger {

  override def beforeExecute(t: Thread, r: Runnable): Unit = {
    super.beforeExecute(t, r)

    showT(s" Before executing ${r}   ${t.getName}  ${Thread.currentThread().getName}")
  }

  override def afterExecute(r: Runnable, t: Throwable): Unit = {
    super.afterExecute(r, t)
    showT(s" After executing ${r}  ${Thread.currentThread().getName}")
  }
}

/**
  * It throws RejectedExecutionException when number of submit(ted) or execut(ed) Tasks exceeds ThreadPool capacity.
  */
object ExecSvc_CustomExecutorApp extends App with Loger {

  import scala.concurrent.ExecutionContext.Implicits.global

  val nThreads = 2
  val nTasks = (nThreads * 2) + 3
  showT(s"Starting App with $nTasks Tasks ...")

  val blockingQueueSize = nThreads
  val queue = new LinkedBlockingQueue[Runnable](nThreads)
  val exService = new CustomThreadPool(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, queue)

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
