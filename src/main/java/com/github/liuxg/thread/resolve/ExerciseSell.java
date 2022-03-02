package com.github.liuxg.thread.resolve;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class ExerciseSell {

    public static void main(String[] args) {
        TicketWindow ticketWindow = new TicketWindow(2000);
        // 用来存储买出去多少张票
        List<Thread> list = new ArrayList<>();
        List<Integer> sellCount = new Vector<>();
        for (int i = 0; i < 2000; i++) {
            Thread t = new Thread(() -> sellCount.add(ticketWindow.sell(randomAmount())));
            list.add(t);
            t.start();
        }
        list.forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 买出去的票求和
        System.out.println("selled count:"+sellCount.stream().mapToInt(c -> c).sum());
        // 剩余票数
        System.out.println("remainder count:"+ticketWindow.get());
    }

    static Random random = new Random();

    private static int randomAmount() {
        return random.nextInt(5) + 1;
    }

}
