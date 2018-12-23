package configuraFacil.presentation.controllers;

import configuraFacil.business.ConfiguraFacil;
import configuraFacil.business.models.Configuracao;
import configuraFacil.business.models.items.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;


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
        cbModelo.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbModelo));
        cbCor.setItems(cf.getCores());
        cbCor.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> corChanged());
        cbVolante.setItems(cf.getVolantes());
        cbVolante.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbVolante));
        cbBancos.setItems(cf.getBancos());
        cbBancos.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbBancos));
        cbEstofos.setItems(cf.getEstofos());
        cbEstofos.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbEstofos));
        cbJantes.setItems(cf.getJantes());
        cbJantes.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbJantes));
        cbPneus.setItems(cf.getPneus());
        cbPneus.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbPneus));
        cbCorpo.setItems(cf.getCorpos());
        cbCorpo.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbCorpo));
        cbPacote.setItems(cf.getPacotes());

        cbOpcional.setItems(cf.getOpcionais());
        cbOpcional.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbOpcional));
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

    public void modeloChanged() {
        cf.getInUseConfig().setModelo(cbModelo.getValue());
    }

    public void corChanged(){
        cf.getInUseConfig().setCor(cbCor.getValue());
    }

        public void itemChanged(ChoiceBox<String> tipo){
        Configuracao c = cf.getInUseConfig();
        Item item = cf.getItems().stream().filter(i -> i.getNome().equals(tipo.getValue())).collect(Collectors.toList()).get(0);
        List<Item> depend = cf.dependencias(item,c.getItens());
        List<Item> incomp = cf.incompatibilidades(item,c.getItens());
        if(depend.isEmpty() && incomp.isEmpty()){
            c = cf.addItem(item,c);
        }
        if(incomp.isEmpty() && !depend.isEmpty()){
            boolean reply = AlertBox.display("O Item tem dependências", "Deseja adicionar os seguintes item/ns? com o custo adicional: " + cf.price(depend));
            if(reply == true){
                for (Item i : depend){
                   c = cf.addItem(i,c);
                }
            }
        }

    }
}
