package com.cdanismaz.dload.slave;

import com.cdanismaz.dload.master.TcpConnectionManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SlaveCommandListener implements Runnable {
    private boolean shouldWork = true;
    private TcpConnectionManager tcpConnectionManager;

    public void run() {
        while (shouldWork) {
            this.readCommand();
/**
            Scanner sc = new Scanner(System.in);
            String command = sc.next();

            if (command.toLowerCase().equals("exit")) {
                this.shouldWork = false;
                terminateSlave();
            } else if (command.toLowerCase().equals("start")) {
                this.runCommand();
            } else
                System.out.println("Unknown command");
        } **/
        }
    }

        public void readCommand() {
        for (int i = 0; i < tcpConnectionManager.socketList.size(); i++) {
            BufferedReader rd = null;
            try {
                rd = new BufferedReader(new InputStreamReader(tcpConnectionManager.socketList.get(i).getInputStream()));
                System.out.println(rd);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String str;
            try {
                while ((str = rd.readLine()) != null) {
                    System.out.println(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                rd.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void runCommand() {
        System.out.println("Sending command");
    }

    private void terminateSlave() {
        System.out.println("Terminating slave");
    }

}
