package configuraFacil.presentation.controllers;

import configuraFacil.business.ConfiguraFacil;
import configuraFacil.business.models.Configuracao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


public class ConfiguracaoController {

    @FXML
    private ChoiceBox<String> cbModelo;

    @FXML
    private ChoiceBox<String> cbCor;

    @FXML
    private ChoiceBox<String> cbVolante;

    @FXML
    private ChoiceBox<String> cbBancos;

    @FXML
    private ChoiceBox<String> cbEstofos;

    @FXML
    private ChoiceBox<String> cbPneus;

    @FXML
    private ChoiceBox<String> cbJantes;

    @FXML
    private ChoiceBox<String> cbCorpo;

    @FXML
    private ChoiceBox<String> cbPacote;

    @FXML
    private ChoiceBox<String> cbOpcional;


    ConfiguraFacil cf;

    public void init(ConfiguraFacil cfo) {
        cf = cfo;

        cbModelo.setItems(cf.getModelos());
        cbModelo.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> ModeloChanged());
        cbCor.setItems(cf.getCores());
        cbVolante.setItems(cf.getVolantes());
        cbBancos.setItems(cf.getBancos());
        cbEstofos.setItems(cf.getEstofos());
        cbJantes.setItems(cf.getJantes());
        cbPneus.setItems(cf.getPneus());
        cbCorpo.setItems(cf.getCorpos());
        cbPacote.setItems(cf.getPacotes());
        cbOpcional.setItems(cf.getOpcionais());
        cf.setInUseConfig(new Configuracao());
    }

    public void handleBtnCancelar(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getResource("../views/vendedor.fxml");
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        SceneManager sm = new SceneManager(url, window);
        sm.newScene(3, cf);
    }

    public void handleBtnFinalizarAction(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getResource("../views/formcliente.fxml");
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        SceneManager sm = new SceneManager(url, window);
        sm.newScene(5, cf);
    }

    public void ModeloChanged() {
        cf.getInUseConfig().setModelo(cbModelo.getValue());
        System.out.println(cf.getInUseConfig().getModelo());
    }
}
