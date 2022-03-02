package com.github.liuxg.thread.nolock;

import java.math.BigDecimal;

public class Demo2 {
    public static void main(String[] args) {
        DecimalAccount.demo(new DecimalAccountUnsafe(new BigDecimal(500)));
        DecimalAccount.demo(new DecimalAccountCasSafe(new BigDecimal(500)));
        DecimalAccount.demo(new DecimalAccountLockSafe(new BigDecimal(500)));
    }
}
