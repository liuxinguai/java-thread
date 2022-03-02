package com.github.liuxg.thread.aqs;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xinguai.liu
 */
public class CountDownLatchDemo1 {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        AtomicInteger count = new AtomicInteger();
        ExecutorService executorService = Executors.newFixedThreadPool(5, (r) -> new Thread(r, "t" + count.getAndIncrement()));
        Random random = new Random();
        String[] all = new String[5];
        for (int i = 0; i < 5; i++) {
            final int index = i;
            executorService.submit(()->{
                for (int j = 0; j < 100; j++) {
                    try {
                        Thread.sleep(random.nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    all[index] = Thread.currentThread().getName() + "(" + (j + "%") + ")";
                    System.out.println(Arrays.toString(all));
                }
                countDownLatch.countDown();
            });
        }
        System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+" wait...");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("游戏开始");
    }
}
