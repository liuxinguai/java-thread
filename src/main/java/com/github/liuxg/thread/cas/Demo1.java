package com.github.liuxg.thread.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Demo1 {
    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            demo(() -> new LongAdder(), adder -> adder.increment());
        }
        System.out.println("------------------------");
        for (int i = 0; i < 5; i++) {
            demo(() -> new AtomicLong(), adder -> adder.getAndIncrement());
        }

    }

    private static <T> void demo(Supplier<T> supplier, Consumer<T> action) {
        long start = System.currentTimeMillis();
        T adder = supplier.get();
        List<Thread> threads = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            threads.add(new Thread(()->{
                for (int j = 0; j < 500000; j++) {
                    action.accept(adder);
                }
            }));
        }
        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("cost : "+(System.currentTimeMillis()-start));
    }
}
