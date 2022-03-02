package com.github.liuxg.thread.cas;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class Demo {

    volatile int count = 0;

    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<Demo> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Demo.class, "count");
        Demo demo = new Demo();
        System.out.println(fieldUpdater.compareAndSet(demo,0,10));
        System.out.println(demo.count);
        System.out.println(fieldUpdater.compareAndSet(demo,10,20));
        System.out.println(demo.count);
        System.out.println(fieldUpdater.compareAndSet(demo,10,30));
        System.out.println(demo.count);
    }
}
