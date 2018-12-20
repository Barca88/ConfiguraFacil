package configuraFacil.presentation.controllers;


import configuraFacil.business.models.items.Item;
import configuraFacil.presentation.controllers.SceneManager;
import configuraFacil.business.ConfiguraFacil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ConsultarConfiguracaoController {

    @FXML
    private Label lblConfig;
    @FXML
    private Label lblModelo;
    @FXML
    private Label lblCliente;
    @FXML
    private Label lblVendedor;
    @FXML
    private Circle crCor;
    @FXML
    private ListView<Item> lvItens;


    private ConfiguraFacil cf;

    public void init(ConfiguraFacil cfo) {
        cf = cfo;
        lblConfig.setText("Configurção " + Integer.toString(cf.getConfigConsulta().getId()));
        lblModelo.setText(cf.getConfigConsulta().getModelo());
        crCor.setFill(javafx.scene.paint.Color.RED);
        for(Item i : cf.getConfigConsulta().getItens().values()) {
            lvItens.getItems().add(i);
        }
        lblCliente.setText(cf.getConfigConsulta().getCliente().getNome());
        lblVendedor.setText(cf.getConfigConsulta().getVendedor().getNome());


    }

    public void handleBtnBack(ActionEvent actionEvent) throws IOException {
        cf.setConfigConsulta(null);
        URL url = getClass().getResource("../views/configuracoes.fxml");
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        SceneManager sm = new SceneManager(url, window);
        sm.newScene(4, cf);
    }
}
