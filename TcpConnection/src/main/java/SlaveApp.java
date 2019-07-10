import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class SlaveApp {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 6789);
/**
        PrintWriter pr = new PrintWriter(socket.getOutputStream());
        pr.println("is it working");
        pr.flush();
**/
        InputStreamReader in = new InputStreamReader(socket.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        String str = bf.readLine();
        System.out.println("server says : " + str);

    }

        /**
        String sentence;
        String modifiedSentence;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket("localhost", 6789);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        sentence = inFromUser.readLine();
        outToServer.writeBytes(sentence + 'n');
        modifiedSentence = inFromServer.readLine();
        System.out.println("FROM SERVER: " + modifiedSentence);
        clientSocket.close();
         **/

}
