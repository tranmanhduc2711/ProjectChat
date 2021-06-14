package GUI;

import IOclass.IO;
import Socket.Server;
import Socket.ServerT;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class addServerController {
    @FXML
    private TextField nameText;
    @FXML
    private TextField portText;
    @FXML
    private Alert alert;


   public void init()
   {
       nameText.clear();
       portText.clear();
   }
    @FXML
    public void submit(ActionEvent event) throws IOException {
        String name=nameText.getText();

        int port=Integer.parseInt(portText.getText());
        if(name == null || portText.getText() ==null  )
        {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning!");
            alert.setContentText("Điền thiếu thông tin!");
            alert.showAndWait();
        }
        else{
            if(Main.list_Server !=null) {
                for (Server item : Main.list_Server) {
                    if(item.getPort()==port)
                    {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Warning!");
                        alert.setContentText("Port đã tồn tại!");
                        alert.showAndWait();
                        return;
                    }
                }
            }
            //thread chay server moi duoc tao
            Runnable r = new ServerT(port);
            new Thread(r).start();


            Server sv=new Server(name,port);
            Main.list_Server.add(sv);
            //luu server
            IO.luuFileBinary(Main.list_Server,"data.txt");

            Stage stage=new Stage();
            stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.close();
        }
    }
}
