package com.cdanismaz.dload.slave;

import com.cdanismaz.dload.common.ClosableThread;
import com.cdanismaz.dload.common.ThreadManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SlaveInputListener implements Runnable, ClosableThread{
    private boolean shouldWork = true;
    private ThreadManager threadManager;
    private BufferedReader inputReader;
    private InputStreamReader inputStreamReader;

    public SlaveInputListener(ThreadManager threadManager) {
        this.threadManager = threadManager;
        this.inputStreamReader = new InputStreamReader(System.in);
        this.inputReader = new BufferedReader(this.inputStreamReader);
    }

    public void run() {
        while (shouldWork) {
            try {
                String command = inputReader.readLine();
                if (command.toLowerCase().equals("exit")) {
                    this.threadManager.terminate();
                } else
                    System.out.println("Unknown command");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void terminate() {
        System.out.println("Terminating slave");
        this.shouldWork = false;
        try {
            this.inputReader.close();
            this.inputStreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
