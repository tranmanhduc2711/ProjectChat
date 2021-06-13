package Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server implements Serializable {

    private String sv_name;
    private int port;
    private String status;
    public  List<Client> listSK;
    public static List<Socket> listClient;






    public Server(String name,int port)
    {
        this.sv_name=name;
        this.port=port;
        this.status="Offline";
        listSK=new ArrayList<>();
    }
    private void execute() throws IOException {
        ServerSocket server = new ServerSocket(port);
//        WriteServer write= new WriteServer();
//        write.start();
        System.out.println("Server is listening...");
        while(true)
        {
            Socket client=server.accept();
            System.out.println("Da ket noi voi "+client);
            listClient.add(client);
            ReadClient read=new ReadClient(client);
            read.start();
        }
    }



    public String getSv_name() {
        return sv_name;
    }

    public void setSv_name(String sv_name) {
        this.sv_name = sv_name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
class ReadServer extends Thread{
    private Socket server;

    public ReadServer(Socket server)
    {
        this.server=server;
    }
    @Override
    public void run() {
        DataInputStream dis=null;
        try{
            dis=new DataInputStream(server.getInputStream());
            while(true)
            {
                String sms = dis.readUTF();
                for(Socket item: Server.listClient)
                {
                    DataOutputStream dos= new DataOutputStream(item.getOutputStream());
                    dos.writeUTF(sms);
                }
                System.out.println(sms);
            }

        }catch(Exception e)
        {
            try {
                dis.close();
                server.close();
            } catch (IOException ioException) {
                System.out.println("Ngat ket noi!");
            }
        }
    }
}
//class WriteServer extends Thread{
//
//    @Override
//    public void run()
//    {
//        DataOutputStream dos=null;
//        Scanner sc=new Scanner(System.in);
//        while(true)
//        {
//            String sms=sc.nextLine();
//            try {
//                for (Socket item : Server.listSK) {
//
//                    dos = new DataOutputStream(item.getOutputStream());
//                    dos.writeUTF(sms);
//                }
//            }catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//        }
//    }
//}
