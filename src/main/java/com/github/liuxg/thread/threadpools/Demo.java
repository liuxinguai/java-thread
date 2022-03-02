package com.github.liuxg.thread.threadpools;

import java.util.concurrent.TimeUnit;

public class Demo {

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(1,1000,TimeUnit.MILLISECONDS,1,((queue, task) -> {task.run();}));
        for (int i = 0; i < 4; i++) {
            final int j = i;
            threadPool.execute(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"打印:"+j);
            });
        }
    }

}
