1.线程池的状态
    ![](D:\work-spaces\java-thread\src\main\resources\threadpoolExecutor\status.png)
2. execute()方法详解：
   ![](D:\work-spaces\java-thread\src\main\resources\threadpoolExecutor\execute.png)
3. addWorker(Runnable firstTask, boolean core)详解：
   1. CAS线程池中线程的数量，即将工作线程数+1===>对应for代码块
   2. 新建工作线程数，将工作线程加入到HashSet中，添加成功执行工作线程，失败调用addWorkerFailed将工作线程数-1===>对应try/finally
   3. ![](D:\work-spaces\java-thread\src\main\resources\threadpoolExecutor\addWorker.png)
4. runWorker()
   1. 先运行显示过来的任务，运行完，在从阻塞任务队列中获取任务
   2. 先设置当前当前线程的状态为正在运行中，通过对Aqs中的status状态加锁来实现
   3. 保证当线程池状态为STOP时，该线程的状态为interrupted，不为STOP时保证当前线程状态不为interrupted
   4. 执行钩子方法beforeExecute()
   5. 执行任务的run方法
   6. 执行钩子方法afterExecute()
   7. 计数当前线程成功运行的任务书
   8. 当执行run方法抛出异常，设置completedAbruptly为true
   9. 当前线程执行异常时或者无任务可执行时->执行processWorkerExit方法
   10. ![](D:\work-spaces\java-thread\src\main\resources\threadpoolExecutor\runWorker.png)
5. processWorkerExit
   1. 当线程异常退出，将工作线程数-1
   2. 将工作线程从HashSet中移除
   3. 尝试终止线程池的运行
   4. 当线程池的状态<=STOP时确保此时线程池中至少有一个线程存活
   5. ![](D:\work-spaces\java-thread\src\main\resources\threadpoolExecutor\processWorkerExit.png)
6. tryTerminate()
   1. ![](D:\work-spaces\java-thread\src\main\resources\threadpoolExecutor\tryTerminate.png)
   2. 因为此处方法有多处调用，须确保线程池
      1. 当线程池处于RUNNING时不进行任何处理
      2. 当多线程执行时，当线程池>=TIDYING说明方法已经有别的线程正在执行或已执行
      3. 当线程池处于SHUTDOWN时且任务队列中还有任务未执行时不该表线程池的状态
      4. 确保只有一个线程去处理下面的terminated方法和设置线程池的状态
7. interruptIdleWorkers()
   1. ![interruptIdleWorkers](D:\work-spaces\java-thread\src\main\resources\threadpoolExecutor\interruptIdleWorkers.png)
8. shutdown
   1. ![shutdown](D:\work-spaces\java-thread\src\main\resources\threadpoolExecutor\shutdown.png)
9. shutdownNow
   1. ![shutdownNow](D:\work-spaces\java-thread\src\main\resources\threadpoolExecutor\shutdownNow.png)
10. 总结：
    1. 线程池的状态：采用clt是一个int数中的高三位实现,工作线程数是采用低29位表示
       1. RUNNING 111
       2. SHUTDOWN 000
       3. STOP 001
       4. TIDYING 010
       5. TERMINED 110
    2. 线程池状态装换
       1. RUNNING->SHUTDOWN shutdown
       2. SHUTDOWN/RUNNING->STOP shutdownNow
       3. STOP/SHUTDOWN->TIDYING tryTermined
       4. TIDYING->TERMINED termined

