import java.io.*;
import java.util.*;

public class MasterApp {

    public static PrintWriter pr = null;

    public static void main(String[] args) throws IOException {

        final TcpConnectionManager tcpConnectionManager = new TcpConnectionManager();
        Thread connectionManagerThread = new Thread(tcpConnectionManager);
        connectionManagerThread.start();

        Thread commandListenerThread = new Thread(new CommandListener(tcpConnectionManager));
        commandListenerThread.start();
    }

}



