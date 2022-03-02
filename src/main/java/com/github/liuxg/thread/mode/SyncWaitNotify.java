package com.github.liuxg.thread.mode;

/**
 * @author xinguai.liu
 */
public class SyncWaitNotify {

    private int runFlag;

    private int loopNum;

    public SyncWaitNotify(int runFlag, int loopNum) {
        this.runFlag = runFlag;
        this.loopNum = loopNum;
    }

    public void print(int waitFlag, int nextFlag, String word) {
        for (int i = 0; i < loopNum; i++) {
            synchronized (this) {
                while (runFlag != waitFlag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.runFlag = nextFlag;
                System.out.print(word);
                this.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        SyncWaitNotify syncWaitNotify = new SyncWaitNotify(1, 5);
        new Thread(()->{
           syncWaitNotify.print(1,2,"a");
        }).start();
        new Thread(()->{
            syncWaitNotify.print(2,3,"b");
        }).start();
        new Thread(()->{
            syncWaitNotify.print(3,1,"c");
        }).start();
    }

}
