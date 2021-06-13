package GUI;

import Socket.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;

public class LogInController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Client curClient;
    @FXML private TextField id_text;


    @FXML
    public void login(ActionEvent e) throws IOException {
        String name=id_text.getText();
        if(id_text.getText().isEmpty() || id_text.getText().isBlank())
        {
            return;
        }
        Client client=new Client(name, InetAddress.getLocalHost(),15797);
        curClient=client;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChatClient.fxml"));
        root=loader.load();
        ChatClientController controller=loader.getController();
        controller.setClient(client);
        stage=(Stage) ((Node)e.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
