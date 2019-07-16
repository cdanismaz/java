package com.cdanismaz.dload.slave;

import com.cdanismaz.dload.common.ThreadManager;
import com.cdanismaz.dload.master.MasterTcpManager;

import java.io.*;
import java.net.*;

public class SlaveApp {

    public static void main(String[] args) throws IOException {
        ThreadManager threadManager = new ThreadManager();

        SlaveInputListener slaveInputListener = new SlaveInputListener(threadManager);
        SlaveTcpListener slaveTcpListener = new SlaveTcpListener(threadManager);

        threadManager.addThread(slaveInputListener);
        threadManager.addThread(slaveTcpListener);

        Thread slaveCommandListenerThread = new Thread(slaveInputListener);
        slaveCommandListenerThread.start();

        Thread slaveTcpListenerThread = new Thread(slaveTcpListener);
        slaveTcpListenerThread.start();
    }


}
