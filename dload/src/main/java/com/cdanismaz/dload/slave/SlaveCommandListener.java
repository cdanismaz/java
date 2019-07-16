package com.cdanismaz.dload.slave;

import java.util.Scanner;

public class SlaveCommandListener implements Runnable {
    private boolean shouldWork = true;

    public void run() {
        while (shouldWork) {
            Scanner sc = new Scanner(System.in);
            String command = sc.next();

            if (command.toLowerCase().equals("exit")) {
                this.shouldWork = false;
                terminateSlave();
            }
            else
                System.out.println("Unknown command");
        }
    }

    private void terminateSlave() {
        System.out.println("Terminating slave");
    }

}
