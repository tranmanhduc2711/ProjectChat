package GUI;


import IOclass.IO;
import Socket.Server;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChatClientController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableView serverTable;
    @FXML
    private TableColumn server_column;
    @FXML
    private TableColumn port_column;
    @FXML
    private TableColumn status_column;
    @FXML
    private TextField nameText;
    @FXML
    private TextField id_text;
    @FXML
    private TextField age_text;
    @FXML
    private Text name_label;
    @FXML
    private Alert alert;

    public static User curClient;
    private ObservableList<Server> serverList = FXCollections.observableArrayList();
    public static ArrayList<User> users = new ArrayList<User>();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        refresh();
        name_label.setText(curClient.getName());
    }

    @FXML
    public void refresh()
    {
        if(serverList != null)
        {
            serverList.clear();
        }

        serverList.addAll(Main.list_Server);//?

        server_column.setCellValueFactory(new PropertyValueFactory<Server, String>("sv_name"));
        port_column.setCellValueFactory(new PropertyValueFactory<Server, Integer>("port"));
        status_column.setCellValueFactory(new PropertyValueFactory<Server,String>("status"));
        serverTable.setItems(serverList);
    }

    @FXML
    public void addServer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addServer.fxml"));
        loader.load();
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        addServerController controller = loader.getController();
        controller.init();
        scene = new Scene(loader.getRoot());
        stage=new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    @FXML
    public void deleteServer(ActionEvent e) {
        Server server = (Server) serverTable.getSelectionModel().getSelectedItem();
        if (server == null)
            return;
        serverList.remove(server);
        Main.list_Server.remove(server);
        refresh();
    }
    @FXML
    public void connectServer(ActionEvent event) throws IOException {
        Server server = (Server) serverTable.getSelectionModel().getSelectedItem();
        if (server == null)
            return;

        server.list_client.add(curClient);//them client vao server
        IO.luuFileBinary(Main.list_Server,"data.txt");

        ChatServerController.curServer=server;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChatServer.fxml"));
        loader.load();

        ChatServerController controller = loader.getController();
        controller.setConfig(server);

        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader.getRoot());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void addTabSelected()
    {
        nameText.clear();
        id_text.clear();
        age_text.clear();
    }
    @FXML
    public void addUser() throws IOException {
        String name=nameText.getText() ;
        String id=id_text.getText();
        int age=Integer.parseInt(age_text.getText());
        if(nameText.getText().isEmpty() || nameText.getText().isBlank()||id_text.getText().isEmpty() || id_text.getText().isBlank())
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
        IO.luuFileUser(Main.list_user,"user.txt");
    }
}
