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
    private Label labID;
    @FXML
    private TextField txtID;
    @FXML
    private Label labQuantidade;
    @FXML
    private TextField txtQuantidade;
    @FXML
    private Button btnEncomendar;

    public void init(ConfiguraFacil cfo) {
        cf = cfo;
        initTable();

        if(cf.getLogged().getClass().getSimpleName().equals("Administrador")){
            labID.setVisible(true);
            labQuantidade.setVisible(true);
            txtID.setVisible(true);
            txtQuantidade.setVisible(true);
            labID.setVisible(true);
            btnEncomendar.setVisible(true);
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
        int item_id = Integer.parseInt(txtID.getText());
        int quantidade = Integer.parseInt(txtQuantidade.getText());
        Item it = cf.getItems().stream().filter(i -> (i.getId() == item_id)).findAny().orElse(null);

        if(quantidade <= 0 ) {
            AlertBox.alert("Quantidade inválida", "Por favor, introduza uma quantidade coerente para encomenda");
        }

        if(item_id < 0 || (it==null)){
            AlertBox.alert("Item inválido", "Por favor, introduza um item coerente para encomenda");
        }

        if(quantidade > 0 && (it!=null)){
            it.setStock(it.getStock() + quantidade);
            cf.encomenda(it);

            tblStock.setItems(cf.getItems());
        }
    }catch(NumberFormatException e){
       e.getMessage();
       }
    }
}
