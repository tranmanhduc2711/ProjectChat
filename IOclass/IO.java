package IOclass;

import GUI.User;
import Socket.Server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IO {
    public static void luuFileBinary(List<Server> list, String filename) throws IOException {
        FileOutputStream fout = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fout);

        oos.write(list.size());
        for (int i = 0; i < list.size(); i++) {
            oos.writeObject(list.get(i));
        }
        oos.close();
        fout.close();
    }

    public static List<Server> docFileBinary(String filename) throws IOException, ClassNotFoundException {
        File f = new File(filename);
        if (f.exists() && f.length()!=0) {
            FileInputStream fin = new FileInputStream(f.getPath());
            ObjectInputStream ois = new ObjectInputStream(fin);

            int stt = ois.read();
            List<Server> list = new ArrayList<Server>();
            for (int i = 0; i < stt; i++) {
                Server tmp = (Server) ois.readObject();
                list.add(tmp);
            }
            return list;
        }
        return new  ArrayList<>();
    }
    public static void luuFileUser(List<User> list, String filename) throws IOException {
        FileOutputStream fout = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fout);

        oos.write(list.size());
        for (int i = 0; i < list.size(); i++) {
            oos.writeObject(list.get(i));
        }
        oos.close();
        fout.close();
    }

    public static List<User> docFileUser(String filename) throws IOException, ClassNotFoundException {
        File f = new File(filename);
        if (f.exists() && f.length()!=0) {
            FileInputStream fin = new FileInputStream(f.getPath());
            ObjectInputStream ois = new ObjectInputStream(fin);

            int stt = ois.read();
            List<User> list = new ArrayList<>();
            for (int i = 0; i < stt; i++) {
                User tmp = (User) ois.readObject();
                list.add(tmp);
            }
            return list;
        }
        return new  ArrayList<>();
    }


}
