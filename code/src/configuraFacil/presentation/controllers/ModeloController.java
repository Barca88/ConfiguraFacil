package configuraFacil.presentation.controllers;

import configuraFacil.business.ConfiguraFacil;
import configuraFacil.business.models.Configuracao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;


public class ModeloController {

    @FXML
    private ChoiceBox<String> cbModelo;

    @FXML
    private ChoiceBox<String> cbCor;

    ConfiguraFacil cf;

    public void init(ConfiguraFacil cfo) {
        cf = cfo;

        cbModelo.getItems().add("X5");
        cbModelo.getItems().add("X3");
        cbModelo.getItems().add("X2");
        cbCor.getItems().add("RED");
        cbCor.getItems().add("GREEN");
        cbCor.getItems().add("BLUE");

        cf.setConfigConsulta(new Configuracao());
    }

    public void handleBtnBack(ActionEvent actionEvent) {
    }

    public void handleBtnSeguinteAction(ActionEvent actionEvent) {
        cf.getConfigConsulta().setModelo(cbModelo.getValue()) ;
        cf.getConfigConsulta().setCor(cbCor.getValue());
    }
}
