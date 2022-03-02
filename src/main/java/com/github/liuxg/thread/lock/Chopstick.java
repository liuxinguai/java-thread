package com.github.liuxg.thread.lock;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xinguai.liu
 */
@AllArgsConstructor
@Data
public class Chopstick extends ReentrantLock {

    private String name;

}
