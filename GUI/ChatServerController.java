package GUI;

import Socket.Client;
import Socket.Server;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatServerController  implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableView clientTable;
    @FXML
    private TableColumn name_column;
    @FXML
    private TableColumn port_column;
   @FXML
    private Text sv_nameText;
   @FXML
    private Text port_Text;
   @FXML
    private Text status_Text;

    public static Server curServer;
    private ObservableList<Client> clientList = FXCollections.observableArrayList();

   public void setConfig(Server sv)
   {

       sv_nameText.setText(sv.getSv_name());
       port_Text.setText(Integer.toString(sv.getPort()));
       status_Text.setText(sv.getStatus());
   }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
    }
    @FXML
    public void refresh()
    {
        if(curServer.listSK==null)
            return;
        if(clientList!=null)
            clientList.clear();
        for(int i=0;i<curServer.listSK.size();i++)
        {
            clientList.add(curServer.listSK.get(i));
        }
        name_column.setCellValueFactory(new PropertyValueFactory<Server, String>("name"));
        port_column.setCellValueFactory(new PropertyValueFactory<Server, Integer>("port"));
        clientTable.setItems(clientList);
    }
}
