package com.github.liuxg.thread.aqs;

import java.util.concurrent.Semaphore;

/**
 * @author xinguai.liu
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"获取 semaphore 成功");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"处理共享资源");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"处理共享资源结束");
                semaphore.release();
            },"t"+i).start();
        }
    }

}
