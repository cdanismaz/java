package com.cansu.concurrency;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static List<MyParallelProcess> mppList = new ArrayList<MyParallelProcess>();


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            MyParallelProcess mpp = new MyParallelProcess(i);
            mppList.add(mpp);
            Thread t = new Thread(mpp);
            t.start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < mppList.size(); i++) {
            mppList.get(i).finish();
        }
    }
}
