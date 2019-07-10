package com.cansu.concurrency;

public class MyParallelProcess implements Runnable {
    private int id;
    private boolean shouldWork = true;

    public MyParallelProcess(int id) {
        this.id=id;
    }

    public void run() {
        while(shouldWork) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("my id is : " + this.id);
    }

    public void finish() {
        this.shouldWork = false;
    }
}
