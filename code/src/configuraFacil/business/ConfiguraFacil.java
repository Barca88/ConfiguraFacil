package configuraFacil.business;
import configuraFacil.business.models.Configuracao;
import configuraFacil.business.models.Pacote;
import configuraFacil.business.models.items.Item;
import configuraFacil.business.models.users.Administrador;
import configuraFacil.business.models.users.Fabricante;
import configuraFacil.business.models.users.Utilizador;

import configuraFacil.dataBase.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sun.awt.ConstrainableGraphics;
import sun.nio.cs.UTF_32LE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ConfiguraFacil {

    private UtilizadorDao utilizadorDao;
    private ConfiguracaoDao configDao;
    private ItemDao itemDao;
    private PacoteDao pacoteDao;
    private Utilizador logged;
    private Configuracao inUseConfig;

    public ConfiguraFacil() {
        utilizadorDao = new UtilizadorDao();
        configDao = new ConfiguracaoDao();
        itemDao = new ItemDao();
        pacoteDao = new PacoteDao();
        logged = null;
    }

    public int login(String email, String password,int i) throws NullPointerException {
        Utilizador us;

        try {
            if (utilizadorDao.containsKey(email)) {
                us = utilizadorDao.get(email);

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

    public ObservableList<Configuracao> getConfiguracoes(){
        List<Configuracao> lc = new ArrayList<>(configDao.values());
        return FXCollections.observableArrayList(lc);
    }
    public ObservableList<Item> getStockitems(){
        List<Item> li = new ArrayList<>(itemDao.values());
        return FXCollections.observableArrayList(li);
    }
    public ObservableList<Utilizador> getVendedores(){
        List<Utilizador> lv = new ArrayList<>(utilizadorDao.values().stream().filter(i -> i.getClass().getSimpleName().equals("Vendedor")).collect(toList()));
        return FXCollections.observableArrayList(lv);
    }

    public ObservableList<Utilizador> getFabricantes(){
        List<Utilizador> lv = new ArrayList<>(utilizadorDao.values().stream().filter(i -> i.getClass().getSimpleName().equals("Fabricante")).collect(toList()));
        return FXCollections.observableArrayList(lv);
    }
    public ObservableList<Item> getItems(){
        List<Item> li = new ArrayList<>(itemDao.values());
        return FXCollections.observableArrayList(li);
    }
    public ObservableList<String> getModelos(){
        List<String> lm = itemDao.values().stream().filter(i -> i.getTipo().equals("Modelo")).map(Item::getNome).collect(Collectors.toList());
        return FXCollections.observableArrayList(lm);
    }

    public ObservableList<String> getCores() {
        List<String> lm = itemDao.values().stream().filter(i -> i.getTipo().equals("Cor")).map(Item::getNome).collect(Collectors.toList());
        return FXCollections.observableArrayList(lm);
    }
    public ObservableList<String> getJantes(){
        List<String> lm = itemDao.values().stream().filter(i -> i.getTipo().equals("Jantes")).map(Item::getNome).collect(Collectors.toList());
        return FXCollections.observableArrayList(lm);
    }

    public ObservableList<String> getPneus(){
        List<String> lm = itemDao.values().stream().filter(i -> i.getTipo().equals("Pneus")).map(Item::getNome).collect(Collectors.toList());
        return FXCollections.observableArrayList(lm);
    }

    public ObservableList<String> getCorpos(){
        List<String> lm = itemDao.values().stream().filter(i -> i.getTipo().equals("Corpo")).map(Item::getNome).collect(Collectors.toList());
        return FXCollections.observableArrayList(lm);
    }

    public ObservableList<String> getVolantes(){
        List<String> lm = itemDao.values().stream().filter(i -> i.getTipo().equals("Volante")).map(Item::getNome).collect(Collectors.toList());
        return FXCollections.observableArrayList(lm);
    }

    public ObservableList<String> getBancos(){
        List<String> lm = itemDao.values().stream().filter(i -> i.getTipo().equals("Bancos")).map(Item::getNome).collect(Collectors.toList());
        return FXCollections.observableArrayList(lm);
    }

    public ObservableList<String> getEstofos(){
        List<String> lm = itemDao.values().stream().filter(i -> i.getTipo().equals("Estofos")).map(Item::getNome).collect(Collectors.toList());
        return FXCollections.observableArrayList(lm);
    }

    public ObservableList<String> getOpcionais(){
        List<String> lm = itemDao.values().stream().filter(i -> i.getTipo().equals("Opcional")).map(Item::getNome).collect(Collectors.toList());
        return FXCollections.observableArrayList(lm);
    }

    public ObservableList<String> getPacotes(){
        List<String> lm = pacoteDao.values().stream().map(Pacote::getNome).collect(Collectors.toList());
        return FXCollections.observableArrayList(lm);
    }

    public List <Item> getItemPacote(String nome){
        return pacoteDao.values().stream().filter(p -> p.getNome().equals(nome)).collect(Collectors.toList()).get(0).getItens().values().stream().collect(Collectors.toList());
    }

    public List<Item> incompatibilidades (Item item, Map<Integer,Item> conf){
        List<Item> incomp = new ArrayList<>();
        if(!(item == null)){
        for(int id : item.getIncomp()){
            if (conf.containsKey(id))
                incomp.add(itemDao.get(id));
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
                 depend.add(itemDao.get(id));
            }
        return depend;
        }
        else return null;
    }

    public List<Item> oldDependent(Configuracao c, Item old){
        List <Item> dependentes = new ArrayList<>();
        for(Item i : c.getItens().values()){
            if(i.getDepend().contains(old.getId())){
                dependentes.add(i);
            }
        }
        return dependentes;
    }

    public void removeSametype(Configuracao c, Item i){
        for (Item i2 : c.getItens().values()){
            if (i.getTipo().equals(i2.getTipo())){
                c.removeItem(i2);
            }
        }
    }

    public int checkPacote(Configuracao c){
        List<Pacote> pacotes = pacoteDao.values().stream().collect(toList());
        int pId = 0;
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
            else pitens.removeAll(p.getItens().values().stream().map(i -> i.getId()).collect(toList()));
        }
        return pId ;
    }

    public  Pacote getPacote(int id){
        return pacoteDao.get(id);
    }

    public float getDesconto(int id){
        return pacoteDao.get(id).getDesconto();
    }

    public void  addItem (Item i, Configuracao c){
        c.addItem(i);
    }

    public void removeItem(Item i, Configuracao c){
        c.removeItem(i);
    }

    public float price (List<Item> itens, float desconto){
        float preco = 0;
        for(Item i : itens){
            preco += i.getPreco();
        }
        if (desconto != 0){
            preco = preco - ((preco  * desconto)/100);
        }
        return preco;
    }

    public void adicionarNovaConfiguracao(){
        configDao.put(inUseConfig.getId(), inUseConfig);


    }

    public Utilizador getLogged() {
        return logged;
    }

    private void setLogged(Utilizador logged) {
        this.logged = logged;
    }

    public Configuracao getInUseConfig() {
        return inUseConfig;
    }

    public void setInUseConfig(Configuracao configConsulta) {
        this.inUseConfig = configConsulta;
    }
}

