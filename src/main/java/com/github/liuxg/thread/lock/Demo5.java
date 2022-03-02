package com.github.liuxg.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xinguai.liu
 */
public class Demo5 {

    /**
     * Walkthrough:
     * 1. If write lock held by another thread, fail.
     * 2. Otherwise, this thread is eligible for
     *    lock wrt state, so ask if it should block
     *    because of queue policy. If not, try
     *    to grant by CASing state and updating count.
     *    Note that step does not check for reentrant
     *    acquires, which is postponed to full version
     *    to avoid having to check hold count in
     *    the more typical non-reentrant case.
     * 3. If step 2 fails either because thread
     *    apparently not eligible or CAS fails or count
     *    saturated, chain to version with full retry loop.
     */
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(()->{
            lock.lock();
            try {
                System.out.println("["+System.currentTimeMillis()+"]["+Thread.currentThread().getName()+"]"+"等待wait");
                condition.await();
                System.out.println("["+System.currentTimeMillis()+"]["+Thread.currentThread().getName()+"]"+"释放锁");
                Thread.sleep(500000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
              lock.unlock();
            }
        },"t1").start();

        new Thread(()->{
            lock.lock();
            try {
                Thread.sleep(5000);
                System.out.println("["+System.currentTimeMillis()+"]["+Thread.currentThread().getName()+"]"+"等待wait");
                condition.signal();
                System.out.println("["+System.currentTimeMillis()+"]["+Thread.currentThread().getName()+"]"+"释放锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t2").start();
    }
}
