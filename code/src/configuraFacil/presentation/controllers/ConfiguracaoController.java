package configuraFacil.presentation.controllers;

import configuraFacil.business.ConfiguraFacil;
import configuraFacil.business.models.Configuracao;
import configuraFacil.business.models.items.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class ConfiguracaoController {

    @FXML
    private Label lbAviso;

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
    private ChoiceBox<String>  cbOpcional_1;

    @FXML
    private ChoiceBox<String>  cbOpcional_2;

    @FXML
    private ChoiceBox<String>  cbOpcional_3;

    @FXML
    private ChoiceBox<String>  cbOpcional_4;

    @FXML
    private ChoiceBox<String>  cbOpcional_5;


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
        cbPacote.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> pacoteChanged());

        cbOpcional_1.setItems(cf.getOpcionais());
        cbOpcional_1.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbOpcional_1,old));

        cbOpcional_2.setItems(cf.getOpcionais());
        cbOpcional_2.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbOpcional_2,old));

        cbOpcional_3.setItems(cf.getOpcionais());
        cbOpcional_3.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbOpcional_3,old));

        cbOpcional_4.setItems(cf.getOpcionais());
        cbOpcional_4.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbOpcional_4,old));

        cbOpcional_5.setItems(cf.getOpcionais());
        cbOpcional_5.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbOpcional_5,old));

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

        if((c.getModelo() != null) && (c.getCor() != null)) {
            URL url = getClass().getResource("../views/clienteform.fxml");
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            SceneManager sm = new SceneManager(url, window);
            sm.newScene(5, cf);
        }else if(c.getModelo() == null && c.getCor() == null) AlertBox.alert("Configuração Incompleta!", "Por favor, escolha o Modelo e a Cor do Carro");
            else if(c.getCor() == null) AlertBox.alert("Configuração Incompleta!", "Por favor, escolha a Cor do Carro");
                else AlertBox.alert("Configuração Incompleta!", "Por favor, escolha o Modelo do Carro");
    }

    public void itemChanged(ChoiceBox<String> tipo, String old) {
        Configuracao c = cf.getInUseConfig();
        Item item = cf.getItems().stream().filter(i -> i.getNome().equals(tipo.getValue())).findAny().orElse(null);
        Item oldItem = cf.getItems().stream().filter(i -> i.getNome().equals(old)).findAny().orElse(null);

        if (old != null) {

            List<Item> remove = cf.oldDependent(c, oldItem);
            if (!remove.isEmpty()) {
                List <String> nomes = remove.stream().map(i -> i.getNome()).collect(Collectors.toList());
                String show = String.join("\n",nomes);
                boolean resp = AlertBox.display("O Item tem incompatibilidades", "Deseja adicionar item?\n" + "Itens incompatíveis:\n" + show );
                if (resp == true) {
                    for (Item rem : remove) {
                        cf.removeItem(rem, c);
                        removeChoices(rem);
                    }
                } else {
                    cf.addItem(oldItem,c);
                    handleChoices(item,oldItem,1);
                }
            }
        }

        List<Item> depend = cf.dependencias(item, c.getItens());
        List<Item> incomp = cf.incompatibilidades(item, c.getItens());
        try {
            if (depend.isEmpty() && incomp.isEmpty()) {
                cf.removeSametype(c, item);
                cf.addItem(item, c);
            } else if(!depend.isEmpty()){
                    List <String> nomesde = depend.stream().map(i -> i.getNome()).collect(Collectors.toList());
                    String showd = String.join("\n",nomesde);
                    boolean reply = AlertBox.display("O Item tem dependencias", "Deseja adicionar os seguintes itens:\n" + showd +"\nCom custo o adicional: " + cf.price(depend,0) + "?");
                    if (reply == true) {

                        for (Item i : depend) {
                            cf.removeSametype(c, i);
                            cf.addItem(i, c);
                            handleChoices(i,oldItem,0);
                        }

                        for (Item i2 : incomp) {
                            cf.removeItem(i2, c);
                            removeChoices(i2);
                        }

                        cf.addItem(item, c);
                    } else {handleChoices(item,oldItem,1); }
                    }
        } catch (NullPointerException e) {
            e.getMessage();
        }

        int pacote = cf.checkPacote(c);
        float desconto = 0;
        if(pacote != 0){
            desconto = cf.getDesconto(pacote);
            addPacoteChoices(pacote);
        }
        float preco = cf.price(c.getItens().values().stream().collect(Collectors.toList()),desconto);

    }

    public void removeChoices(Item item) {
            String tipo = item.getTipo();

            switch (tipo) {

                case "Modelo":
                    cbModelo.setValue(null);
                    break;
                case "Cor":
                    cbCor.setValue(null);
                    break;
                case "Jantes":
                    cbJantes.setValue(null);
                    break;
                case "Pneus":
                    cbPneus.setValue(null);
                    break;
                case "Corpo":
                    cbCorpo.setValue(null);
                    break;
                case "Volante":
                    cbVolante.setValue(null);
                    break;
                case "Bancos":
                    cbBancos.setValue(null);
                    break;
                case "Estofos":
                    cbEstofos.setValue(null);
                    break;
                default:
                    break;
        }
    }

    public void handleChoices(Item item,Item old, int type_of_handling){
        String new_tipo = item.getTipo();
        String new_nome = item.getNome();

        String old_nome = null;
        if(old != null) old_nome = old.getNome();
        if(type_of_handling == 1) new_nome = old_nome;

            switch (new_tipo) {

                case "Modelo":
                    cbModelo.setValue(new_nome);
                    break;
                case "Cor":
                    cbCor.setValue(new_nome);
                    break;
                case "Jantes":
                    cbJantes.setValue(new_nome);
                    break;
                case "Pneus":
                    cbPneus.setValue(new_nome);
                    break;
                case "Corpo":
                    cbCorpo.setValue(new_nome);
                    break;
                case "Volante":
                    cbVolante.setValue(new_nome);
                    break;
                case "Bancos":
                    cbBancos.setValue(new_nome);
                    break;
                case "Estofos":
                    cbEstofos.setValue(new_nome);
                    break;
                default:
                    break;

            }
        }

    public void pacoteChanged(){
        List<Item> itens = cf.getItemPacote(cbPacote.getValue());
        for(Item item : itens){
            String tipo = item.getTipo();
            switch(tipo) {

                case "Modelo" : cbModelo.setValue(item.getNome()); break;
                case "Cor" : cbCor.setValue(item.getNome()); break;
                case "Jantes" : cbJantes.setValue(item.getNome()); break;
                case "Pneus" : cbPneus.setValue(item.getNome()); break;
                case "Corpo" : cbCorpo.setValue(item.getNome()); break;
                case "Volante" : cbVolante.setValue(item.getNome()); break;
                case "Bancos" : cbBancos.setValue(item.getNome()); break;
                case "Estofos" : cbEstofos.setValue(item.getNome()); break;
                default: break;

            }

        }
    }
    public void addPacoteChoices(int id){
        cbPacote.setValue(cf.getPacote(id).getNome());

    }
}
