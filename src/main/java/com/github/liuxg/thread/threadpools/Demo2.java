package com.github.liuxg.thread.threadpools;

public class Demo2 {

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;
    private static final int RUNNING    = -1 << COUNT_BITS; // 111
    private static final int SHUTDOWN   =  0 << COUNT_BITS; //000
    private static final int STOP       =  1 << COUNT_BITS; //001
    private static final int TIDYING    =  2 << COUNT_BITS; //010
    private static final int TERMINATED =  3 << COUNT_BITS; //110

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(Integer.SIZE-1));
        System.out.println(Integer.toBinaryString(Integer.SIZE-2));
        System.out.println(Integer.toBinaryString(3));
        System.out.println(Integer.toBinaryString(COUNT_BITS));
        System.out.println(COUNT_BITS);
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(RUNNING));
        System.out.println(RUNNING);
        System.out.println(Integer.toBinaryString(CAPACITY));
        System.out.println(Integer.toBinaryString(RUNNING | 0));
    }

}
