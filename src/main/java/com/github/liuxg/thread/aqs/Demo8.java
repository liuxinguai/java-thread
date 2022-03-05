package com.github.liuxg.thread.aqs;

import java.util.concurrent.locks.ReentrantLock;

public class Demo8 {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(true);
        new Thread(()->{
            lock.lock();
            try {
                Thread.sleep(120000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行....");
            lock.unlock();
        },"t1").start();

        Thread.sleep(1000);

        new Thread(()->{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"执行....");
            lock.unlock();
        },"t2").start();

        Thread.sleep(1000);

        new Thread(()->{
            lock.lock();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行....");
            lock.unlock();
        },"t3").start();

    }
}
