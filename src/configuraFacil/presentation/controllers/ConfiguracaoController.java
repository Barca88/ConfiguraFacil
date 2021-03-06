package configuraFacil.presentation.controllers;

import configuraFacil.business.ConfiguraFacil;
import configuraFacil.business.models.Configuracao;
import configuraFacil.business.models.Pacote;
import configuraFacil.business.models.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class ConfiguracaoController {


    private ConfiguraFacil cf;
    private float preco = 0;
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
    @FXML
    private Label lblPreco;
    @FXML
    private Label lblDesconto;

    public void init(ConfiguraFacil cfo) {
        cf = cfo;

        //load and add listener to choice boxes
        cbModelo.setItems(cf.getModelos());
        cbModelo.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbModelo,old, newValue));
        cbCor.setItems(cf.getCores());
        cbCor.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbCor,old, newValue));
        cbVolante.setItems(cf.getVolantes());
        cbVolante.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbVolante,old, newValue));
        cbBancos.setItems(cf.getBancos());
        cbBancos.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbBancos,old, newValue));
        cbEstofos.setItems(cf.getEstofos());
        cbEstofos.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbEstofos,old, newValue));
        cbJantes.setItems(cf.getJantes());
        cbJantes.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbJantes,old, newValue));
        cbPneus.setItems(cf.getPneus());
        cbPneus.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbPneus,old, newValue));
        cbCorpo.setItems(cf.getCorpos());
        cbCorpo.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbCorpo,old,newValue));
        cbPacote.setItems(cf.getPacotes_N());
        cbPacote.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> pacoteChanged());
        cbOpcional_1.setItems(cf.getOpcionais());
        cbOpcional_1.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbOpcional_1,old,newValue));
        cbOpcional_2.setItems(cf.getOpcionais());
        cbOpcional_2.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbOpcional_2,old,newValue));
        cbOpcional_3.setItems(cf.getOpcionais());
        cbOpcional_3.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbOpcional_3,old,newValue));
        cbOpcional_4.setItems(cf.getOpcionais());
        cbOpcional_4.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbOpcional_4,old,newValue));
        cbOpcional_5.setItems(cf.getOpcionais());
        cbOpcional_5.getSelectionModel().selectedItemProperty().addListener((v, old, newValue) -> itemChanged(cbOpcional_5,old,newValue));

        cf.setInUseConfig(new Configuracao());
    }

    public void handleBtnCancelar(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getResource("../views/vendedor.fxml");
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        SceneManager sm = new SceneManager(url, window);
        sm.newScene(3, cf);
    }

    public void handleBtnFinalizarAction(ActionEvent actionEvent) throws IOException {
        Configuracao c = cf.consultarConfiguracao();
        c.setPreco(preco);

        if((c.getModelo() != null) && (c.getCor() != null)) {
            URL url = getClass().getResource("../views/clienteform.fxml");
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            SceneManager sm = new SceneManager(url, window);
            sm.newScene(5, cf);
        }else if(c.getModelo() == null && c.getCor() == null) AlertBox.alert("Configuração Incompleta!", "\nPor favor, escolha o Modelo e a Cor do Carro");
            else if(c.getCor() == null) AlertBox.alert("Configuração Incompleta!", "\nPor favor, escolha a Cor do Carro");
                else AlertBox.alert("Configuração Incompleta!", "\nPor favor, escolha o Modelo do Carro");
    }

    private void itemChanged(ChoiceBox<String> tipo, String old, String newValue) {
        Pacote check = null;
        Configuracao c = cf.consultarConfiguracao();

        Pacote pacote = cf.getPacotes().stream().filter(i -> i.equals(newValue)).findAny().orElse(null);
        Item item = cf.cosultarStock().stream().filter(i -> i.getNome().equals(newValue)).findAny().orElse(null);
        Item oldItem = cf.cosultarStock().stream().filter(i -> i.getNome().equals(old)).findAny().orElse(null);

        List<Item> depend = cf.dependencias(item, c.getItens());
        List<Item> incomp = cf.incompatibilidades(item, c.getItens());

            try {
                if (!item.getTipo().equals("Opcional")) {

                    if(old != null) {
                        handleOldDependencies(depend, incomp, item, oldItem, c);
                    }

                    if (depend.isEmpty() && incomp.isEmpty()) {
                        cf.removeSametype(c, item);
                        cf.addItem(item, c);
                    }

                    if (depend.size() > 0) {
                        handleDependencies(depend,incomp,item,oldItem,c);
                    }

                    if (incomp.size() > 0){
                        handleIncompatibilities(depend,incomp,item,oldItem,c);
                    }

                }else if (item.getTipo().equals("Opcional")) {
                    String l1[] = tipo.toString().split("id=");
                    String l2[] = l1[1].split(",");
                    String cb_BOX = l2[0];

                    handleOpcionais(cb_BOX, item, oldItem,c);

                } else{addPacoteChoices(pacote.getId());}

            }catch(NullPointerException e){
                e.getMessage();
        }

        check = cf.checkPacote(c);
        float desconto = 0;
        if(pacote == null){
            if(check != null) {
                desconto = cf.getDesconto(check.getId());
                cbPacote.setValue(check.getNome());
            }
        }

        preco = cf.price(c.getItens().values().stream().collect(Collectors.toList()),desconto);

        lblPreco.setText(Float.toString(preco));
        lblDesconto.setText(Float.toString(desconto));
    }

    private void handleDependencies(List<Item> depend,List<Item> incomp,Item item,Item oldItem,Configuracao c){
        List<String> nomesde = depend.stream().map(Item::getNome).collect(Collectors.toList());
        String showd = String.join("\n", nomesde);

        boolean reply = AlertBox.display("O Item tem dependências!", "\nDeseja adicionar os seguintes itens:\n" + showd + "\nCom custo o adicional de: " + cf.price(depend, 0) + "?");
        if (reply) {

            for (Item i : depend) {
                cf.removeSametype(c, i);
                cf.addItem(i, c);
                handleChoices(i, oldItem, 0);
            }

            for (Item i2 : incomp) {
                cf.removeItem(i2, c);
                removeChoices(i2);
            }

            cf.addItem(item, c);

        } else {
            handleChoices(item, oldItem, 1);
        }
    }

    private void handleIncompatibilities(List<Item> depend,List<Item> incomp,Item item,Item oldItem,Configuracao c){
        List<String> nomesde = incomp.stream().map(Item::getNome).collect(Collectors.toList());
        String showi = String.join("\n", nomesde);


        boolean resp = AlertBox.display("O Item tem Incompatibilidades!", "\nSe adicionar o item, os seguintes items serão removidos:\n\n" + showi);
        if (resp) {

            for(Item inc: incomp){
                cf.removeItem(inc,c);
                removeChoices(inc);
            }

            cf.addItem(item, c);

        }else{
            handleChoices(oldItem, item, 1);
        }

    }

    private void handleOldDependencies(List<Item> depend,List<Item> incomp,Item item,Item newItem,Configuracao c){
        List<Item> remove = cf.oldDependent(c, newItem);

        if(!remove.isEmpty()) {
            List<String> nomes = remove.stream().map(Item::getNome).collect(Collectors.toList());
            String showold_d = String.join("\n", nomes);

            boolean resp = AlertBox.display("Alguns Items são dependentes do item a ser alterado", "\nSe o remover, os seguintes items também serão removidos:\n\n" + showold_d);
            if (resp) {

                for (Item rem : remove) {
                    cf.removeItem(rem, c);
                    removeChoices(rem);
                }

                cf.addItem(newItem, c);

            } else {
                handleChoices(item, newItem, 1);
            }
        }
    }

    private void handleOpcionais(String box, Item item, Item old,Configuracao c){
        String new_nome = item.getNome();
        List<Item> ops = c.getItens().values().stream().filter(i -> i.getTipo().equals("Opcional")).collect(Collectors.toList());

        String old_nome = null;
        if(old != null) old_nome = old.getNome();

        switch (box) {

            case "cbOpcional_1":
                cbOpcional_1.setValue(new_nome);
                cbOpcional_2.getItems().remove(new_nome);
                cbOpcional_3.getItems().remove(new_nome);
                cbOpcional_4.getItems().remove(new_nome);
                cbOpcional_5.getItems().remove(new_nome);

                if(old != null) {
                    cbOpcional_2.getItems().add(old_nome);
                    cbOpcional_3.getItems().add(old_nome);
                    cbOpcional_4.getItems().add(old_nome);
                    cbOpcional_5.getItems().add(old_nome);
                    cf.removeItem(old,c); ops.remove(old);
                }

                if(!ops.contains(item)){ cf.addItem(item,c); ops.add(item); }

                break;
            case "cbOpcional_2":
                cbOpcional_2.setValue(new_nome);
                cbOpcional_1.getItems().remove(new_nome);
                cbOpcional_3.getItems().remove(new_nome);
                cbOpcional_4.getItems().remove(new_nome);
                cbOpcional_5.getItems().remove(new_nome);
                if(old != null) {
                    cbOpcional_1.getItems().add(old_nome);
                    cbOpcional_3.getItems().add(old_nome);
                    cbOpcional_4.getItems().add(old_nome);
                    cbOpcional_5.getItems().add(old_nome);
                    cf.removeItem(old,c);
                    ops.remove(old);
                }

                if(!ops.contains(item)){ cf.addItem(item,c); ops.add(item);}

                break;
            case "cbOpcional_3":
                cbOpcional_3.setValue(new_nome);
                cbOpcional_1.getItems().remove(new_nome);
                cbOpcional_2.getItems().remove(new_nome);
                cbOpcional_4.getItems().remove(new_nome);
                cbOpcional_5.getItems().remove(new_nome);
                if(old != null) {
                    cbOpcional_1.getItems().add(old_nome);
                    cbOpcional_2.getItems().add(old_nome);
                    cbOpcional_4.getItems().add(old_nome);
                    cbOpcional_5.getItems().add(old_nome);
                    cf.removeItem(old,c); ops.remove(old);
                }

                if(!ops.contains(item)){ cf.addItem(item,c); ops.add(item);}

                break;
            case "cbOpcional_4":
                cbOpcional_4.setValue(new_nome);
                cbOpcional_1.getItems().remove(new_nome);
                cbOpcional_2.getItems().remove(new_nome);
                cbOpcional_3.getItems().remove(new_nome);
                cbOpcional_5.getItems().remove(new_nome);
                if(old != null) {
                    cbOpcional_1.getItems().add(old_nome);
                    cbOpcional_2.getItems().add(old_nome);
                    cbOpcional_3.getItems().add(old_nome);
                    cbOpcional_5.getItems().add(old_nome);
                    cf.removeItem(old,c); ops.remove(old);
                }

                if(!ops.contains(item)){ cf.addItem(item,c); ops.add(item);}

                break;
            case "cbOpcional_5":
                cbOpcional_5.setValue(new_nome);
                cbOpcional_1.getItems().remove(new_nome);
                cbOpcional_2.getItems().remove(new_nome);
                cbOpcional_3.getItems().remove(new_nome);
                cbOpcional_4.getItems().remove(new_nome);
                if(old != null) {
                    cbOpcional_1.getItems().add(old_nome);
                    cbOpcional_2.getItems().add(old_nome);
                    cbOpcional_3.getItems().add(old_nome);
                    cbOpcional_4.getItems().add(old_nome);
                    cf.removeItem(old,c); ops.remove(old);
                }

                if(!ops.contains(item)){ cf.addItem(item,c); ops.add(item);}

                break;
            default:
                break;
        }
    }

    private void handleChoices(Item item,Item old, int type_of_handling){
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

    private void removeChoices(Item item) {
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


    public void pacoteChanged(){
        Configuracao c = cf.consultarConfiguracao();

        List<Item> itens = cf.getItensPacote(cbPacote.getValue());

        for(Item item : itens){
            String i_tipo = item.getTipo();
            switch(i_tipo) {

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

    private void addPacoteChoices(int id){
        Pacote p = cf.getPacote(id);
        cbPacote.setValue(p.getNome());
    }
}
