package configuraFacil.business;
import configuraFacil.business.models.Configuracao;
import configuraFacil.business.models.users.Administrador;
import configuraFacil.business.models.users.Fabricante;
import configuraFacil.business.models.users.Utilizador;

import configuraFacil.dataBase.ConfiguracaoDao;
import configuraFacil.dataBase.UtilizadorDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConfiguraFacil {

    private UtilizadorDao utilizadorDao;
    private ConfiguracaoDao configDao;
    private Map<String, Configuracao> configuracoes;
    private ObservableList<Configuracao> oc;
    private Utilizador logged;
    private Configuracao configConsulta;

    public ConfiguraFacil() {
        utilizadorDao = new UtilizadorDao();
        configDao = new ConfiguracaoDao();
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
                    logged = us;

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

    public Configuracao getConfigConsulta() {
        return configConsulta;
    }

    public void setConfigConsulta(Configuracao configConsulta) {
        this.configConsulta = configConsulta;
    }

    public Utilizador getLogged() {
        return logged;
    }

    public void setLogged(Utilizador logged) {
        this.logged = logged;
    }
}
