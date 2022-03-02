package com.github.liuxg.thread.sync;

import org.openjdk.jol.info.ClassLayout;

public class JolDemo {
    public static void main(String[] args) {
        Object o = new Object();
        ClassLayout instance = ClassLayout.parseInstance(o);
        System.out.println(instance.toPrintable());

        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
