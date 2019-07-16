package com.cdanismaz.dload.slave;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class SlaveApp {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 6789);

        Thread slaveCommandListenerThread = new Thread(new SlaveCommandListener());
        slaveCommandListenerThread.start();


    }
}
