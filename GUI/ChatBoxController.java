package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import javafx.scene.text.Text;
import Socket.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;


public class ChatBoxController extends Thread  implements Initializable  {
    @FXML
    private TextArea chat_area;
    @FXML
    private TextArea input_area;
    @FXML
    private ComboBox emojiBox;
    @FXML
    private Text    name_label;

    BufferedReader reader;
    PrintWriter writer;
    Socket socket;
    public static Server curServer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name_label.setText("Tran Manh Duc");
        connectSocket();
    }
    public void connectSocket() {
        try {
            socket = new Socket("localhost", curServer.getPort());
            System.out.println("Socket is connected with server!");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try {
            while (true) {
                String msg = reader.readLine();
                String[] tokens = msg.split("\\s+");
                String cmd = tokens[0];
                System.out.println(cmd);
                StringBuilder fulmsg = new StringBuilder();
                for(int i = 1; i < tokens.length; i++) {
                    fulmsg.append(tokens[i]);
                }
                System.out.println(fulmsg);
                if (cmd.equalsIgnoreCase(  ChatClientController.curClient.getName()+":")) {
                    continue;
                } else if(fulmsg.toString().equalsIgnoreCase("bye")) {
                    break;
                }
                chat_area.appendText(msg + "\n");
            }
            reader.close();
            writer.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void handleSendEvent(ActionEvent event) {
        send();
        System.out.println("name");

    }


    public void send() {
        String msg = input_area.getText();
        writer.println("username" + ": " + msg);
        chat_area.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        chat_area.appendText("Me: " + msg + "\n");
        input_area.setText("");
        if(msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);
        }
    }

}
