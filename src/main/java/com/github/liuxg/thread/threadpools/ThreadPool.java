package com.github.liuxg.thread.threadpools;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author xinguai.liu
 */
public class ThreadPool {

    private BlockingQueue<Runnable> taskQueue;

    private long timeout;

    private TimeUnit unit;

    private Set<Worker> workers = new HashSet<>();

    private final int coreSize;

    private RejectPolicy<Runnable> rejectPolicy;

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapcity, RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.unit = timeUnit;
        this.taskQueue = new BlockingQueue<>(queueCapcity);
        this.rejectPolicy = rejectPolicy;
    }

    public void execute(Runnable runnable) {
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(runnable);
                workers.add(worker);
                worker.start();
                return;
            }
        }
        taskQueue.tryPut(rejectPolicy,runnable);
    }

    class Worker extends Thread {

        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            while (task != null || (task =taskQueue.poll(timeout,unit)) != null) {
                try {
                    task.run();
                } finally {
                    task = null;
                }
            }
            synchronized (workers) {
                workers.remove(this);
            }

        }
    }

}
