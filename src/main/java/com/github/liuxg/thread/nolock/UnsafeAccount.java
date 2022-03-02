package com.github.liuxg.thread.nolock;

public class UnsafeAccount implements Account {

    private int balance;

    public UnsafeAccount(int balance) {
        this.balance = balance;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public void withdraw(int balance) {
        this.balance -= balance;
    }
}
