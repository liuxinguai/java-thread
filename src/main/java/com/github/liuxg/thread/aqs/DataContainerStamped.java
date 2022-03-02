package com.github.liuxg.thread.aqs;

import java.util.concurrent.locks.StampedLock;

/**
 * @author xinguai.liu
 */
public class DataContainerStamped {

    private int data;

    private StampedLock stampedLock = new StampedLock();

    public DataContainerStamped(int data) {
        this.data = data;
    }

    public int read(int readTime) {
        long read = stampedLock.tryOptimisticRead();
        System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"optimistic read locking... "+read);
        try {
            Thread.sleep(readTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (stampedLock.validate(read)) {
            System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"read finish... data : "+data);
            return  data;
        }
        System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"updating to read lock..."+read);
        long readLock = stampedLock.readLock();
        System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"lock stamp : "+readLock);
        try {
            Thread.sleep(readTime);
            System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"read finish... data : "+data);
            return data;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return data;
        } finally {
            stampedLock.unlockRead(readLock);
        }
    }

    public void write(int data) {
        long stamp = stampedLock.writeLock();
        System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"write lock : "+stamp);
        try {
            Thread.sleep(1000);
            this.data = data;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"write unlock : "+stamp);
            stampedLock.unlockWrite(stamp);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DataContainerStamped stamped = new DataContainerStamped(10);
        new Thread(()-> stamped.read(1000),"t1").start();
        new Thread(()-> stamped.read(1000),"t2").start();


        Thread.sleep(2000);
        new Thread(()-> stamped.write(1000),"t3").start();
        new Thread(()-> stamped.write(1000),"t4").start();

        Thread.sleep(5000);

        new Thread(()-> stamped.read(1000),"t5").start();
        new Thread(()-> stamped.write(1000),"t6").start();
    }

}
