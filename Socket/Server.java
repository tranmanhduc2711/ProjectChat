package Socket;

import GUI.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// class luu thong tin server
public class Server implements Serializable {

    private String sv_name;
    private int port;
    private String status;
    public List<User> list_client;
    public Server(String name,int port)
    {
        this.sv_name=name;
        this.port=port;
        this.status="Offline";
        list_client=new ArrayList<>();
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


