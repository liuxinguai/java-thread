package com.github.liuxg.thread.threadpools;

import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo {
    public static void main(String[] args) {
        Timer timer = new Timer();

        TimerTask timerTask1 = new TimerTask() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis()+":"+Thread.currentThread().getName()+"执行定时任务1");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        TimerTask timerTask2 = new TimerTask() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis()+":"+Thread.currentThread().getName()+"执行定时任务2");
            }
        };
        timer.schedule(timerTask1,1000);
        timer.schedule(timerTask2,1000);

    }
}
