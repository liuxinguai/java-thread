package com.github.liuxg.thread.resolve;

public class TicketWindow {

    private int count;

    public TicketWindow(int count) {
        this.count = count;
    }

    public int sell(int amount) {
        synchronized (this) {
            if (count >= amount) {
                count -= amount;
                return amount;
            } else {
                return 0;
            }
        }
    }

    public int get() {
        return count;
    }
}
