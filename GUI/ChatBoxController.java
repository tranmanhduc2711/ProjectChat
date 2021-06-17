package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import javafx.scene.text.Text;
import Socket.Server;
import javafx.stage.Stage;

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
     private ComboBox<String> emojiBox;
    @FXML
    private Text    name_label;


    BufferedReader reader;
    PrintWriter writer;
    Socket socket;
    public static Server curServer;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name_label.setText(ChatClientController.curClient.getName());
        emojiBox.getItems().addAll("smile","sad","laugh","scare","bleh");
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
               if(msg!=null) {
                   String[] tokens = msg.split("\\s+");
                   String cmd = tokens[0];
                   System.out.println(cmd + "cmd");
                   StringBuilder fulmsg = new StringBuilder();
                   for (int i = 1; i < tokens.length; i++) {
                       fulmsg.append(tokens[i]);
                   }
                   System.out.println(fulmsg);
                   if (cmd.equalsIgnoreCase(ChatClientController.curClient.getName() + ":")) {
                       continue;
                   } else if (fulmsg.toString().equalsIgnoreCase("bye")) {
                       break;
                   }
                   chat_area.appendText(msg + "\n");
               }
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
       //System.out.println("name");
    }


    public void send() {
        String emoji=emoji();
        String msg = input_area.getText();
        msg+=emoji;
        System.out.println(msg);
        writer.println(ChatClientController.curClient.getName() + ": " + msg);
        chat_area.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        chat_area.appendText("Me: " + msg + "\n");
        input_area.setText("");
        if(msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);
        }
        emojiBox.getSelectionModel().clearSelection();
    }
    @FXML
    public String emoji()
    {
        String emo;
        String res=emojiBox.getSelectionModel().getSelectedItem();
        if(res==null)
            return "\0";
        if(res.equals("smile"))
        {
            return new String(Character.toChars(0x1F60A));
        }
        else if(res.equals("sad"))
        {
            return new String(Character.toChars(0x1F614));
        }
        else if(res.equals("laugh"))
        {
            return new String(Character.toChars(0x1F602));
        }
        else if(res.equals("scare"))
        {
            return new String(Character.toChars(0x1F628));
        }
        else if(res.equals("bleh"))
        {
            return new String(Character.toChars(0x1F61D));
        }
        return "\0";
    }
    @FXML
    public void exit_but(ActionEvent event)
    {
        Stage stage=new Stage();
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    public void webcam_but()
    {
        WebcamClass cam=new WebcamClass();
        cam.run();
    }

}
