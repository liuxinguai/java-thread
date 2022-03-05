package com.github.liuxg.thread.aqs;

import java.util.concurrent.locks.LockSupport;

public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().isInterrupted());
            try {
                LockSupport.park();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行。。。。");
            System.out.println(Thread.currentThread().isInterrupted());
            boolean interrupted = Thread.interrupted();
            System.out.println(interrupted);
            System.out.println(Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();
        Thread.sleep(200);
        t1.interrupt();

        //Thread.interrupted(),当前线程被打断时，返回true，并清空当前线程状态位,
        //若没被打断时返回false，状态位为false

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+Thread.currentThread().isInterrupted());
            System.out.println(Thread.currentThread().getName()+Thread.interrupted());
            System.out.println(Thread.currentThread().isInterrupted());
        },"t2").start();


        Thread t3 = new Thread(() -> {
            LockSupport.park();
            System.out.println(Thread.currentThread().getName()+Thread.currentThread().isInterrupted());
            System.out.println(Thread.currentThread().getName()+Thread.interrupted());
            System.out.println(Thread.currentThread().getName()+Thread.currentThread().isInterrupted());
        }, "t3");

        t3.start();
        Thread.sleep(200);
        LockSupport.unpark(t3);

    }
}
