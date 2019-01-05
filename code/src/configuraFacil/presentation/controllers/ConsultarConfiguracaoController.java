package configuraFacil.presentation.controllers;

import configuraFacil.business.models.Configuracao;
import configuraFacil.business.models.Item;
import configuraFacil.business.ConfiguraFacil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class ConsultarConfiguracaoController {

    private ConfiguraFacil cf;
    @FXML
    private Button btnOtima;
    @FXML
    private Label lblConfig;
    @FXML
    private Label lblModelo;
    @FXML
    private Label lblPreco;
    @FXML
    private Label lblCliente;
    @FXML
    private Label lblVendedor;
    @FXML
    private Circle crCor;
    @FXML
    private ListView<Item> lvItens;

    public void init(ConfiguraFacil cfo) {
        cf = cfo;

        lblConfig.setText("Configuração " + Integer.toString(cf.consultarConfiguracao().getId()));
        lblModelo.setText(cf.consultarConfiguracao().getModelo());

        if(cf.consultarConfiguracao().getPreco() < 0) {
            btnOtima.setVisible(true);
            lblCliente.setVisible(false);
            lblVendedor.setVisible(false);
            lblPreco.setText(Float.toString(-(cf.consultarConfiguracao().getPreco())));
            String cor = cf.consultarConfiguracao().getCor();
            crCor.setFill(javafx.scene.paint.Color.valueOf(cor));
            for(Item i : cf.consultarConfiguracao().getItens().values()){
                if(!i.getTipo().equals("Modelo") && !i.getTipo().equals("Cor"))
                    lvItens.getItems().add(i);
            }
        }else{
            lblPreco.setText(Float.toString((cf.consultarConfiguracao().getPreco())));
            String cor = cf.consultarConfiguracao().getCor();
            crCor.setFill(javafx.scene.paint.Color.valueOf(cor));
            for(Item i : cf.consultarConfiguracao().getItens().values()){
                if(!i.getTipo().equals("Modelo") && !i.getTipo().equals("Cor"))
                    lvItens.getItems().add(i);
            }
            lblCliente.setText(cf.consultarConfiguracao().getCliente().getNome());
            lblVendedor.setText(cf.consultarConfiguracao().getVendedor().getNome());
        }

    }

    public void handleBtnBack(ActionEvent actionEvent) throws IOException {
        if(cf.consultarConfiguracao().getPreco() < 0){
            cf.setInUseConfig(null);
            URL url = getClass().getResource("../views/configuracaoOtima.fxml");
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            SceneManager sm = new SceneManager(url, window);
            sm.newScene(8, cf);
        }
        else {
            cf.setInUseConfig(null);
            URL url = getClass().getResource("../views/configuracoes.fxml");
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            SceneManager sm = new SceneManager(url, window);
            sm.newScene(4, cf);
        }
    }

    public void handleBtnFinalizar(ActionEvent actionEvent) throws IOException {
        Configuracao c = cf.consultarConfiguracao();
        cf.setPreco(c,-(c.getPreco()));

        URL url = getClass().getResource("../views/clienteform.fxml");
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        SceneManager sm = new SceneManager(url, window);
        sm.newScene(5, cf);

    }
}
