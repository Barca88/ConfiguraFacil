package configuraFacil.presentation.controllers;

import configuraFacil.business.models.items.Item;
import configuraFacil.business.ConfiguraFacil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;


public class StockController {
    ConfiguraFacil cf;

    @FXML
    private TableView<Item> tblStock;
    @FXML
    private TableColumn<Item,Integer> clnIdItem;
    @FXML
    private TableColumn<Item,Integer> clnNome;
    @FXML
    private TableColumn<Item,Integer> clnQuantidade;
    @FXML
    private TableColumn<Item,Integer> clnPreco;
    @FXML
    private TextField tfQuantidade;
    @FXML
    private Button btnEncomendar;

    public void init(ConfiguraFacil cfo) {
        cf = cfo;
        initTable();

        if(cf.getLogged().getClass().getSimpleName().equals("Fabricante")){
            tfQuantidade.setVisible(false);
            btnEncomendar.setVisible(false);
        }
    }

    private void initTable(){
        clnIdItem.setCellValueFactory(new PropertyValueFactory<>("id"));
        clnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clnQuantidade.setCellValueFactory(new PropertyValueFactory<>("stock"));
        clnPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tblStock.setItems(cf.getItems());
    }


    public void handleBtnBack(ActionEvent actionEvent) throws IOException {
        URL url;
        if(cf.getLogged().getClass().getSimpleName().equals("Administrador")) {
            url = getClass().getResource("../views/administrador.fxml");
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            SceneManager sm = new SceneManager(url, window);
            sm.newScene(1, cf);
        } else if(cf.getLogged().getClass().getSimpleName().equals("Fabricante")) {
            url = getClass().getResource("../views/fabricante.fxml");
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            SceneManager sm = new SceneManager(url, window);
            sm.newScene(2, cf);
        }
    }

    public void handleBtnEncomendar(ActionEvent actionEvent) throws IOException{
        try{

            if(tblStock.getSelectionModel().getSelectedItem() != null) {
                Item i = tblStock.getSelectionModel().getSelectedItem();
                int quantidade = Integer.parseInt(tfQuantidade.getText());

                if (quantidade <= 0) {
                    AlertBox.alert("Quantidade inválida", "Por favor, introduza uma quantidade coerente para encomenda");
                }

                if (quantidade > 0 && (i != null)) {
                    i.setStock(i.getStock() + quantidade);
                    cf.encomenda(i);

                    initTable();
                }
            }else{
                AlertBox.alert("Item inválido", "Por favor, selecione um item para encomenda");
            }
        }catch(NumberFormatException e){
            e.getMessage();
        }
    }
}
