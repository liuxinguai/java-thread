package com.github.liuxg.thread.mode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xinguai.liu
 */
public class SyncLock {

    private Lock lock = new ReentrantLock();

    private int loopNum;

    public SyncLock(int loopNum) {
        this.loopNum = loopNum;
    }

    public void print(String word, Condition current, Condition nextCondition) {
        for (int i = 0; i < loopNum; i++) {
            lock.lock();
            try {
                try {
                    current.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(word);
                nextCondition.signal();
            } finally {
                lock.unlock();
            }
        }
    }


    public void start(Condition start) {
        lock.lock();
        try {
            start.signal();
            System.out.println("start");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SyncLock syncLock = new SyncLock(5);
        Condition conditionA = syncLock.lock.newCondition();
        Condition conditionB = syncLock.lock.newCondition();
        Condition conditionC = syncLock.lock.newCondition();
        new Thread(()->syncLock.print("a",conditionA,conditionB)).start();
        new Thread(()->syncLock.print("b",conditionB,conditionC)).start();
        new Thread(()->syncLock.print("c",conditionC,conditionA)).start();
        Thread.sleep(1000);
        syncLock.start(conditionA);
    }
}
