package Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private String name;
    private InetAddress host;
    private int port;

    public Client(String name,InetAddress host, int port)
    {
        this.name=name;
        this.host=host;
        this.port=port;
    }

    public void execute() throws IOException {
        Socket client= new Socket(host,port);
        ReadClient read= new ReadClient(client);
        read.start();
        WriteClient write= new WriteClient(client);
        write.start();
    }

    public static void main(String[] args) throws IOException {
//        Client client=new Client(InetAddress.getLocalHost(),15797);
//        client.execute();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InetAddress getHost() {
        return host;
    }

    public void setHost(InetAddress host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
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
        try{
            dis=new DataInputStream(client.getInputStream());
            while(true)
            {
               String sms= dis.readUTF();
               System.out.println(sms);
            }
        }catch(Exception e)
        {
            try {
                dis.close();
                client.close();
            } catch (IOException ioException) {
                System.out.println("Ngat ket noi!");
            }
        }
   }
}
class WriteClient extends Thread{
    private Socket client;

    public WriteClient(Socket client)
    {
        this.client=client;
    }
    @Override
    public void run()
    {
        DataOutputStream  dos=null;
        Scanner sc=null;
        try {
            dos=new DataOutputStream(client.getOutputStream());
            sc=new Scanner(System.in);
            while(true)
            {
                String sms=sc.nextLine();
                dos.writeUTF(sms);
            }
        }catch (Exception e)
        {
            try {
                dos.close();
                client.close();
            } catch (IOException ioException) {
                System.out.println("Ngat ket noi!");
            }
        }
    }
}