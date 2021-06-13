package StoreConfig;

import GUI.Main;
import Socket.Server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class WriteFileConfig {
    public static void writeConfig()
    {
        Properties prop = new Properties();
        OutputStream output = null;
        try {
            output = new FileOutputStream("config.properties");

            // set the properties value
            for(Server item:Main.list_Server) {
                prop.setProperty("remote.server", "192.168.1.100");
                prop.setProperty("remote.server.port", "8080");
                prop.setProperty("remote.user", "vuta");
                prop.setProperty("remote.password", "bimat");
            }
            // save properties to project root folder
            prop.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    }

}
