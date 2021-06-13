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
    @FXML private TextField id_text;


    @FXML
    public void login(ActionEvent e) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentFeatureTable.fxml"));
        root=loader.load();
        stage=(Stage) ((Node)e.getSource()).getScene().getWindow();
//        StudentFeatureController controller = loader.getController();
//        controller.setUser(item);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
