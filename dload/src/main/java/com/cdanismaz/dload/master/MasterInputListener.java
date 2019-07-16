package com.cdanismaz.dload.master;

import java.util.Scanner;

public class MasterInputListener implements Runnable{
    private MasterTcpManager masterTcpManager;
    private Scanner scanner;
    private boolean shouldWork = true;

    public MasterInputListener(MasterTcpManager masterTcpManager) {
        this.masterTcpManager = masterTcpManager;
    }

    public void run() {
        while(shouldWork) {
            this.scanner = new Scanner(System.in);
            String command = scanner.next();

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
        this.masterTcpManager.sendCommand("start\n");
    }

    private void terminateApplication() {
        System.out.println("Terminating application");
        this.scanner.close();
        this.masterTcpManager.terminate();
    }
}
