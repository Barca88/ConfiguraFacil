package app.controllers;

import Facade.ConfiguraFacil;
import Facade.SceneManager;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ConsultarConfiguracaoController {

    private ConfiguraFacil cf;

    public void init(ConfiguraFacil cfo) {
        cf = cfo;
    }

    public void handleBtnBack(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getResource("../views/configuracoesAdmin.fxml");
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        SceneManager sm = new SceneManager(url, window);
        sm.newScene(4, cf);
    }
}
