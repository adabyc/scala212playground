package ab.jvmthreading

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
  * By default ThreadPoolExecutor does not spin up new threads when there are pending Runnables in the queue.
  */
object ExecSvcMinMaxThreadsApp extends App with Loger {

  val minThreads = 3
  val maxThreads = 10
  val nTasks = 10
  val queueSize = 3// Int.MaxValue

  val blockingQueueSize = minThreads
  val exService = new ThreadPoolExecutor(
    minThreads, maxThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(queueSize))

  for (i <- 0 until nTasks) {
    exService.execute(new Tazk(i))
  }
}
