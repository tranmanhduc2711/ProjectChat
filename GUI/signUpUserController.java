package GUI;

import IOclass.IO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class signUpUserController {
    @FXML
    private TextField nameText;
    @FXML
    private TextField idText;
    @FXML
    private TextField ageText;
    @FXML
    private Alert alert;

    @FXML
    public void addUser(ActionEvent e) throws IOException {
        String name=nameText.getText() ;
        String id=idText.getText();
        int age=Integer.parseInt(ageText.getText());
        if(nameText.getText().isEmpty() || nameText.getText().isBlank()||idText.getText().isEmpty() || idText.getText().isBlank())
        {
            return;
        }
        if(Main.list_user.isEmpty()==false) {
            for (User item : Main.list_user) {
                if (item.getId().equals(id)) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("LogIn");
                    // Header Text: null
                    alert.setHeaderText(null);
                    alert.setContentText("User already exists!");
                    alert.showAndWait();
                    return;
                }
            }
        }
        User user=new User(id,name,age);
        Main.list_user.add(user);
        IO.luuFileUser(Main.list_user,"user.txt");
        Stage stage;
        stage=(Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }
}
