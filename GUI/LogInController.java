package GUI;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    public void login(ActionEvent e) throws IOException {
        String name=id_text.getText();
        int age=Integer.parseInt(age_text.getText());
        if(id_text.getText().isEmpty() || id_text.getText().isBlank())
        {
            return;
        }
        User user=new User(name,age);
        curClient=user;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChatClient.fxml"));
        root=loader.load();
        ChatClientController controller=loader.getController();
        controller.setClient(user);
        stage=(Stage) ((Node)e.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
