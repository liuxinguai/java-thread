package com.github.liuxg.thread.lock;


public class Demo4 {
    public static void main(String[] args) {
        Chopstick c1 = new Chopstick("筷子" + 1);
        Chopstick c2 = new Chopstick("筷子" + 2);
        Chopstick c3 = new Chopstick("筷子" + 3);
        Chopstick c4 = new Chopstick("筷子" + 4);
        Chopstick c5 = new Chopstick("筷子" + 5);
        new Philosopher("哲学家1",c1,c2).start();
        new Philosopher("哲学家2",c2,c3).start();
        new Philosopher("哲学家3",c3,c4).start();
        new Philosopher("哲学家4",c4,c5).start();
        new Philosopher("哲学家5",c5,c1).start();
    }
}
