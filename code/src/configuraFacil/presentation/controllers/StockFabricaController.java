package configuraFacil.presentation.controllers;

import configuraFacil.business.ConfiguraFacil;
import configuraFacil.business.models.items.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;

public class StockFabricaController {

    private ConfiguraFacil cf;

    private Map<Integer,Integer> encomenda;

    @FXML
    private TableView<Item> tblStockFabricante;
    @FXML
    private TableColumn<Item,Integer> clnIdStock;
    @FXML
    private TableColumn<Item,Integer> clnNomeStock;
    @FXML
    private TableColumn<Item,Integer> clnTipoStock;
    @FXML
    private TableColumn<Item,String> clnStockStock;
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfQuantidade;
    @FXML
    private Label lbErro;

    public void init(ConfiguraFacil cfo) {
        cf = cfo;
        encomenda = new Hashtable<>();
        initTable();
    }

    private void initTable(){
        clnIdStock.setCellValueFactory(new PropertyValueFactory<>("id"));
        clnNomeStock.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clnTipoStock.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        clnStockStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        //tblStockFabricante.setItems(cf.getConfiguracoes());
    }
    public void handlerBtnAdicionStock(ActionEvent actionEvent) throws NumberFormatException{
        try {
            lbErro.setText("");
            int id = Integer.parseInt(tfId.getText());
            int quantidade = Integer.parseInt(tfQuantidade.getText());
            int aux;
            if (encomenda.containsKey(id)) {
                aux = encomenda.get(id);
                encomenda.replace(id, quantidade + aux);
            } else {
                encomenda.put(id, quantidade);
            }
        } catch(NumberFormatException e){
            lbErro.setText("Numeros Errados!");
        }
    }
    public void handlerBtnFinalizarStock(){
        lbErro.setText("");
        //todo
        return;
    }

    public void handleBtnBack(ActionEvent actionEvent) throws IOException {

        URL url =  getClass().getResource("../views/fabricante.fxml");
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        SceneManager sm = new SceneManager(url, window);
        sm.newScene(2, cf);

    }
}
