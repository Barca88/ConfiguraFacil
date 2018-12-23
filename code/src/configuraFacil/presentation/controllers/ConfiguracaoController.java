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
        cbModelo.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbModelo,old));
        cbCor.setItems(cf.getCores());
        cbCor.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbCor,old));
        cbVolante.setItems(cf.getVolantes());
        cbVolante.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbVolante,old));
        cbBancos.setItems(cf.getBancos());
        cbBancos.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbBancos,old));
        cbEstofos.setItems(cf.getEstofos());
        cbEstofos.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbEstofos,old));
        cbJantes.setItems(cf.getJantes());
        cbJantes.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbJantes,old));
        cbPneus.setItems(cf.getPneus());
        cbPneus.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbPneus,old));
        cbCorpo.setItems(cf.getCorpos());
        cbCorpo.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbCorpo,old));
        cbPacote.setItems(cf.getPacotes());

        cbOpcional.setItems(cf.getOpcionais());
        cbOpcional.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbOpcional,old));
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

        public void itemChanged(ChoiceBox<String> tipo, String old){
        Configuracao c = cf.getInUseConfig();
        Item item = cf.getItems().stream().filter(i -> i.getNome().equals(tipo.getValue())).collect(Collectors.toList()).get(0);
        if (old != null){
            Item oldItem = cf.getItems().stream().filter(i -> i.getNome().equals(old)).collect(Collectors.toList()).get(0);
            List <Item> remove = cf.oldDependent(c,oldItem);
            if(!remove.isEmpty()){
                boolean resp = AlertBox.display("O Item tem incompatibilidades", "Deseja adicionar o seguinte item?");
                if(resp == true){
                    for (Item rem : remove){
                        cf.removeItem(rem,c);
                    }
                }
            }



        }
        List<Item> depend = cf.dependencias(item,c.getItens());
        List<Item> incomp = cf.incompatibilidades(item,c.getItens());
        if(depend.isEmpty() && incomp.isEmpty()){
            cf.removeSametype(c,item);
            cf.addItem(item,c);
        }
        else{
            //TO DO (Muito Importante)
            List<Item> dincomp = cf.dIncompativeis(c.getItens(),depend);
            List<Item> idepend = cf.iDependentes(c.getItens(),incomp);
            boolean reply = AlertBox.display("O Item tem dependências", "Deseja adicionar os seguintes itens com o custo adicional de " + cf.price(depend) + "?");
            if(reply == true){

                for (Item i : depend){
                    cf.removeSametype(c,i);
                    cf.addItem(i,c);

                }
                for (Item i2 : idepend){
                    cf.removeSametype(c,i2);
                    cf.addItem(i2,c);
                }
                for (Item i3 : incomp){
                    cf.removeItem(i3,c);
                }
                for (Item i4 : dincomp){
                    cf.removeItem(i4,c);
                }
                cf.addItem(item,c);
            }
        }
        if(!incomp.isEmpty() && !depend.isEmpty());//TO DO(Esta condição poderá não ser necessária)

    }
}
