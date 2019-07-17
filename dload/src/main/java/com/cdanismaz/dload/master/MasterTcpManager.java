package com.cdanismaz.dload.master;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.ArrayList;

public class MasterTcpManager implements Runnable {

    private ServerSocket serverSocket;
    public ArrayList<Socket> socketList = new ArrayList<Socket>();
    private boolean shouldWork = true;

    public MasterTcpManager() throws IOException {
        this.serverSocket = new ServerSocket(6789);
    }

    public void run() {
        System.out.println("Connection manager thread started.");
        while (shouldWork) {
            System.out.println("Waiting for new connections");
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New connection: " + clientSocket.getRemoteSocketAddress() + ":" + clientSocket.getPort());
                socketList.add(clientSocket);
                System.out.println("Current client socket count: " + socketList.size());
            } catch (IOException e) {
                //e.printStackTrace();
            }

        }

        closeAllConnections();
    }

    private void closeAllConnections() {
        System.out.println("Closing all tcp connections");
        for (int i = 0; i < socketList.size(); i++) {
            try {
                this.socketList.get(i).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void terminate() {
        this.shouldWork = false;

        // We should close the socket here, otherwise even if we set the shouldWork to false,
        // this thread will continue to wait for new connections. Remember that the accept method
        // waits until there is a new connection or the server socket is closed
        try {
            this.sendCommand("exit\n");
            this.serverSocket.close();
        } catch (IOException e) {
            System.out.println("Cannot close server socket...");
            e.printStackTrace();
        }
    }

    public void sendCommand(String command) {
        for (int i = this.socketList.size()-1; i >= 0; i--) {
            final Socket socket = this.socketList.get(i);
            try {
            	// Check if the socket is closed, or its output stream is closed
                if (socket.isClosed() || socket.isOutputShutdown()) {
					System.out.println("Socket is closed. Removing socket from list");
					this.socketList.remove(socket);
					System.out.println("New socket count: " + socketList.size());
				} else {
					socket.getOutputStream().write(command.getBytes());
				}
				
//				BufferedWriter socketWriter;
//                socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//                socketWriter.write(command + "\n");
//                socketWriter.newLine();
            } catch (IOException e) {
                System.out.println("Cannot send message to slave. Removing socket from list");
                socketList.remove(socket);
                try {
                    if (socket.isClosed()) {
                        System.out.println("Socket is already closed");
                    } else {
                        socket.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.out.println("New socket count: " + socketList.size());
            }
        }
    }


}
