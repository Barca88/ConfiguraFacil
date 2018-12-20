package configuraFacil.business;

import configuraFacil.business.models.users.Administrador;
import configuraFacil.business.models.Configuracao;
import configuraFacil.business.models.users.Fabricante;
import configuraFacil.business.models.users.Utilizador;
import configuraFacil.business.models.users.Vendedor;

import configuraFacil.dataBase.ClienteDao;
import configuraFacil.dataBase.ConfiguracaoDao;
import configuraFacil.dataBase.UtilizadorDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfiguraFacil {

    private UtilizadorDao utilizadorDao;
    private ConfiguracaoDao configDao;
    private ClienteDao clienteDao;
    private Map<String, Configuracao> configuracoes;
    private ObservableList<Configuracao> oc;
    private int logged;
    private Utilizador u;

    public ConfiguraFacil() {
        utilizadorDao = new UtilizadorDao();
        configDao = new ConfiguracaoDao();

        logged = -1;
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
                    u = us.clone();
                    return 1;
                } else return 0;
            } else return 2;


        }catch (Exception e){
            throw new NullPointerException(e.getMessage());
        }

    }

    public void adicionarConfiguracao(Configuracao config){
        configuracoes.put(config.getEstado(),config);
    }

    public void removeConfiguracao(Configuracao config){
        configuracoes.put(config.getEstado(),config);
    }

    public ObservableList<Configuracao> getConfiguracoes(){
        List<Configuracao> lc = new ArrayList<>(configDao.values());
        oc = FXCollections.observableArrayList(lc);
        return oc;
    }


    public int getLogged() {
        return logged;
    }

    public void setLogged(int logged) {
        this.logged = logged;
    }
}
