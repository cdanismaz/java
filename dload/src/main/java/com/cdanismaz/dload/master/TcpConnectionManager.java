package com.cdanismaz.dload.master;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class TcpConnectionManager implements Runnable {

    private ServerSocket serverSocket;
    public ArrayList<Socket> socketList = new ArrayList<Socket>();
    private boolean shouldWork = true;

    public TcpConnectionManager() throws IOException {
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
        for(int i = 0; i< socketList.size(); i++) {
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
            this.serverSocket.close();
        } catch (IOException e) {
            System.out.println("Cannot close server socket...");
            e.printStackTrace();
        }
    }


}
