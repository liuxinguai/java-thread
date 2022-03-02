package com.github.liuxg.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Demo6 {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(true);
        Condition condition = lock.newCondition();
        new Thread(()->{
            lock.lock();
            try {
                Thread.sleep(50);
                System.out.println("["+System.currentTimeMillis()+"]["+Thread.currentThread().getName()+"]"+"等待wait");
                condition.await();
                System.out.println("["+System.currentTimeMillis()+"]["+Thread.currentThread().getName()+"]"+"释放锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t1").start();

        new Thread(()->{
            lock.lock();
            try {
                System.out.println("["+System.currentTimeMillis()+"]["+Thread.currentThread().getName()+"]"+"等待wait");
                Thread.sleep(100);
                System.out.println("["+System.currentTimeMillis()+"]["+Thread.currentThread().getName()+"]"+"释放锁");
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t2").start();

        new Thread(()->{
            lock.lock();
            try {
                System.out.println("["+System.currentTimeMillis()+"]["+Thread.currentThread().getName()+"]"+"等待wait");
                Thread.sleep(150);
                condition.await();
                System.out.println("["+System.currentTimeMillis()+"]["+Thread.currentThread().getName()+"]"+"释放锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t3").start();
        Thread.sleep(200);
        lock.lock();
        condition.signalAll();
        lock.unlock();
    }

}
