package com.github.liuxg.thread.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicMarkableReference;

public class TestAba2 {
    public static void main(String[] args) throws InterruptedException {
        GarbageBag bag = new GarbageBag("装满了垃圾");
        AtomicMarkableReference<GarbageBag> ref = new AtomicMarkableReference(bag,true);
        System.out.println("主线程 start...");
        GarbageBag prev = ref.getReference();
        System.out.println(prev.toString());
        new Thread(() -> {
            System.out.println("打扫卫生的线程 start...");
            bag.setDesc("空垃圾袋");
            while (!ref.compareAndSet(bag, bag, true, false)) {}
            System.out.println(bag);
        }).start();
        Thread.sleep(1000);
        System.out.println("主线程想换一只新垃圾袋？");
        boolean success = ref.compareAndSet(prev, new GarbageBag("空垃圾袋"), true, false);
        System.out.println("换了么？" + success);
        System.out.println(ref.getReference().toString());
    }

    @ToString
    @AllArgsConstructor
    @Data
    static class GarbageBag {

        private String desc;

    }
}
