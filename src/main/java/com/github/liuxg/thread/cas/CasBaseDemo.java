package com.github.liuxg.thread.cas;

public class CasBaseDemo {
    public static void main(String[] args) {
        int c;
        int a = 1;
        carbase(c=a,c+1);
    }

    public static void carbase(int a, int b) {
        System.out.println(a+","+b);
    }
}
