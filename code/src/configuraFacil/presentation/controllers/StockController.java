package configuraFacil.presentation.controllers;

import configuraFacil.presentation.controllers.SceneManager;
import configuraFacil.business.ConfiguraFacil;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class StockController {
    ConfiguraFacil cf;

    public void init(ConfiguraFacil cfo) {
        cf = cfo;
    }

    public void handleBtnBack(ActionEvent actionEvent) throws IOException {

        URL url =  getClass().getResource("../views/administrador.fxml");
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        SceneManager sm = new SceneManager(url, window);
        sm.newScene(1, cf);

    }
}
