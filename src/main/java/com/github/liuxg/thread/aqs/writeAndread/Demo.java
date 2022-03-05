package com.github.liuxg.thread.aqs.writeAndread;

public class Demo {

    static final int SHARED_SHIFT   = 16;
    static final int SHARED_UNIT    = (1 << SHARED_SHIFT);
    static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
    static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;

    public static void main(String[] args) {
        System.out.println(SHARED_SHIFT);
        System.out.println(SHARED_UNIT);
        System.out.println(Integer.toBinaryString(MAX_COUNT));
        System.out.println(Integer.toBinaryString(EXCLUSIVE_MASK));

        System.out.println(1>>>3);
    }

}
