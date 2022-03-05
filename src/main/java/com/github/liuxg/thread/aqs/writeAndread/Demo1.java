package com.github.liuxg.thread.aqs.writeAndread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xinguai.liu
 */
public class Demo1 {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    private String data = "liuxg";

    public static void main(String[] args) throws InterruptedException {
        Demo1 demo1 = new Demo1();
        new Thread(()->{
            System.out.println("["+System.currentTimeMillis()+"]"+Thread.currentThread().getName()+"开始读取数据...");
            String read = demo1.read();
            System.out.println("["+System.currentTimeMillis()+"]"+Thread.currentThread().getName()+"读取："+read);
        },"t1").start();
        Thread.sleep(50);
        new Thread(()->{
            System.out.println("["+System.currentTimeMillis()+"]"+Thread.currentThread().getName()+"开始读取数据...");
            String read = demo1.read();
            System.out.println("["+System.currentTimeMillis()+"]"+Thread.currentThread().getName()+"读取："+read);
        },"t2").start();
    }

    public String read() {
        String temp = null;
        readLock.lock();
        try {
            Thread.sleep(5000);
            temp = data;
        } catch (InterruptedException e) {
            e.printStackTrace();
            temp = data;
        } finally {
            readLock.unlock();
        }
        return temp;
    }

    public void set(String data) {
        writeLock.lock();
        try {
            this.data = data;
        } finally {
            writeLock.unlock();
        }
    }



}
