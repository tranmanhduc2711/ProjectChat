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
    private TextField age_text;
    @FXML
    private TextField name_text;
    @FXML
    private Alert alert;


    @FXML
    public void login(ActionEvent e) throws IOException {
        String name=name_text.getText();
        String id=id_text.getText();
        int age=Integer.parseInt(age_text.getText());
        if(name_text.getText().isEmpty() || name_text.getText().isBlank()||id_text.getText().isEmpty() || id_text.getText().isBlank())
        {
            return;
        }
//        boolean check=false;
//        if(Main.list_user.isEmpty()==false) {
//            for (User item : Main.list_user) {
//                if (item.getId().equals(id)) {
//                    check = true;
//                    break;
//                }
//            }
//        }
//        if (check == false) {
//            alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("LogIn");
//            // Header Text: null
//            alert.setHeaderText(null);
//            alert.setContentText("User not found!");
//            alert.showAndWait();
//            return;
//        }
        User user=new User(id,name,age);
        IO.luuFileUser(Main.list_user,"user.txt");
        curClient=user;
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
}
