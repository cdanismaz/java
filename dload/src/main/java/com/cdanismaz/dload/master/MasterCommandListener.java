package com.cdanismaz.dload.master;

import java.util.Scanner;
import java.io.*;
import java.net.*;

public class MasterCommandListener implements Runnable{
    private TcpConnectionManager tcpConnectionManager;
    private boolean shouldWork = true;

    public MasterCommandListener(TcpConnectionManager tcpConnectionManager) {
        this.tcpConnectionManager = tcpConnectionManager;
    }

    public void run() {
        while(shouldWork) {
            Scanner sc = new Scanner(System.in);
            String command = sc.next();

            if (command.toLowerCase().equals("exit")) {
                this.shouldWork = false;
                terminateApplication();
            }

            else if(command.toLowerCase().equals("start")) {
                this.startLoadTest();
            }

            else
                System.out.println("Unknown command");
        }
    }

    private void startLoadTest() {
        System.out.println("Commencing load test");
    }

    private void terminateApplication() {
        System.out.println("Terminating application");
        this.tcpConnectionManager.terminate();
    }
}
