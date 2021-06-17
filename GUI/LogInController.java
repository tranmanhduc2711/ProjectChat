package GUI;


import IOclass.IO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class LogInController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private User curClient;
    @FXML private TextField id_text;

    @FXML
    private Alert alert;



    @FXML
    public void login(ActionEvent e) throws IOException {

        String id=id_text.getText();
        if(id_text.getText().isEmpty() || id_text.getText().isBlank())
        {
            return;
        }
        boolean check=false;
        if(Main.list_user.isEmpty()==false) {
            for (User item : Main.list_user) {
                if (item.getId().equals(id)) {
                    curClient=item;
                    check = true;
                    break;
                }
            }
        }
        if (check == false) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("LogIn");
            alert.setHeaderText(null);
            alert.setContentText("User not found!");
            alert.showAndWait();
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChatClient.fxml"));
        ChatClientController.curClient=curClient;
        root=loader.load();
        ChatClientController controller=loader.getController();

        stage=(Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void signUpUser(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signUpUser.fxml"));
        root=loader.load();
        stage=(Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage =new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
