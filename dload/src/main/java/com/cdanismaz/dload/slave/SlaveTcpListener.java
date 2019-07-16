package com.cdanismaz.dload.slave;

import com.cdanismaz.dload.common.ClosableThread;
import com.cdanismaz.dload.common.ThreadManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SlaveTcpListener implements Runnable,ClosableThread {
    private boolean shouldWork = true;
    private Socket socket = null;
    private ThreadManager threadManager;

    public SlaveTcpListener(ThreadManager threadManager) {
        this.threadManager = threadManager;
        try {
            this.socket = new Socket("localhost", 6789);
            System.out.println("Connected to master");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (shouldWork) {
            System.out.println("Waiting for new command from master");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
                final String commandFromMaster = reader.readLine();
                if (commandFromMaster.toLowerCase().equals("start")) {
                    System.out.println("Starting load test");
                }
                else if (commandFromMaster.toLowerCase().equals("exit")) {
                    this.threadManager.terminate();
                }
                else
                    System.out.println("Unknown command from master: " + commandFromMaster);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void terminate() {
        try {
            this.shouldWork = false;
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
