package com.cdanismaz.dload.slave;

import com.cdanismaz.dload.common.ThreadManager;

public class SlaveApp {

    public static void main(String[] args) {
        ThreadManager threadManager = new ThreadManager();

        SlaveInputListener slaveInputListener = new SlaveInputListener(threadManager);
        SlaveTcpListener slaveTcpListener = new SlaveTcpListener(threadManager);

        threadManager.addThread(slaveInputListener);
        threadManager.addThread(slaveTcpListener);

        Runtime.getRuntime().addShutdownHook(new Thread(threadManager::terminate));

        Thread slaveCommandListenerThread = new Thread(slaveInputListener);
        slaveCommandListenerThread.start();

        Thread slaveTcpListenerThread = new Thread(slaveTcpListener);
        slaveTcpListenerThread.start();
    }


}
