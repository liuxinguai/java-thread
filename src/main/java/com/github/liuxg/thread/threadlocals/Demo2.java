package com.github.liuxg.thread.threadlocals;

public class Demo2 {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        String s = threadLocal.get();
        System.out.println(s);
        threadLocal.remove();
    }
}
