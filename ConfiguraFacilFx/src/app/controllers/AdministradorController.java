package app.controllers;

import Facade.ConfiguraFacil;
import Facade.SceneManager;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdministradorController {

    ConfiguraFacil cf;

    public void init(ConfiguraFacil cfo) {
        cf = cfo;
    }

    public void handleBtnSair(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getResource("../views/login.fxml");
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        SceneManager sm = new SceneManager(url, window);
        sm.newScene(-1, cf);
    }

    public void handleBtnStock(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getResource("../views/stockAdmin.fxml");
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        SceneManager sm = new SceneManager(url, window);
        sm.newScene(7, cf);
    }

    public void handleBtnConfiguracoes(ActionEvent actionEvent) throws IOException {

        URL url = getClass().getResource("../views/configuracoesAdmin.fxml");
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        SceneManager sm = new SceneManager(url, window);
        sm.newScene(4, cf);
    }

    public void handleBtnVendedores(ActionEvent actionEvent) {

    }

    public void handleBtnFabricantes(ActionEvent actionEvent) {
    }
}
