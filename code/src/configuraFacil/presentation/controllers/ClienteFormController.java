package configuraFacil.presentation.controllers;

import configuraFacil.business.ConfiguraFacil;
import configuraFacil.business.models.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

public class ClienteFormController {

    private ConfiguraFacil cf;

    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfTel;

    public void init(ConfiguraFacil cfo) {
        cf = cfo;
    }


    public void handleBtnCancelar(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getResource("../views/vendedor.fxml");
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        SceneManager sm = new SceneManager(url, window);
        sm.newScene(3, cf);
    }

    public void handleBtnFinalizarAction(ActionEvent actionEvent) throws IOException {

        //novo cliente
        Cliente c = new Cliente();
        c.setNome(tfNome.getText());
        c.setEmail(tfEmail.getText());
        c.setTelemovel(tfTel.getText());


        //cliente da configuracao
        cf.getInUseConfig().setCliente(c);
        cf.getInUseConfig().setVendedor(cf.getLogged());
        cf.getInUseConfig().setData(LocalDate.now());

        //adicionar as configuracoes
        cf.adicionarNovaConfiguracao();

        URL url = getClass().getResource("../views/vendedor.fxml");
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        SceneManager sm = new SceneManager(url, window);
        sm.newScene(3, cf);
    }
}
