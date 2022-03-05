package com.github.liuxg.thread.aqs;

import java.util.concurrent.Semaphore;

/**
 * @author xinguai.liu
 */
public class SemaphoreDemo2 {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        new Thread(()->{
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行....");
        },"t1").start();

        new Thread(()->{
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行...");
        },"t2").start();

    }

}
