package com.github.liuxg.thread.deadlock;

public class Demo1 {
    final static Object obj1 = new Object();
    final static Object obj2 = new Object();
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            a();
        },"t1").start();
        Thread.sleep(10);
        new Thread(()->{
            b();
        },"t2").start();
    }

    public static void a() {
        synchronized (obj1) {
            System.out.println(Thread.currentThread().getName()+"执行方法a");
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            b();
        }

    }

    public static void b() {
        synchronized (obj2) {
            System.out.println(Thread.currentThread().getName()+"执行方法b");
            a();
        }
    }
}
