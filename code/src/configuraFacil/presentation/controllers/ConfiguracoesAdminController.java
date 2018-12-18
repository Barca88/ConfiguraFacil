package configuraFacil.presentation.controllers;

import configuraFacil.presentation.controllers.SceneManager;
import configuraFacil.business.ConfiguraFacil;
import configuraFacil.business.models.Configuracao;

import javafx.collections.ObservableList;
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
import java.util.List;

public class ConfiguracoesAdminController {

    private ConfiguraFacil cf;

    @FXML
    private TableView<Configuracao> tblConfigAdmin;
    @FXML
    private TableColumn<Configuracao,Integer> clnIdConfig;
    @FXML
    private TableColumn<Configuracao,String> clnEstadoConfig;
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
        clnEstadoConfig.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tblConfigAdmin.setItems(cf.getConfiguracoes());
    }

    public void handleBtnBack(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getResource("../views/administrador.fxml");
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        SceneManager sm = new SceneManager(url, window);
        sm.newScene(1, cf);
    }

    public void handleBtnRemoverConfigAction(ActionEvent actionEvent) {
        ObservableList<Configuracao> configuracaoSelecionada,todasAsConfiguracoes;
        todasAsConfiguracoes = tblConfigAdmin.getItems();
        configuracaoSelecionada = tblConfigAdmin.getSelectionModel().getSelectedItems();
        configuracaoSelecionada.forEach(todasAsConfiguracoes::remove);
        //initTable();
    }

    public void handleBtnAdicionarConfigAction(ActionEvent actionEvent) {
        Configuracao c = new Configuracao(Integer.parseInt(tfIdConfig.getText()), tfEstadoConfig.getText());
        cf.adicionarConfiguracao(c);
        cf.getConfiguracoes();
        initTable();
    }

    public void handleViewConfigAction(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER))
        {
            URL url = getClass().getResource("../views/consultarConfiguracao.fxml");
            Stage window = (Stage) ((Node) keyEvent.getSource()).getScene().getWindow();
            SceneManager sm = new SceneManager(url, window);
            sm.newScene(9, cf);
        }

    }
}
