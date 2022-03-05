package com.github.liuxg.thread.aqs;

import java.util.concurrent.locks.ReentrantLock;

public class Demo3 {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            lock.lock();
            System.out.println("["+System.currentTimeMillis()+"]"+Thread.currentThread().getName() + "执行。。。。。。");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            lock.lock();
            System.out.println("["+System.currentTimeMillis()+"]"+Thread.currentThread().getName() + "执行。。。。。。");
            lock.unlock();
        }, "t2");
        t2.start();
        Thread.sleep(1000);
        t2.interrupt();

    }

}
