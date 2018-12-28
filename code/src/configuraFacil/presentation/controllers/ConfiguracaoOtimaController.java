package configuraFacil.presentation.controllers;

import configuraFacil.business.ConfiguraFacil;
import configuraFacil.business.models.Configuracao;
import configuraFacil.business.models.items.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.stream.Collectors;

public class ConfiguracaoOtimaController {

    ConfiguraFacil cf;
    float preco = 0;

    @FXML
    private ChoiceBox<String> cbModelo;

    @FXML
    private ChoiceBox<String> cbCor;

    @FXML
    private TextField txtOrcamento;

    @FXML
    private Label lblPreco;

    public void init(ConfiguraFacil cfo) {
        cf = cfo;

        cbModelo.setItems(cf.getModelos());
        cbModelo.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbModelo));

        cbCor.setItems(cf.getCores());
        cbCor.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbCor));

        cf.setInUseConfig(new Configuracao());
    }

    public void handleBtnCancelar(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getResource("../views/vendedor.fxml");
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        SceneManager sm = new SceneManager(url, window);
        sm.newScene(3, cf);
    }

    public void handleBtnFinalizarAction(ActionEvent actionEvent) throws IOException {
        Configuracao c = cf.getInUseConfig();

        if(Integer.parseInt(txtOrcamento.getText()) <= 0) AlertBox.alert("Orçamento Inválido!", "Por favor, escolha um Orçamento coerente");

        if((c.getModelo() != null) && (c.getCor() != null) && (txtOrcamento != null)) {
            c.setOrcamento(Float.parseFloat(txtOrcamento.getText()));
            cf.optimus_prime(c,Float.parseFloat(txtOrcamento.getText()));
            preco = cf.price(c.getItens().values().stream().collect(Collectors.toList()),0);
            c.setPreco(preco);


            URL url = getClass().getResource("../views/clienteform.fxml");
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            SceneManager sm = new SceneManager(url, window);
            sm.newScene(5, cf);
        }else if(c.getModelo() == null && c.getCor() == null) AlertBox.alert("Configuração Incompleta!", "Por favor, escolha o Modelo e a Cor do Carro");
        else if(c.getCor() == null) AlertBox.alert("Configuração Incompleta!", "Por favor, escolha a Cor do Carro");
        else AlertBox.alert("Configuração Incompleta!", "Por favor, escolha o Modelo do Carro");


    }

    public void itemChanged(ChoiceBox<String> tipo){
        Configuracao c = cf.getInUseConfig();
        Item item = cf.getItems().stream().filter(i -> i.getNome().equals(tipo.getValue())).findAny().orElse(null);

        try {
            cf.removeSametype(c, item);
            cf.addItem(item, c);

            preco = cf.price(c.getItens().values().stream().collect(Collectors.toList()),0);

            lblPreco.setText(Float.toString(preco));
        }catch(NullPointerException e){
            e.getMessage();
        }
    }
}

