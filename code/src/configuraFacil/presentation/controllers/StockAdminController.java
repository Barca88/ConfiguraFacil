package configuraFacil.presentation.controllers;

import configuraFacil.business.models.items.Item;
import configuraFacil.presentation.controllers.SceneManager;
import configuraFacil.business.ConfiguraFacil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class StockAdminController {
    ConfiguraFacil cf;

    @FXML
    private TableView<Item> tblStockAdmin;
    @FXML
    private TableColumn<Item,Integer> clnIdItem;
    @FXML
    private TableColumn<Item,Integer> clnNome;
    @FXML
    private TableColumn<Item,Integer> clnQuantidade;
    @FXML
    private TextField tfIdConfig;
    @FXML
    private TextField tfEstadoConfig;


    public void init(ConfiguraFacil cfo) {
        cf = cfo;
        initTable();
    }

    private void initTable(){
        clnIdItem.setCellValueFactory(new PropertyValueFactory<>("id"));
        clnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clnQuantidade.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tblStockAdmin.setItems(cf.getItems());
    }


    public void handleBtnBack(ActionEvent actionEvent) throws IOException {

        URL url =  getClass().getResource("../views/administrador.fxml");
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        SceneManager sm = new SceneManager(url, window);
        sm.newScene(1, cf);

    }
}
