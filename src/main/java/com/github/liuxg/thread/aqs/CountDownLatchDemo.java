package com.github.liuxg.thread.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xinguai.liu
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        AtomicInteger count = new AtomicInteger();
        ExecutorService service = Executors.newFixedThreadPool(4, (r) -> new Thread(r, "t" + count.getAndIncrement()));
        for (int i = 0; i < 3; i++) {
            service.submit(()->{
                System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"begin....");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
                System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"end..."+latch.getCount());
            });
        }
        Thread.sleep(500);
        service.submit(()->{
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"执行完成");
        });
    }

}
