package com.github.liuxg.thread.nolock;

public class Demo1 {

    public static void main(String[] args) {
        Account account = new UnsafeAccount(1000);
        Account.demo(account);

        Account.demo(new AccountSafe(1000));
    }

}
