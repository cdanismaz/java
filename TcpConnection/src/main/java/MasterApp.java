import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class MasterApp {
    public static ArrayList<Socket> socketList = new ArrayList<Socket>();
    public static PrintWriter pr = null;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6789);

        while (true) {
            System.out.println("Waiting for the client request");
            Socket clientsocket = serverSocket.accept();
            socketList.add(clientsocket);
            System.out.println("New connection: " + clientsocket.getRemoteSocketAddress() + ":" + clientsocket.getPort());
            System.out.println("Current clientsocket count: " + socketList.size());

                for (int i = 0; i < socketList.size(); i++) {
                    if (socketList.get(i).isClosed()) {
                        socketList.remove(i);
                        System.out.println("Socket removed from the socket list. Current socket count: " + socketList.size());
                    } else
                        System.out.println("socket kaldırılamadı.");
                }

        }

    }

}



/**
            InputStreamReader in = new InputStreamReader(clientsocket.getInputStream());
            BufferedReader bf = new BufferedReader(in);
            System.out.println("Enter command: ");
            if(bf.readLine().toLowerCase() == "start") {
                startLoadTest();
                bf.close();
            }

            else if (bf.readLine().toLowerCase() == "exit") {
                System.out.println("Terminating application");
                closeClients();
                bf.close();
                serverSocket.close();
            }
            else
                System.out.println("Unknown command");


            String str = bf.readLine();
            System.out.println("client says : " + str);
        }

    }

    private static void closeClients() throws IOException {
        for(int i = 0; i < socketList.size(); i++) {
            pr = new PrintWriter(socketList.get(i).getOutputStream(), true);
            BufferedReader bf = new BufferedReader(new InputStreamReader(socketList.get(i).getInputStream()));
            pr.println("terminate");
            pr.flush();
        }
    }

    private static void startLoadTest() throws IOException {
        for(int i = 0; i < socketList.size(); i++) {
            pr = new PrintWriter(socketList.get(i).getOutputStream(), true);
            BufferedReader bf = new BufferedReader(new InputStreamReader(socketList.get(i).getInputStream()));
            pr.println("start");
            pr.flush();
        }
    }

         String clientSentence;
         String capitalizedSentence;
         ServerSocket serverSocket = new ServerSocket(6789);

         while(true) {
         Socket connection = serverSocket.accept();
         InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());

         BufferedReader inFromClient = new BufferedReader(inputStreamReader);

         DataOutputStream outToClient = new DataOutputStream(connection.getOutputStream());
         clientSentence = inFromClient.readLine();

         System.out.println("Received: " + clientSentence);
         capitalizedSentence = clientSentence.toUpperCase() + 'n';
         outToClient.writeBytes(capitalizedSentence);
         }





 **/


