package configuraFacil.presentation.controllers;


import configuraFacil.business.ConfiguraFacil;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;


public class VendedorController {

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

    public void handleBtnCriarConfigAction(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getResource("../views/configuracao.fxml");
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        SceneManager sm = new SceneManager(url, window);
        sm.newScene(6, cf);
    }

    public void handleBtnConfig(ActionEvent actionEvent) throws IOException {
        //new presentation
        URL url = getClass().getResource("../views/configuracoes.fxml");
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        SceneManager sm = new SceneManager(url, window);
        sm.newScene(4, cf);
    }

    public void handleBtnOtima(ActionEvent actionEvent) throws IOException {
        //new presentation
        URL url = getClass().getResource("../views/configuracaoOtima.fxml");
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        SceneManager sm = new SceneManager(url, window);
        sm.newScene(8, cf);
    }
}
