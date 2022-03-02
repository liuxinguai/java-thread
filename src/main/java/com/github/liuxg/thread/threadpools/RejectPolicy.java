package com.github.liuxg.thread.threadpools;

@FunctionalInterface
public interface RejectPolicy<T> {

    /**
     * 拒绝策略
     * @param queue 阻塞队列
     * @param task 执行任务
     */
    void reject(BlockingQueue<T> queue, T task);

}
