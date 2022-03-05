package com.github.liuxg.thread.threadlocals;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 *
 * @author xinguai.liu
 */
public class Demo1 {

    public static void main(String[] args) {
        Dog dog = new Dog("liuxg");
        ReferenceQueue<Dog> referenceQueue = new ReferenceQueue<>();
        WeakReference<Dog> weakReference = new WeakReference<Dog>(dog,referenceQueue);
        dog = null;
        while (weakReference.get() != null) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Reference<? extends Dog> reference = referenceQueue.poll();
        if (reference.get() != null) {
            reference.get().clear();
        }
    }

    @AllArgsConstructor
    @Data
    static class Dog {

        private String name;

        public void clear() {
            System.out.println(name+"被清理");
        }
    }

}
