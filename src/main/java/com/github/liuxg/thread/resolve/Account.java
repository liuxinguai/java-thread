package com.github.liuxg.thread.resolve;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author xinguai.liu
 */
@AllArgsConstructor
@Data
public class Account {

    private Integer money;

    private static  final Object lock = new Object();

    public void transfer(Account target, int amount) {
        synchronized (lock) {
            if (this.money >= amount) {
                this.setMoney(this.getMoney() - amount);
                target.setMoney(target.getMoney() + amount);
            }
        }
    }

}
