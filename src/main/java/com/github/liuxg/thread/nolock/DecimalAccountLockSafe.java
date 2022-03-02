package com.github.liuxg.thread.nolock;

import java.math.BigDecimal;

/**
 * @author xinguai.liu
 */
public class DecimalAccountLockSafe implements DecimalAccount {

    private static final Object obj = new Object();

    private BigDecimal balance;

    public DecimalAccountLockSafe(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public void withdraw(BigDecimal balance) {
        synchronized (obj) {
            this.balance = this.balance.subtract(balance);
        }
    }
}
