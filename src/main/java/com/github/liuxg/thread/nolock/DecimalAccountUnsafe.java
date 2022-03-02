package com.github.liuxg.thread.nolock;

import java.math.BigDecimal;

/**
 * @author xinguai.liu
 */
public class DecimalAccountUnsafe implements DecimalAccount {

    private BigDecimal balance;

    public DecimalAccountUnsafe(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public void withdraw(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }
}
