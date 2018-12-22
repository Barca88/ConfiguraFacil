package configuraFacil.presentation.controllers;


import configuraFacil.business.ConfiguraFacil;
import configuraFacil.business.models.Configuracao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableColumn<Configuracao,Integer> clnModeloConfig;
    @FXML
    private TableColumn<Configuracao,Integer> clnCorConfig;
    @FXML
    private TableColumn<Configuracao,String> clnEstadoConfig;
    @FXML
    private TableColumn<Configuracao,Integer> clnOrcamentoConfig;
    @FXML
    private TextField tfIdConfig;
    @FXML
    private TextField tfEstadoConfig;


    public void init(ConfiguraFacil cfo) {
        cf = cfo;
        initTable();
    }

    private void initTable(){
        clnIdConfig.setCellValueFactory(new PropertyValueFactory<>("id"));
        clnModeloConfig.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        clnCorConfig.setCellValueFactory(new PropertyValueFactory<>("cor"));
        clnEstadoConfig.setCellValueFactory(new PropertyValueFactory<>("estado"));
        clnOrcamentoConfig.setCellValueFactory(new PropertyValueFactory<>("orcamento"));
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

    public void handleBtnAdicionarConfigAction(ActionEvent actionEvent) {
        if(tfIdConfig.getText().equals("") || tfEstadoConfig.getText().equals("")) {
            System.out.println("TODO SHOW Aviso");
        }else{Configuracao c = new Configuracao(Integer.parseInt(tfIdConfig.getText()), "Civic", "Preto", tfEstadoConfig.getText(), 0, null, null, null);
           // cf.adicionarConfiguracao(c);
            cf.getConfiguracoes();
            initTable();
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
}
