package com.github.liuxg.thread.threadpools;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Demo4 {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,2,100, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>(1));
        executor.execute(()->{
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread.sleep(1000);

        executor.execute(()->{
            System.out.println("sss");
            try {
                Thread.sleep(120000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

//        executor.execute(()->{
//            try {
//                Thread.sleep(120000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
    }

}
