package com.github.liuxg.thread.sync;

public class Room {
    int value = 0;

    public void increment() {
        synchronized (this) {
            value ++;
        }
    }

    public void decrement() {
        synchronized (this) {
            value--;
        }
    }

    public int get() {
        synchronized (this) {
            return value;
        }
    }
}
