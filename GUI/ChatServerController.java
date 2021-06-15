package GUI;

import IOclass.IO;
import Socket.Server;
import Socket.ServerT;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChatServerController  implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableView<User> clientTable;
    @FXML
    private TableColumn<User,String> name_column;
    @FXML
    private TableColumn<User,Integer> age_column;
    @FXML
    private TableColumn<User,String> id_column;
   @FXML
    private Text sv_nameText;
   @FXML
    private Text port_Text;
   @FXML
    private Text status_Text;
   @FXML
   private Alert alert;

    public static Server curServer;
    private ObservableList<User> clientList = FXCollections.observableArrayList();
    private List<Server> dataServer;
    private Runnable r = new ServerT(curServer.getPort());
    private  Thread runsv=new Thread(r);

   public void setConfig(Server sv)
   {
       sv_nameText.setText(sv.getSv_name());
       port_Text.setText(Integer.toString(sv.getPort()));
       status_Text.setText(sv.getStatus());
   }
    private void setClientData() throws IOException, ClassNotFoundException {
       if(dataServer != null)
            dataServer.clear();
        dataServer= IO.docFileBinary("data.txt");
        if(dataServer.isEmpty())
            return;
        for(Server item:dataServer)
        {
            if(item.getPort() == curServer.getPort())
            {
                curServer=item;
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
        setServer();
    }
    private void setServer()
    {
        if(Main.list_Server!=null)
        {
            for(Server item:Main.list_Server)
            {
                if(item.getStatus().equals("Online"))
                {
                    Runnable r = new ServerT(item.getPort());
                    new Thread(r).start();
                }
            }
        }
    }

    @FXML
    public void refresh()
    {
        try {
            setClientData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(curServer.list_client==null)
            return;
        if(clientList!=null)
            clientList.clear();
        for(int i=0;i<curServer.list_client.size();i++)
        {
            clientList.add(curServer.list_client.get(i));
        }
        name_column.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        age_column.setCellValueFactory(new PropertyValueFactory<User, Integer>("age"));
        id_column.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
        clientTable.setItems(clientList);
    }
    @FXML
    public void openServer() throws IOException {
        System.out.println(curServer.getStatus());
        if(curServer.getStatus().equals("Offline"))
        {
            curServer.setStatus("Online");
            status_Text.setText(curServer.getStatus());
            for(Server item:Main.list_Server)
            {
                if(item.getPort()== curServer.getPort())
                {
                    item.setStatus("Online");
                    break;
                }
            }
            IO.luuFileBinary(Main.list_Server,"data.txt");
            //thread chay server
            runsv.start();

        }

    }
    @FXML
    public void closeServer() throws IOException {
        System.out.println(curServer.getStatus());
        if(curServer.getStatus().equals("Online"))
        {
            curServer.setStatus("Offline");
            status_Text.setText(curServer.getStatus());
            for(Server item:Main.list_Server)
            {
                if(item.getPort()== curServer.getPort())
                {
                    item.setStatus("Offline");
                    break;
                }
            }
            IO.luuFileBinary(Main.list_Server,"data.txt");
            runsv.interrupt();
        }
    }
    @FXML
    public void joinChatRoom(ActionEvent event) throws IOException {
        if(curServer.getStatus().equals("Offline"))
        {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Connection");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Server is offline!");
            alert.showAndWait();
            return;
        }

        ChatBoxController.curServer=curServer;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChatBox.fxml"));
        root=loader.load();

        ChatBoxController controller=loader.getController();

        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
