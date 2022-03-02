package com.github.liuxg.thread.deadlock;

/**
 * @author xinguai.liu
 */
public class Philosopher extends Thread {

    private String name;

    private Chopstick left;

    private Chopstick right;

    public Philosopher(String name, Chopstick left, Chopstick right) {
        super(name);
        this.name = name;
        this.left = left;
        this.right = right;
    }

    public void eat() throws InterruptedException {
        System.out.println(name+"吃饭");
        Thread.sleep(200);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (left) {
                synchronized (right) {
                    try {
                        eat();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
