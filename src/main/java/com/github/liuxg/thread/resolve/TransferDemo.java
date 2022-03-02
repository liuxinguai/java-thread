package com.github.liuxg.thread.resolve;

import java.util.Random;

/**
 * @author xinguai.liu
 */
public class TransferDemo {

    public static void main(String[] args) throws InterruptedException {
        Account a = new Account(1000);
        Account b = new Account(1000);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                a.transfer(b, randomAmount());
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                b.transfer(a, randomAmount());
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("total:"+(a.getMoney() + b.getMoney()));
    }

    static Random random = new Random();

    private static int randomAmount() {
        return random.nextInt(100) + 1;
    }


}
