package com.github.liuxg.thread.aqs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Demo4 {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                condition.await();
                Thread.yield();
//                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }, "t1");
        t1.start();
        Thread.sleep(6000);
        lock.lock();
        condition.signal();
        t1.interrupt();
        lock.unlock();
    }

}
