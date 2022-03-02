package com.github.liuxg.thread.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author xinguai.liu
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        new Thread(()->{
            System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"开始...");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"继续执行...");
        },"t1").start();

        new Thread(()->{
            System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"开始...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"继续执行...");
        },"t2").start();

    }

}
