package configuraFacil.business;


import configuraFacil.business.models.Configuracao;
import configuraFacil.business.models.Pacote;
import configuraFacil.business.models.Item;
import configuraFacil.business.models.users.Utilizador;
import configuraFacil.dataBase.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ConfiguraFacil {

    private UtilizadorDao utilizadores;
    private ConfiguracaoDao configuracoes;
    private ItemDao itens;
    private PacoteDao pacotes;
    private Utilizador logged;
    private Configuracao inUseConfig;

    public ConfiguraFacil() {
        utilizadores = new UtilizadorDao();
        configuracoes = new ConfiguracaoDao();
        itens = new ItemDao();
        pacotes = new PacoteDao();
        logged = null;
    }

    public int login(String email, String password,int i) throws NullPointerException {
        Utilizador us;

        try {
            if (utilizadores.containsKey(email)) {
                us = utilizadores.get(email);

                if (us.getPassword().equals(password)) {
                    String tipo = us.getClass().getSimpleName();
                    switch (i){
                        case 0:
                            if (!tipo.equals("Administrador"))
                                return 2;
                            break;

                        case 1:
                            if (!tipo.equals("Fabricante"))
                                return 2;
                            break;

                        case 2:
                            if (!tipo.equals("Vendedor"))
                                return 2;
                            break;
                    }
                    this.setLogged(us);
                    return 1;
                } else return 0;
            } else return 2;


        }catch (Exception e){
            throw new NullPointerException(e.getMessage());
        }

    }

    public Utilizador getLogged() {
        return logged;
    }

    private void setLogged(Utilizador logged) {
        this.logged = logged;
    }

    public Configuracao consultarConfiguracao() {
        return inUseConfig;
    }

    public void setInUseConfig(Configuracao configConsulta) {
        this.inUseConfig = configConsulta;
    }

    public void setPreco(Configuracao c, Float p){
        c.setPreco(p);
    }

    public  Pacote getPacote(int id){
        return pacotes.get(id);
    }

    public float getDesconto(int id){
        return pacotes.get(id).getDesconto();
    }

    public ObservableList<Configuracao> consultarConfiguracoes(){
        List<Configuracao> lc = new ArrayList<>(configuracoes.values());
        return FXCollections.observableArrayList(lc);
    }

    public ObservableList<Utilizador> consultarVendedores(){
        List<Utilizador> lv = new ArrayList<>(utilizadores.values().stream().filter(i -> i.getClass().getSimpleName().equals("Vendedor")).collect(toList()));
        return FXCollections.observableArrayList(lv);
    }

    public ObservableList<Utilizador> conssultarFabricantes(){
        List<Utilizador> lv = new ArrayList<>(utilizadores.values().stream().filter(i -> i.getClass().getSimpleName().equals("Fabricante")).collect(toList()));
        return FXCollections.observableArrayList(lv);
    }
    public ObservableList<Item> cosultarStock(){
        List<Item> li = new ArrayList<>(itens.values());
        return FXCollections.observableArrayList(li);
    }
    public ObservableList<String> getModelos(){
        List<String> lm = itens.values().stream().filter(i -> i.getTipo().equals("Modelo")).map(Item::getNome).collect(Collectors.toList());
        return FXCollections.observableArrayList(lm);
    }

    public ObservableList<String> getCores() {
        List<String> lm = itens.values().stream().filter(i -> i.getTipo().equals("Cor")).map(Item::getNome).collect(Collectors.toList());
        return FXCollections.observableArrayList(lm);
    }
    public ObservableList<String> getJantes(){
        List<String> lm = itens.values().stream().filter(i -> i.getTipo().equals("Jantes")).map(Item::getNome).collect(Collectors.toList());
        return FXCollections.observableArrayList(lm);
    }

    public ObservableList<String> getPneus(){
        List<String> lm = itens.values().stream().filter(i -> i.getTipo().equals("Pneus")).map(Item::getNome).collect(Collectors.toList());
        return FXCollections.observableArrayList(lm);
    }

    public ObservableList<String> getCorpos(){
        List<String> lm = itens.values().stream().filter(i -> i.getTipo().equals("Corpo")).map(Item::getNome).collect(Collectors.toList());
        return FXCollections.observableArrayList(lm);
    }

    public ObservableList<String> getVolantes(){
        List<String> lm = itens.values().stream().filter(i -> i.getTipo().equals("Volante")).map(Item::getNome).collect(Collectors.toList());
        return FXCollections.observableArrayList(lm);
    }

    public ObservableList<String> getBancos(){
        List<String> lm = itens.values().stream().filter(i -> i.getTipo().equals("Bancos")).map(Item::getNome).collect(Collectors.toList());
        return FXCollections.observableArrayList(lm);
    }

    public ObservableList<String> getEstofos(){
        List<String> lm = itens.values().stream().filter(i -> i.getTipo().equals("Estofos")).map(Item::getNome).collect(Collectors.toList());
        return FXCollections.observableArrayList(lm);
    }

    public ObservableList<String> getOpcionais(){
        List<String> lm = itens.values().stream().filter(i -> i.getTipo().equals("Opcional")).map(Item::getNome).collect(Collectors.toList());
        return FXCollections.observableArrayList(lm);
    }

    public ObservableList<String> getPacotes_N(){
        List<String> lm =  pacotes.values().stream().map(Pacote::getNome).collect(Collectors.toList());
        return FXCollections.observableArrayList(lm);
    }

    public ObservableList<Pacote> getPacotes(){
        List<Pacote> lm =  new ArrayList<>(pacotes.values());
        return FXCollections.observableArrayList(lm);
    }

    public List <Item> getItensPacote(String nome){
        return new ArrayList<>(pacotes.values().stream().filter(p -> p.getNome().equals(nome)).collect(Collectors.toList()).get(0).getItens().values());
    }

    public List<Item> incompatibilidades (Item item, Map<Integer,Item> conf){
        List<Item> incomp = new ArrayList<>();
        if(!(item == null)){
        for(int id : item.getIncomp()){
            if (conf.containsKey(id))
                incomp.add(itens.get(id));
        }
        return incomp;
        }
        else return null;
    }

    public List<Item> dependencias (Item item, Map<Integer,Item> conf ){
        List<Item> depend = new ArrayList<>();

        if(!(item == null)){
            for(int id : item.getDepend()){
                if (!conf.containsKey(id))
                 depend.add(itens.get(id));
            }
        return depend;
        }
        else return null;
    }

    public List<Item> oldDependent(Configuracao c, Item old){
        List <Item> dependentes = new ArrayList<>();
        if(!(old== null)){
        for(Item i : c.getItens().values()){
            if(i.getDepend().contains(old.getId())){
                dependentes.add(i);
            }
        }
        return dependentes;
        }
        else return null;
    }

    public void removeSametype(Configuracao c, Item i){
        for (Item i2 : c.getItens().values()){
            if (i.getTipo().equals(i2.getTipo())){
                c.removeItem(i2);
            }
        }
    }

    public int checkPacote(Configuracao c){
        List<Pacote> pacotes = new ArrayList<>(this.pacotes.values());
        int pId = -1;
        List <Integer> pitens = new ArrayList<>();
        for(Pacote p : pacotes){
            for(Item i : c.getItens().values()){
                if (p.getItens().containsKey(i.getId())){
                    pitens.add(i.getId());
                }

            }
            if(pitens.size() == p.getItens().size()){
                pId = p.getId();
                break;
            }
            else pitens.removeAll(p.getItens().values().stream().map(Item::getId).collect(toList()));
        }
        return pId ;
    }

    public void  addItem (Item i, Configuracao c){
        c.addItem(i);
    }

    public float price (List<Item> itens, float desconto){
        try {
            float preco = 0;
            for (Item i : itens) {
                preco += i.getPreco();
            }
            if (desconto != 0) {
                preco = preco - ((preco * desconto) / 100);
            }
            return preco;
        }catch (Exception e){return 0;}
    }

    public void removeItem(Item i, Configuracao c){
        c.removeItem(i);
    }

    public void configuracaoCustomizada(){
        configuracoes.put(inUseConfig.getId(), inUseConfig);
    }

    public  void encomenda(Item i){ itens.put(i.getId(),i);}

    public  int validaConfiguracao(Configuracao c){
        switch (c.getEstado()) {
            case "N":
                c.setEstado("V");
                configuracoes.put(c.getId(), c);
                return c.getId();
            case "V":
                return -2;
            default:
                return -1;
        }
    }

    public int produzConfiguracao(){
        List<Configuracao> lc = configuracoes.values().stream().filter(c1 -> c1.getEstado().equals("V")).
            sorted(Comparator.comparing(Configuracao::getData)).filter(this::existeStock).collect(toList());
        Configuracao c;
        if(!lc.isEmpty()){
            c = lc.get(0);
            c.getItens().values().forEach(i -> {
                Item it = itens.get(i.getId());
                it.setStock(it.getStock()-1);
                itens.put(it.getId(),it);
            });
            c.setEstado("P");
            configuracoes.put(c.getId(),c);

            return c.getId();
        } else
            return -2;
    }

    private boolean existeStock(Configuracao c1){
         return c1.getItens().values().stream().noneMatch(i1 -> (i1.getStock() == 0));
    }

    public void configuracaoOtima(Configuracao c, float orc){
        float choice = 0;
        float diff_c = 0;

        List<String> tipos = new ArrayList<>();
        tipos.add("Modelo");
        tipos.add("Cor");

        Item modelo = c.getItens().values().stream().filter(i -> i.getTipo().equals("Modelo")).findAny().orElse(null);

        try {
            if (!modelo.getDepend().isEmpty()) {
                List<Item> depend = dependencias(modelo, c.getItens());
                for (Item d : depend) {
                    addItem(d, c);
                    tipos.add(d.getTipo());
                }
            }

            if (!tipos.contains("Corpo")) {
                List<Item> p_corpo = itens.values().stream().filter(i -> i.getTipo().equals("Corpo")).collect(toList());

                for (Item p : p_corpo) {
                    if (orc > p.getPreco()) {
                        removeSametype(c, p);
                        addItem(p, c);
                        choice = p.getPreco();
                        diff_c = choice - (p.getPreco());
                    }
                }

                orc = orc - choice + diff_c;
            }

            if (!tipos.contains("Jantes")) {
                List<Item> p_jantes = itens.values().stream().filter(i -> i.getTipo().equals("Jantes")).collect(toList());

                for (Item p : p_jantes) {
                    if (orc > p.getPreco()) {
                        removeSametype(c, p);
                        addItem(p, c);
                        choice = p.getPreco();
                        diff_c = choice - (p.getPreco());
                    }
                }

                orc = orc - choice + diff_c;
            }

            if (!tipos.contains("Pneus")) {
                List<Item> p_pneus = itens.values().stream().filter(i -> i.getTipo().equals("Pneus")).collect(toList());

                for (Item p : p_pneus) {
                    if (orc > p.getPreco()) {
                        removeSametype(c, p);
                        addItem(p, c);
                        choice = p.getPreco();
                        diff_c = choice - (p.getPreco());
                    }
                }

                orc = orc - choice + diff_c;
            }

            if (!tipos.contains("Volante")) {
                List<Item> p_volante = itens.values().stream().filter(i -> i.getTipo().equals("Volante")).collect(toList());

                for (Item p : p_volante) {
                    if (orc > p.getPreco()) {
                        removeSametype(c, p);
                        addItem(p, c);
                        choice = p.getPreco();
                        diff_c = choice - (p.getPreco());
                    }
                }

                orc = orc - choice + diff_c;
            }

            if (!tipos.contains("Bancos")) {
                List<Item> p_bancos = itens.values().stream().filter(i -> i.getTipo().equals("Bancos")).collect(toList());
                for (Item p : p_bancos) {
                    if (orc > p.getPreco()) {
                        removeSametype(c, p);
                        addItem(p, c);
                        choice = p.getPreco();
                        diff_c = choice - (p.getPreco());
                    }
                }

                orc = orc - choice + diff_c;
            }

            if (!tipos.contains("Estofos")) {
                List<Item> p_estofos = itens.values().stream().filter(i -> i.getTipo().equals("Estofos")).collect(toList());

                for (Item p : p_estofos) {
                    if (orc > p.getPreco()) {
                        removeSametype(c, p);
                        addItem(p, c);
                        choice = p.getPreco();
                        diff_c = choice - (p.getPreco());
                    }
                }

                orc = orc - choice + diff_c;
            }

        }catch (NullPointerException e){
            e.getMessage();
        }
    }
}

