package com.github.liuxg.thread.mode;

import java.util.concurrent.locks.LockSupport;

public class OrderDemo {

    static final Object lock = new Object();
    static volatile boolean isCan = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            synchronized (lock) {
                System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"是否能运行:"+isCan);
                while (!isCan) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"正常工作");
            }
        },"t1").start();
        Thread.sleep(1000);
        new Thread(()->{
            synchronized (lock) {
                System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"设置线程t1能运行");
                isCan = true;
                lock.notifyAll();
            }
        },"t2").start();

        Thread t3 = new Thread(() -> {
            LockSupport.park();
            System.out.println("[" + System.currentTimeMillis() + "]" + "[" + Thread.currentThread().getName() + "]" + "等待t4运行结果");
        }, "t3");

        t3.start();

        new Thread(() -> {
            System.out.println("[" + System.currentTimeMillis() + "]" + "[" + Thread.currentThread().getName() + "]" + "运行结束");
            LockSupport.unpark(t3);
        }, "t4").start();
    }

}
