package com.github.liuxg.thread.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author xinguai.liu
 */
public class Demo1 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        new Thread(()->{
            reentrantLock.lock();
            try {
                Thread.sleep(1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        },"t1").start();
        new Thread(()->{
            reentrantLock.lock();
            try {
                Thread.sleep(1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        },"t2").start();
    }

}
