package com.github.liuxg.thread.nolock;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author xinguai.liu
 */
public class DecimalAccountCasSafe implements DecimalAccount {

    private AtomicReference<BigDecimal> balance;

    public DecimalAccountCasSafe(BigDecimal balance) {
        this.balance = new AtomicReference<>(balance);
    }

    @Override
    public BigDecimal getBalance() {
        return balance.get();
    }

    @Override
    public void withdraw(BigDecimal amount) {
        while (true) {
            BigDecimal prev = this.balance.get();
            BigDecimal next = prev.subtract(amount);
            if (this.balance.compareAndSet(prev,next)) {
                break;
            }
        }
    }
}
