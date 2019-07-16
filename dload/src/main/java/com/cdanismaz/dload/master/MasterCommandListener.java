package com.cdanismaz.dload.master;

import java.util.Scanner;

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
                this.startLoadTest(command);
            }

            else
                System.out.println("Unknown command");
        }
    }

    private void startLoadTest(String command) {
        System.out.println("Commencing load test");
        this.tcpConnectionManager.sendCommand(command);
    }

    private void terminateApplication() {
        System.out.println("Terminating application");
        this.tcpConnectionManager.terminate();
    }
}
