package GUI;

import Socket.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    public void submit(ActionEvent event)
    {
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
            Server server=new Server(name,port);
            Main.list_Server.add(server);
            System.out.println(Main.list_Server.get(0).getSv_name());
            Stage stage=new Stage();
            stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.close();
        }
    }
}
