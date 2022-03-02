package com.github.liuxg.thread.wait;

/**
 * @author xinguai.liu
 */
public class Demo1 {

    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        Thread t1 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"执行.....");
            synchronized (obj) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"执行其它代码");
            }

        },"t1");

        Thread t2 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"执行.....");
            synchronized (obj) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"执行其它代码");
            }
        },"t2");

        t1.start();
        t2.start();

        Thread.sleep(2000);
        System.out.println("唤醒所有的线程");
        synchronized (obj) {
            obj.notifyAll();
        }
    }

}
