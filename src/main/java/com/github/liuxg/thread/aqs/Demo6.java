package com.github.liuxg.thread.aqs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Demo6 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行");
            lock.unlock();
        }, "t1");
        t1.start();
        t1.interrupt();
    }
}
