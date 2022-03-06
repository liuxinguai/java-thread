package com.github.liuxg.thread.aqs;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xinguai.liu
 */
public class Demo9 {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        new Thread(()->{
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行。。。");
        },"t1").start();
//        Thread.sleep(1000);
//        new Thread(()->{
//            try {
//                semaphore.acquire();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName()+"执行。。。");
//        },"t2").start();
//        Thread.sleep(60000);
//        semaphore.release();
    }

}
