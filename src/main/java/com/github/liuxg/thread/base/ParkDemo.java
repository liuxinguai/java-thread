package com.github.liuxg.thread.base;

import java.util.concurrent.locks.LockSupport;

/**
 * @author xinguai.liu
 */
public class ParkDemo {

    public static void main(String[] args) throws InterruptedException {
        /**
         * t1.interrupt()会打断LockSupport.park()休眠
         * 并设置打断标记为True
         * 通过Thread.interrupted()清空打断标记
         */
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                long now = System.currentTimeMillis();
                System.out.println(Thread.currentThread().getName()+" park...." + Thread.currentThread().isInterrupted());
                LockSupport.park();
                System.out.println(Thread.currentThread().getName()+" unpark...."+(System.currentTimeMillis()-now));
                System.out.println(Thread.currentThread().getName()+" flag : "+Thread.currentThread().isInterrupted());
                System.out.println(Thread.currentThread().getName()+" interrupted : "+Thread.interrupted());
            }
        }, "t1");
        t1.start();
        Thread.sleep(50);
        t1.interrupt();
    }

}
