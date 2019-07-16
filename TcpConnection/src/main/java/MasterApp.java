import java.io.*;

public class MasterApp {

    public static PrintWriter pr = null;

    public static void main(String[] args) throws IOException {

        final TcpConnectionManager tcpConnectionManager = new TcpConnectionManager();
        Thread connectionManagerThread = new Thread(tcpConnectionManager);
        connectionManagerThread.start();

        Thread commandListenerThread = new Thread(new MasterCommandListener(tcpConnectionManager));
        commandListenerThread.start();
    }

}


