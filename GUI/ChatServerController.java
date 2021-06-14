package GUI;

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
    private TableView<User> clientTable;
    @FXML
    private TableColumn<User,String> name_column;
    @FXML
    private TableColumn<User,Integer> age_column;
   @FXML
    private Text sv_nameText;
   @FXML
    private Text port_Text;
   @FXML
    private Text status_Text;

    public static Server curServer;
    private ObservableList<User> clientList = FXCollections.observableArrayList();

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
        clientTable.setItems(clientList);
    }
}
