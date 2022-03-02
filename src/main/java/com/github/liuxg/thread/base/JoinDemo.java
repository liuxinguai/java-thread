package com.github.liuxg.thread.base;

/**
 * @author xinguai.liu
 */
public class JoinDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"run");
        },"t1");

        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"run");
        },"t2");
        t1.start();
        t2.start();
        /**
         * t2线程等待t1线程执行完后在执行
         */
    }

}
