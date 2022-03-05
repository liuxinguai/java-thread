package com.github.liuxg.thread.fork;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xinguai.liu
 */
public class Demo3 {

    private final static Random random = new Random();

    private final static AtomicInteger count = new AtomicInteger();
    public static void main(String[] args) {
        Character[] array = new Character[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = random();
        }
        MyOperation<Character> operation = new MyOperation<Character>();
        ForEach.forEach(array,operation);
        System.out.println(count.get());
    }

    static char random() {
        return (char) (random.nextInt(26) + 65);
    }

    static class MyOperation<E> {
        void apply(E e) {
            System.out.println(e);
            count.getAndIncrement();
        }

    }

    static class ForEach<E> extends CountedCompleter<Void> {

        public static <E> void forEach(E[] array, MyOperation<E> op) {
            new ForEach<E>(null, array, op, 0, array.length).invoke();
        }

        final E[] array; final MyOperation<E> op; final int lo, hi;
        ForEach(CountedCompleter<?> p, E[] array, MyOperation<E> op, int lo, int hi) {
            super(p);
            this.array = array; this.op = op; this.lo = lo; this.hi = hi;
        }

        @Override
        public void compute() { // version 1
            if (hi - lo >= 2) {
                int mid = (lo + hi) >>> 1;
                setPendingCount(2); // must set pending count before fork
                new ForEach(this, array, op, mid, hi).fork(); // right child
                new ForEach(this, array, op, lo, mid).fork(); // left child
            }
            else if (hi > lo)
                op.apply(array[lo]);
            tryComplete();
        }
    }

}
