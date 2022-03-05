package com.github.liuxg.thread.aqs;

public class Demo5 {

    public static void main(String[] args) {
        int i = 0;
        do {
            System.out.println(i);
            i++;
        } while (i < 5);
    }

}
