package com.github.liuxg.thread.nolock;

import java.util.concurrent.atomic.AtomicInteger;

public class AccountSafe implements Account {

    private AtomicInteger balance;

    public AccountSafe(int balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public int getBalance() {
        return balance.get();
    }

    @Override
    public void withdraw(int balance) {
        while (true) {
            int prev = this.balance.get();
            int next = prev - balance;
            if (this.balance.compareAndSet(prev,next)) {
                break;
            }
        }
    }
}
