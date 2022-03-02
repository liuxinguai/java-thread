package com.github.liuxg.thread.wait;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author xinguai.liu
 */
public class Demo2 {

    public static void main(String[] args) throws InterruptedException {
        Object room = new Object();
        AtomicBoolean hasCigarette = new AtomicBoolean(false);
        boolean hasTakeout = false;
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"有烟没?"+hasCigarette);
            synchronized (room) {
                while (!hasCigarette.get()) {
                    System.out.println(Thread.currentThread().getName()+"没烟，休息会");
                    try {
                        room.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName()+"有烟，开始工作");
            }
        }, "小南").start();

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                synchronized (room) {
                    System.out.println(Thread.currentThread().getName()+"开始干活");
                }
            },"其他人").start();
        }

        Thread.sleep(1000);
        new Thread(()->{
            synchronized (room) {
                System.out.println(Thread.currentThread().getName()+"开始干活，送烟");
                hasCigarette.set(true);
                room.notify();
            }
        },"小红").start();
    }

}
