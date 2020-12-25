package ab.jvmthreading

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
  * By default ThreadPoolExecutor does not spin up new threads when there are pending Runnables in the queue.
  */
object ExecSvc_NewThreadsCreatedApp extends App with Loger {

  val minThreads = 3
  val maxThreads = 10
  val nTasks = 10
  /**
    * 4th Task will make new Threads created.
    */
  val queueSize = 3

  val blockingQueueSize = minThreads
  val exService = new ThreadPoolExecutor(
    minThreads, maxThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(queueSize))

  for (i <- 0 until nTasks) {
    exService.execute(new Tazk(i))
  }
}

/**
  * New Threads are created ONLY when Queue is full. Hence for unbounded Queues, new Threads are never created.
  * Sample output:
  * 19:05:50.603 pool-1-thread-3 (13) Running task 2...
  * 19:05:50.603 pool-1-thread-1 (11) Running task 0...
  * 19:05:50.603 pool-1-thread-2 (12) Running task 1...
  * 19:05:51.607 pool-1-thread-2 (12) Running task 3...
  * 19:05:51.607 pool-1-thread-1 (11) Running task 5...
  * 19:05:51.607 pool-1-thread-3 (13) Running task 4...
  * 19:05:52.608 pool-1-thread-2 (12) Running task 6...
  * 19:05:52.608 pool-1-thread-1 (11) Running task 7...
  * 19:05:52.608 pool-1-thread-3 (13) Running task 8...
  * 19:05:53.609 pool-1-thread-2 (12) Running task 9...
  */
object ExecSvc_NONewThreadsCreatedApp extends App with Loger {

  val minThreads = 3
  val maxThreads = 10
  val nTasks = 10
  val queueSize = Int.MaxValue

  val blockingQueueSize = minThreads
  val exService = new ThreadPoolExecutor(
    minThreads, maxThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(queueSize))

  for (i <- 0 until nTasks) {
    exService.execute(new Tazk(i))
  }
}

