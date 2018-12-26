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
        lblConfig.setText("Configuração " + Integer.toString(cf.getInUseConfig().getId()));
        lblModelo.setText(cf.getInUseConfig().getModelo());
        String cor = cf.getInUseConfig().getCor();
        crCor.setFill(javafx.scene.paint.Color.valueOf(cor));
        for(Item i : cf.getInUseConfig().getItens().values()){
            if(!i.getTipo().equals("Modelo") && !i.getTipo().equals("Cor"))
            lvItens.getItems().add(i);
        }
        lblCliente.setText(cf.getInUseConfig().getCliente().getNome());
        lblVendedor.setText(cf.getInUseConfig().getVendedor().getNome());


    }

    public void handleBtnBack(ActionEvent actionEvent) throws IOException {
        cf.setInUseConfig(null);
        URL url = getClass().getResource("../views/configuracoes.fxml");
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        SceneManager sm = new SceneManager(url, window);
        sm.newScene(4, cf);
    }
}
