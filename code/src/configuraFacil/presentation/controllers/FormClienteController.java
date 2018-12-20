package configuraFacil.presentation.controllers;

import configuraFacil.business.ConfiguraFacil;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class FormClienteController {

    ConfiguraFacil cf;

    public void init(ConfiguraFacil cfo) {
        cf = cfo;
    }


    public void handleBtnCancelar(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getResource("../views/vendedor.fxml");
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        SceneManager sm = new SceneManager(url, window);
        sm.newScene(3, cf);
    }

    public void handleBtnFinalizarAction(ActionEvent actionEvent) {
    }
}
