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
import sun.nio.cs.UTF_32LE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ConfiguraFacil {

    private UtilizadorDao utilizadorDao;
    private ConfiguracaoDao configDao;
    private ClienteDao clienteDao;
    private ItemDao itemDao;
    private PacoteDao pacoteDao;

    private ObservableList<Configuracao> oc;
    private ObservableList<Utilizador> ov;
    private ObservableList<Utilizador> of;
    private ObservableList<Item> oi;
    private ObservableList<String> o_modelo;
    private ObservableList<String> o_cor;
    private ObservableList<String> o_jantes;
    private ObservableList<String> o_pneu;
    private ObservableList<String> o_corpo;
    private ObservableList<String> o_volante;
    private ObservableList<String> o_bancos;
    private ObservableList<String> o_estofos;
    private ObservableList<String> o_op;
    private ObservableList<String> o_pacote;

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
        oc = FXCollections.observableArrayList(lc);
        return oc;
    }

    public ObservableList<Utilizador> getVendedores(){
        List<Utilizador> lv = new ArrayList<>(utilizadorDao.values().stream().filter(i -> i.getClass().getSimpleName().equals("Vendedor")).collect(toList()));
        ov = FXCollections.observableArrayList(lv);
        return ov;
    }

    public ObservableList<Utilizador> getFabricantes(){
        List<Utilizador> lv = new ArrayList<>(utilizadorDao.values().stream().filter(i -> i.getClass().getSimpleName().equals("Fabricante")).collect(toList()));
        of = FXCollections.observableArrayList(lv);
        return of;
    }

    public ObservableList<Item> getItems(){
        List<Item> li = new ArrayList<>(itemDao.values());
        oi = FXCollections.observableArrayList(li);
        return oi;
    }

    public ObservableList<String> getModelos(){
        List<String> lm = itemDao.values().stream().filter(i -> i.getTipo().equals("Modelo")).map(Item::getNome).collect(Collectors.toList());
        o_modelo= FXCollections.observableArrayList(lm);
        return o_modelo;
    }

    public ObservableList<String> getCores(){
        List<String> lm = itemDao.values().stream().filter(i -> i.getTipo().equals("Cor")).map(Item::getNome).collect(Collectors.toList());
        o_cor = FXCollections.observableArrayList(lm);
        return o_cor;
    }

    public ObservableList<String> getJantes(){
        List<String> lm = itemDao.values().stream().filter(i -> i.getTipo().equals("Jantes")).map(Item::getNome).collect(Collectors.toList());
        o_jantes = FXCollections.observableArrayList(lm);
        return o_jantes;
    }

    public ObservableList<String> getPneus(){
        List<String> lm = itemDao.values().stream().filter(i -> i.getTipo().equals("Pneus")).map(Item::getNome).collect(Collectors.toList());
        o_pneu = FXCollections.observableArrayList(lm);
        return o_pneu;
    }

    public ObservableList<String> getCorpos(){
        List<String> lm = itemDao.values().stream().filter(i -> i.getTipo().equals("Corpo")).map(Item::getNome).collect(Collectors.toList());
        o_corpo = FXCollections.observableArrayList(lm);
        return o_corpo;
    }

    public ObservableList<String> getVolantes(){
        List<String> lm = itemDao.values().stream().filter(i -> i.getTipo().equals("Volante")).map(Item::getNome).collect(Collectors.toList());
        o_volante = FXCollections.observableArrayList(lm);
        return o_volante;
    }


    public ObservableList<String> getBancos(){
        List<String> lm = itemDao.values().stream().filter(i -> i.getTipo().equals("Bancos")).map(Item::getNome).collect(Collectors.toList());
        o_bancos = FXCollections.observableArrayList(lm);
        return o_bancos;
    }


    public ObservableList<String> getEstofos(){
        List<String> lm = itemDao.values().stream().filter(i -> i.getTipo().equals("Estofos")).map(Item::getNome).collect(Collectors.toList());
        o_estofos = FXCollections.observableArrayList(lm);
        return o_estofos;
    }

    public ObservableList<String> getOpcionais(){
        List<String> lm = itemDao.values().stream().filter(i -> i.getTipo().equals("Opcional")).map(Item::getNome).collect(Collectors.toList());
        o_op = FXCollections.observableArrayList(lm);
        return o_op;
    }

    public ObservableList<String> getPacotes(){
        List<String> lm = pacoteDao.values().stream().map(Pacote::getNome).collect(Collectors.toList());
        o_pacote = FXCollections.observableArrayList(lm);
        return o_pacote;
    }

    public List<Item> incompatibilidades (Item item, Map<Integer,Item> conf){
        List<Item> incomp = new ArrayList<>();
        for(int id : item.getIncomp()){
            if (conf.containsKey(id))
                incomp.add(itemDao.get(id));
        }
        return incomp;
    }

    public List<Item> dependencias (Item item, Map<Integer,Item> conf){
        List<Item> depend = new ArrayList<>();
        for(int id : item.getDepend()){
            if (conf.containsKey(id))
                depend.add(itemDao.get(id));
        }
        return depend;
    }

    public Utilizador getLogged() {
        return logged;
    }

    public void setLogged(Utilizador logged) {
        this.logged = logged;
    }

    public Configuracao getInUseConfig() {
        return inUseConfig;
    }

    public void setInUseConfig(Configuracao configConsulta) {
        this.inUseConfig = configConsulta;
    }
}

