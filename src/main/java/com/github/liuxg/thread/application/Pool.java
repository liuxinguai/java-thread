package com.github.liuxg.thread.application;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author xinguai.liu
 */
public class Pool {

    private Semaphore semaphore = null;

    private final int capacity;

    private Conn[] conns;

    private AtomicIntegerArray states;

    public Pool(int capacity) {
        this.capacity = capacity;
        semaphore = new Semaphore(capacity);
        conns = new Conn[capacity];
        for (int i = 0; i < capacity; i++) {
            conns[i] = new Conn("conn"+i);
        }
        states = new AtomicIntegerArray(new int[capacity]);
    }


    public Conn borrow() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < capacity; i++) {
            if (states.get(i) == 0) {
                if (states.compareAndSet(i,0,1)) {
                    System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"] borrow "+conns[i]);
                    return conns[i];
                }
            }
        }
        return null;
    }

    public void free(Conn conn) {
        for (int i = 0; i < capacity; i++) {
            if (conns[i] == conn) {
                if (states.compareAndSet(i,1,0)) {
                    System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"] free "+conn);
                    semaphore.release();
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Pool pool = new Pool(2);
        new Thread(()->{
            Conn borrow = pool.borrow();
            System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"使用"+borrow);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pool.free(borrow);
        },"t1").start();
        new Thread(()->{
            Conn borrow = pool.borrow();
            System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"使用"+borrow);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pool.free(borrow);
        },"t2").start();
        new Thread(()->{
            Conn borrow = pool.borrow();
            System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"使用"+borrow);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pool.free(borrow);
        },"t3").start();
        new Thread(()->{
            Conn borrow = pool.borrow();
            System.out.println("["+System.currentTimeMillis()+"]"+"["+Thread.currentThread().getName()+"]"+"使用"+borrow);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pool.free(borrow);
        },"t4").start();
    }

    @ToString
    @AllArgsConstructor
    static class Conn {
        private String name;
    }

}
