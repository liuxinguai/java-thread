package com.github.liuxg.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

public class Demo2 {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(Thread.currentThread().getName()+"获取锁失败，因为被打断...");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"释放锁...");
            lock.unlock();
        }, "t1");

        Thread t3 = new Thread(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"释放锁...");
            lock.unlock();
        }, "t3");

        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"获得锁，并启动t1");
            t1.start();
            Thread.sleep(20);
            t3.start();
            Thread.sleep(50);
            System.out.println(Thread.currentThread().getName()+"打断t1的休眠...");
            t1.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"释放锁");
            lock.unlock();
        }

    }



}
