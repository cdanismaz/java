package com.cdanismaz.dload.slave;

import com.cdanismaz.dload.common.ClosableThread;
import com.cdanismaz.dload.common.ThreadManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.List;

public class SlaveTcpListener implements Runnable, ClosableThread {
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

                if (commandFromMaster.toLowerCase().equals("exit")) {
                    System.out.println("Exiting");
                    this.threadManager.terminate();
                }
                else {
                    System.out.println("Starting load test");
                    runCommand(commandFromMaster);
                }

            } catch (SocketException e) {
                System.out.println("Socket closed");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
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

    public void runCommand(String command) {
        List<String> commandList = Arrays.asList(command.split(" "));

        ProcessBuilder builder = new ProcessBuilder();
        builder.command(commandList);
        builder.redirectErrorStream(true);
        Process process = null;
        try {
            process = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Process p = Runtime.getRuntime().exec("cd /Users/cdanismaz/Documents/workspace/dlp-generator");

        BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        try {
            while (true) {
                line = r.readLine();
                if (line == null) { break; }
                System.out.println(line);
            }
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
