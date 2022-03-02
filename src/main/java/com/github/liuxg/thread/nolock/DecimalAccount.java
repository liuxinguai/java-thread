package com.github.liuxg.thread.nolock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface DecimalAccount {

    BigDecimal getBalance();

    void withdraw(BigDecimal amount);

    static void demo(DecimalAccount account) {
        List<Thread> threads = new ArrayList<>(50);
        for (int i = 0; i < 50; i++) {
            threads.add(new Thread(()->{
                account.withdraw(BigDecimal.TEN);
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
