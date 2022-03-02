package com.github.liuxg.thread.nolock;

import java.util.ArrayList;
import java.util.List;

public interface Account {

    int getBalance();


    void withdraw(int balance);


    static void demo(Account account) {
        List<Thread> threads = new ArrayList<>(50);
        for (int i = 0; i < 50; i++) {
           threads.add(new Thread(()->{
               account.withdraw(20);
           }));
        }
        threads.forEach(thread -> thread.start());
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(account.getBalance());
    }

}
