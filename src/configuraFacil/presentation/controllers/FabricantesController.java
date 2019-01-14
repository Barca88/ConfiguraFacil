package configuraFacil.presentation.controllers;


import configuraFacil.business.ConfiguraFacil;
import configuraFacil.business.models.users.Utilizador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;


public class FabricantesController {

    @FXML
    private TableView<Utilizador> tblFabricante;
    @FXML
    private TableColumn<Utilizador,Integer> clnId;
    @FXML
    private TableColumn<Utilizador,Integer> clnNome;
    @FXML
    private TableColumn<Utilizador,Integer> clnEmail;
    @FXML
    private TableColumn<Utilizador,String> clnTelemovel;

    ConfiguraFacil cf;

    public void init(ConfiguraFacil cfo) {
        cf = cfo;
        initTable();
    }

    private void initTable(){
        clnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clnTelemovel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        tblFabricante.setItems(cf.conssultarFabricantes());
    }

    public void handleBtnBack(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getResource("../views/administrador.fxml");
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        SceneManager sm = new SceneManager(url, window);
        sm.newScene(1, cf);
    }
}
