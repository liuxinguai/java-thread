package com.github.liuxg.thread.threadpools;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xinguai.liu
 */
public class BlockingQueue<T> {

    private Deque<T> queue = new ArrayDeque<>();

    private Lock lock = new ReentrantLock();

    /**
     * 生产临界条件
     */
    private Condition fullWaitSet = lock.newCondition();

    /**
     * 消费者临界条件
     */
    private Condition emptyWaitSet = lock.newCondition();

    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public T poll(long timeout, TimeUnit unit) {
        lock.lock();
        try {
            long nanos = unit.toNanos(timeout);
            while (queue.isEmpty()) {
                try {
                    if (nanos <= 0) {
                        return null;
                    }
                    nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T poll = queue.removeFirst();
            fullWaitSet.signalAll();
            return poll;
        } finally {
            lock.unlock();
        }
    }

    public T take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T poll = queue.poll();
            fullWaitSet.signalAll();
            return poll;
        } finally {
            lock.unlock();
        }
    }

    public void put(T task) {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                try {
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addFirst(task);
            emptyWaitSet.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public boolean offer(T task, long timeout, TimeUnit unit) {
        lock.lock();
        try {
            long nanos = unit.toNanos(timeout);
            while (queue.size() == capacity) {
                if (nanos <= 0) {
                    return false;
                }
                try {
                    nanos = fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addFirst(task);
            emptyWaitSet.signalAll();
            return true;
        } finally {
            lock.unlock();
        }
    }

    public void tryPut(RejectPolicy<T> rejectPolicy,T task) {
        lock.lock();
        try {
            if (queue.size() == capacity) {
                rejectPolicy.reject(this,task);
            } else {
                queue.addFirst(task);
                emptyWaitSet.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }

    }


}
