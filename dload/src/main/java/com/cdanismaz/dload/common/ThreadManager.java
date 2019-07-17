package com.cdanismaz.dload.common;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager {
    List<ClosableThread> threads = new ArrayList();

    public void addThread(ClosableThread thread) {
        threads.add(thread);
    }

    public void terminate() {
        for (ClosableThread thread : threads) {
            thread.terminate();
        }
    }
}
