package configuraFacil.presentation.controllers;

import configuraFacil.business.ConfiguraFacil;
import configuraFacil.business.models.Configuracao;
import configuraFacil.business.models.users.Utilizador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;


public class ConfiguracoesController {

    private ConfiguraFacil cf;

    @FXML
    private TableView<Configuracao> tblConfigAdmin;
    @FXML
    private TableColumn<Configuracao,Integer> clnIdConfig;
    @FXML
    private TableColumn<Configuracao,String> clnEstadoConfig;
    @FXML
    private TableColumn<Configuracao,Integer> clnOrcamentoConfig;
    @FXML
    private TableColumn<Configuracao,Integer> clnPrecoConfig;
    @FXML
    private TableColumn<Configuracao,Integer> clnDataConfig;
    @FXML
    private Button btnValidar;
    @FXML
    private Button btnProduzir;

    public void init(ConfiguraFacil cfo) {
        cf = cfo;
        Utilizador u = cf.getLogged();

        if(u.getClass().getSimpleName().equals("Vendedor")){
            btnValidar.setVisible(true);
            btnProduzir.setVisible(false);
        }

        if(u.getClass().getSimpleName().equals("Fabricante")) {
            btnValidar.setVisible(false);
            btnProduzir.setVisible(true);
        }
        if(u.getClass().getSimpleName().equals("Administrador")) {
            btnValidar.setVisible(false);
            btnProduzir.setVisible(false);
        }

        initTable();
    }

    private void initTable(){
        clnIdConfig.setCellValueFactory(new PropertyValueFactory<>("id"));
        clnEstadoConfig.setCellValueFactory(new PropertyValueFactory<>("estado"));
        clnOrcamentoConfig.setCellValueFactory(new PropertyValueFactory<>("orcamento"));
        clnPrecoConfig.setCellValueFactory(new PropertyValueFactory<>("preco"));
        clnDataConfig.setCellValueFactory(new PropertyValueFactory<>("data"));
        tblConfigAdmin.setItems(cf.getConfiguracoes());
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
        } else if(cf.getLogged().getClass().getSimpleName().equals("Vendedor")) {
            url = getClass().getResource("../views/vendedor.fxml");
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            SceneManager sm = new SceneManager(url, window);
            sm.newScene(3, cf);
        }
    }

    public void handleViewConfigAction(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER))
        {
            Configuracao c = tblConfigAdmin.getSelectionModel().getSelectedItem();
            cf.setInUseConfig(c);
            URL url = getClass().getResource("../views/consultarConfiguracao.fxml");
            Stage window = (Stage) ((Node) keyEvent.getSource()).getScene().getWindow();
            SceneManager sm = new SceneManager(url, window);
            sm.newScene(9, cf);
        }
    }

    public void handleBtnValidar(ActionEvent actionEvent) throws IOException{
        try{
            if(tblConfigAdmin.getSelectionModel().getSelectedItem() == null){
                AlertBox.alert("Configuração inválida", "Por favor, selecione uma configuração");
            }else{
                Configuracao c = tblConfigAdmin.getSelectionModel().getSelectedItem();

                if(c.getEstado().equals("N")){
                    c.setEstado("V");
                    cf.valida(c);

                    initTable();
                } else if (c.getEstado().equals("V")){
                    AlertBox.alert("Acção Inválida", "Configuração já se encontra validada");
                } else {
                    AlertBox.alert("Acção Inválida", "Configuração já se encontra produzida");
                }
            }
        }catch (NumberFormatException e){e.getMessage();}
    }

    public void handleBtnProduzir(ActionEvent actionEvent) throws IOException{
       cf.produz();
       initTable();
    }
}
