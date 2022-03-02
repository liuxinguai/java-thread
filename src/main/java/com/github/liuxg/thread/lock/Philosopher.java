package com.github.liuxg.thread.lock;

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

    public void eat() {
        System.out.println(name+"吃饭");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        int index = 0;
        while (index++ <= 5) {
            if (left.tryLock()) {
                try {
                    if (right.tryLock()) {
                        try {
                            eat();
                        } finally {
                            right.unlock();
                        }
                    }
                } finally {
                    left.unlock();
                }
            }
        }
    }
}
