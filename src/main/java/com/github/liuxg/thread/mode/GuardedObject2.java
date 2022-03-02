package com.github.liuxg.thread.mode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author xinguai.liu
 */
public class GuardedObject2 {

    private String id;

    public GuardedObject2(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    private Object holdData;

    public Object get(long timeout, TimeUnit unit) {
        synchronized (this) {
            long start = System.currentTimeMillis();
            long passed = 0;
            while (holdData == null) {
                long wait = unit.toMillis(timeout) - passed;
                if (wait <= 0) {
                    break;
                }
                try {
                    Thread.sleep(wait);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                passed = System.currentTimeMillis() - start;
            }
        }
        return holdData;
    }

    public Object get() {
        synchronized (this) {
            while (holdData == null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return holdData;
    }

    public void compute(Object data) {
        synchronized (this) {
            this.holdData = data;
            this.notifyAll();
        }
    }

    static class Person extends Thread {
        @Override
        public void run() {
            GuardedObject2 guardedObject2 = Mailboxes.createGuardedObject2();
            System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"开始收信 id :"+guardedObject2.id);
            Object o = guardedObject2.get();
            System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"收到信，信内容是："+o);
        }
    }

    static class Postman extends Thread {

        private int id;

        private String mail;

        public Postman(int id, String mail) {
            this.id = id;
            this.mail = mail;
        }

        @Override
        public void run() {
            GuardedObject2 guardedObject2 = Mailboxes.get(id);
            System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"送信 id : "+id+" 内容 : "+mail);
            guardedObject2.compute(mail);
        }
    }

    static class Mailboxes {

        private static Map<Integer,GuardedObject2> guardedObject2Map = new HashMap<>();

        private static int index = 0;

        public static synchronized int generateId() {
            return ++index;
        }

        public static synchronized GuardedObject2 get(Integer index) {
            return guardedObject2Map.remove(index);
        }

        public static synchronized GuardedObject2 createGuardedObject2() {
            int id = generateId();
            GuardedObject2 guardedObject2 = new GuardedObject2(id+"");
            guardedObject2Map.put(id, guardedObject2);
            return guardedObject2;
        }

        public static synchronized Set<Integer> ids() {
            return guardedObject2Map.keySet();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new Person().start();
        }
        Thread.sleep(500);
        Mailboxes.ids().forEach((id)->{
            new Postman(id,"内容:"+id).start();
        });
    }

}
