package com.github.liuxg.thread.base;

/**
 * @author xinguai.liu
 */
public class TestStat {

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            System.out.println("洗水壶");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("烧开水");
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");
        Thread t2 = new Thread(()->{
            System.out.println("洗茶壶");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("洗茶叶");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("拿茶叶");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("泡茶");
        },"t2");
        t1.start();
        t2.start();
    }

}
