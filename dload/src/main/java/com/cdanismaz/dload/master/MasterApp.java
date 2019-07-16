package com.cdanismaz.dload.master;

import java.io.*;

public class MasterApp {

    public static void main(String[] args) throws IOException {

        final MasterTcpManager masterTcpManager = new MasterTcpManager();
        Thread connectionManagerThread = new Thread(masterTcpManager);
        connectionManagerThread.start();

        Thread commandListenerThread = new Thread(new MasterInputListener(masterTcpManager));
        commandListenerThread.start();
    }

}



