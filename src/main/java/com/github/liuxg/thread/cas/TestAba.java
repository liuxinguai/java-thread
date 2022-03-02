package com.github.liuxg.thread.cas;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class TestAba {
    public static void main(String[] args) throws InterruptedException {
        AtomicReference<String> result = new AtomicReference<>();
        Thread t1 = new Thread(() -> {
            String prev = result.get();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"A->C : " + result.compareAndSet(prev, "C"));
        }, "t1");
        t1.start();
        Thread t2 = new Thread(() -> {
            String prev = result.get();
            System.out.println(Thread.currentThread().getName()+"A->B:"+result.compareAndSet(prev,"B"));
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"B-A:"+result.compareAndSet(result.get(),"A"));
        }, "t2");
        t2.start();
        Thread.sleep(1000);
        AtomicStampedReference<String> reference = new AtomicStampedReference<>("A",0);
        Thread t3 = new Thread(() -> {
            String prev = reference.getReference();
            int stamp = reference.getStamp();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"A->C : " + reference.compareAndSet(prev, "C",stamp,stamp+1));
        }, "t3");
        t3.start();
        Thread t4 = new Thread(() -> {
            String prev = reference.getReference();
            System.out.println(Thread.currentThread().getName()+"A->B : " + reference.compareAndSet(prev, "B",reference.getStamp(),reference.getStamp()+1));
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"B-A:"+reference.compareAndSet(reference.getReference(),"A",reference.getStamp(),reference.getStamp()+1));
        }, "t4");
        t4.start();
    }
}
