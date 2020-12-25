package ab.jvmthreading

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit


/**
  * It throws RejectedExecutionException when number of submit(ted) or execut(ed) Tasks exceeds ThreadPool capacity.
  */
object ExecSvc_RejectedExecutionApp extends App with Loger {

  val nThreads = 3
  val nTasks = (nThreads * 2) + 1
  /**
    * IMPORTANT: ThreadPool capacity is a sum of 'maximumPoolSize' and BlockingQueue size.
    * BlockingQueue holds pending tasks.
    */
  val blockingQueueSize = nThreads
  val exService = new ThreadPoolExecutor(
    nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(nThreads))

  for (i <- 0 until nTasks) {
    //    exService.submit(new Task(i))
    exService.execute(new Tazk(i))
  }
}
