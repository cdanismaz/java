package com.cdanismaz.dload.slave;

import com.cdanismaz.dload.master.TcpConnectionManager;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class SlaveApp {
/**
    Socket socket;
    String server = "localhost";
    int port = 6789; **/
    private TcpConnectionManager tcpConnectionManager;

    public static void main(String[] args) throws IOException {
        //new SlaveApp();
        Socket socket = new Socket("localhost", 6789);

        Thread slaveCommandListenerThread = new Thread(new SlaveCommandListener());
        slaveCommandListenerThread.start();


    }
/**
    public SlaveApp() {
        openSocket();
    }

    private void openSocket() {
        try {
            InetAddress addr = InetAddress.getByName(server);
            SocketAddress socketAddr = new InetSocketAddress(addr, port);
            socket = new Socket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }**/

}
