package com.github.liuxg.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xinguai.liu
 */
public class Demo1 {

    final static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        method1();
    }

    public static void method1() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"execute method1");
            method2();
        } finally {
            lock.unlock();
        }
    }

    private static void method2() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"execute method2");
        } finally {
            lock.unlock();
        }
    }

}
