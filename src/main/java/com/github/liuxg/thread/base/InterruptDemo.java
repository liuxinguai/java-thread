package com.github.liuxg.thread.base;

/**
 * @author xinguai.liu
 */
public class InterruptDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" interrupted flags : "+Thread.currentThread().isInterrupted());
        },"t1");
        /**
         * t1.interrupt()
         * 打断t1线程的休眠，并清空t1线程的打断标记为false
         */
        t1.start();
        Thread.sleep(1);
        t1.interrupt();

        /***
         * t2没有处于休眠状态时，调用t2.interrupt()只会设置打断flag为true
         */
        Thread t2 = new Thread(()->{
            while (true) {
                Thread currentThread = Thread.currentThread();
                boolean flag = currentThread.isInterrupted();
                System.out.println(currentThread.getName()+" interrupted flags : "+ flag);
                if (flag) {
                    break;
                }
            }
        },"t2");
        t2.start();
        Thread.sleep(1);
        t2.interrupt();

    }

}
