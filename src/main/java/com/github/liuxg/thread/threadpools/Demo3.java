package com.github.liuxg.thread.threadpools;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo3 {

    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;//后29位表示容量

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;//111
    private static final int SHUTDOWN   =  0 << COUNT_BITS;//000
    private static final int STOP       =  1 << COUNT_BITS;//001
    private static final int TIDYING    =  2 << COUNT_BITS;//010
    private static final int TERMINATED =  3 << COUNT_BITS;//110

    // Packing and unpacking ctl
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }

    public static void main(String[] args) {

        System.out.println(COUNT_BITS);
        System.out.println(1<<2);
        System.out.println(Integer.toBinaryString(RUNNING));
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(SHUTDOWN));
        System.out.println(Integer.toBinaryString(STOP));
        System.out.println(Integer.toBinaryString(TIDYING));
        System.out.println(Integer.toBinaryString(TERMINATED));
    }
}
