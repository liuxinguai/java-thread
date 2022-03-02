package com.github.liuxg.thread.aqs;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataContainer {

    private Object data = new Object();

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    private ReentrantReadWriteLock.ReadLock readLock = lock.readLock();

    public Object read() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"读取数据");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
        return data;
    }

    public void write(Object data) {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"写入数据");
            this.data = data;
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        DataContainer container = new DataContainer();
        new Thread(()->{
            container.read();
        },"t1").start();
        new Thread(()->{
            container.read();
        },"t2").start();
    }

}
