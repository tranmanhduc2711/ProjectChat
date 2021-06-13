package Socket;

import java.io.DataInputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private InetAddress host;
    private int port;

    public Client(InetAddress host, int port)
    {
        this.host=host;
        this.port=port;
    }

    private void execute()
    {

    }
}

class ReadClient extends Thread{
    private Socket client;

    public ReadClient(Socket client)
    {
        this.client=client;
    }
    @Override
    public void run() {
        DataInputStream dis=null;

    }
}