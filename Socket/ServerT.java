package Socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerT implements Runnable{

    private int port;

    private static ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();





    public ServerT(int port) {
        this.port = port;
    }



    public void run(){
        ServerSocket serverSocket;
        Socket socket;

        try {
            serverSocket = new ServerSocket(port);//8889
            while(true) {
                System.out.println("Waiting for clients...");
                socket = serverSocket.accept();
                System.out.println("Connected");
                ClientHandler clientThread = new ClientHandler(socket, clients);
                clients.add(clientThread);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
