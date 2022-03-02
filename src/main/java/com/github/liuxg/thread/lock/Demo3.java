package com.github.liuxg.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Demo3 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(()->{
            if (!lock.tryLock()) {
                System.out.println(Thread.currentThread().getName()+"获取锁失败");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"获取锁成功");
            lock.unlock();
            System.out.println(Thread.currentThread().getName()+"释放锁");
        },"t1");

        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"启动t1线程");
            t1.start();
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"释放锁");
            lock.unlock();
        }

        Thread t2 = new Thread(()->{
            try {
                if (!lock.tryLock(10, TimeUnit.MILLISECONDS)) {
                    System.out.println(Thread.currentThread().getName()+"获取锁超时");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            System.out.println(Thread.currentThread().getName()+"获取锁成功");
            lock.unlock();
            System.out.println(Thread.currentThread().getName()+"释放锁");
        },"t2");

        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"启动t2线程");
            t2.start();
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"释放锁");
            lock.unlock();
        }
    }
}
