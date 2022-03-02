package com.github.liuxg.thread.mode;

import java.util.concurrent.TimeUnit;

public class GuardedObject<T> {

    private T holdData;

    private final Object lock = new Object();

    public T getData() throws InterruptedException {
        synchronized (lock) {
            while (holdData == null) {
                lock.wait();
            }
            return holdData;
        }
    }

    public T getData(long time, TimeUnit unit) {
        synchronized (lock) {
            long start = System.currentTimeMillis();
            long passedTime = 0;
            long millis = unit.toMillis(time);
            while (holdData == null) {
                long wait = millis - passedTime;
                if (wait <= 0) {
                    break;
                }
                try {
                    Thread.sleep(wait);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                passedTime = System.currentTimeMillis() - start;
            }
            return holdData;
        }
    }

    public void compute(T data) {
        synchronized (lock) {
            holdData = data;
            lock.notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        GuardedObject<String> guardedObject = new GuardedObject<>();
        new Thread(()->{
            try {
                System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"读取"+guardedObject.getData());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();
        Thread.sleep(2000);
        new Thread(()->{
            System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"设置值");
            guardedObject.compute("liuxg");
        },"t2").start();
    }

}
