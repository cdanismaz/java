package com.cdanismaz.dload.slave;

import com.cdanismaz.dload.common.ClosableThread;
import com.cdanismaz.dload.common.ThreadManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class SlaveTcpListener implements Runnable,ClosableThread {
    private boolean shouldWork = true;
    private Socket socket = null;
    private ThreadManager threadManager;
    private BufferedReader bufferedReader;
    private InputStreamReader inputStreamReader;

    public SlaveTcpListener(ThreadManager threadManager) {
        this.threadManager = threadManager;
        try {
            this.socket = new Socket("localhost", 6789);
            this.inputStreamReader = new InputStreamReader(this.socket.getInputStream());
            this.bufferedReader = new BufferedReader(this.inputStreamReader);
            System.out.println("Connected to master");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (shouldWork) {
            System.out.println("Waiting for new command from master");
            try {
                final String commandFromMaster = this.bufferedReader.readLine();
                if (commandFromMaster.toLowerCase().equals("start")) {
                    System.out.println("Starting load test");
                }
                else if (commandFromMaster.toLowerCase().equals("exit")) {
                    this.threadManager.terminate();
                }
                else
                    System.out.println("Unknown command from master: " + commandFromMaster);
            } catch (SocketException e) {
                System.out.println("Socket closed");
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
            this.bufferedReader.close();
            this.inputStreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
