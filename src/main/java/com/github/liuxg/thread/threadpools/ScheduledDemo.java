package com.github.liuxg.thread.threadpools;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author xinguai.liu
 */
public class ScheduledDemo {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        scheduledThreadPool.schedule(()->{
            System.out.println(Thread.currentThread().getName()+"执行时间"+System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },1000, TimeUnit.MILLISECONDS);
        scheduledThreadPool.schedule(()->{
            System.out.println(Thread.currentThread().getName()+"执行时间"+System.currentTimeMillis());
        },1000, TimeUnit.MILLISECONDS);
    }
}
