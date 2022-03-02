package com.github.liuxg.thread.threadpools;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulerDemo2 {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
//        scheduledThreadPool.scheduleAtFixedRate(()->{
//            System.out.println(Thread.currentThread().getName()+"执行任务，时间："+System.currentTimeMillis());
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },1,1, TimeUnit.SECONDS);

        scheduledThreadPool.scheduleWithFixedDelay(()->{
            System.out.println(Thread.currentThread().getName()+"执行任务，时间："+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },1,1, TimeUnit.SECONDS);
    }

}
