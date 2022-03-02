package com.github.liuxg.thread.wait;

import java.util.concurrent.locks.LockSupport;

/**
 * @author xinguai.liu
 */
public class ParkDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start....");
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " park....");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " resume....");
        }, "t1");
        t1.start();
        Thread.sleep(35);
        System.out.println(t1.getName()+" unpark....");
        LockSupport.unpark(t1);

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start....");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " park....");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " resume....");
        }, "t2");
        t2.start();
        Thread.sleep(20);
        System.out.println(t2.getName()+" unpark....");
        LockSupport.unpark(t2);
    }

}
