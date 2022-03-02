package com.github.liuxg.thread.base;

/**
 * @author xinguai.liu
 */
public class YieldDemo {
    public static void main(String[] args) {
        new Thread(()->{
            Thread.yield();
            System.out.println(Thread.currentThread().getName()+"run");
        },"t2").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"run");
        },"t1").start();
    }
}
