package configuraFacil.business;

import configuraFacil.business.models.users.Administrador;
import configuraFacil.business.models.Configuracao;
import configuraFacil.business.models.users.Fabricante;
import configuraFacil.business.models.users.Vendedor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfiguraFacil {

    private Map<String, Administrador> admins;
    private Map<String, Fabricante> fabricantes;
    private Map<String, Vendedor> vendedores;
    private Map<String, Configuracao> configuracoes;
    private ObservableList<Configuracao> oc;
    private int logged;

    public ConfiguraFacil() {
        admins = new HashMap<>();
        fabricantes = new HashMap<>();
        vendedores = new HashMap<>();
        configuracoes = new HashMap<>();

        logged = -1;

        Administrador ad1 = new Administrador(1,"shaman","leal","a","123");
        admins.put(ad1.getNome(),ad1);

        Fabricante fa1 = new Fabricante(1,"donatello","curopos","a","123");
        fabricantes.put(fa1.getNome(),fa1);
        Fabricante fa2 = new Fabricante(1,"ana","cerineu72","a","123");
        fabricantes.put(fa2.getNome(),fa2);

        Vendedor vd1 = new Vendedor(1,"barca","matias","a","123");
        vendedores.put(vd1.getNome(),vd1);

        configuracoes.put("PRODUZIDA",new Configuracao(0,"PRODUZIDA"));
        configuracoes.put( "NAOVALIDA", new Configuracao(1,"NAOVALIDA"));
        configuracoes.put("VALIDA", new Configuracao(2,"VALIDA"));
    }

    public int login(String nome, String password, int userkind) {
        switch (userkind){
            case 0 :
                if (admins.containsKey(nome)){
                    if (admins.get(nome).getPassword().equals(password)){
                        logged = admins.get(nome).getId();
                        return 1;
                    }
                    else
                        return 0;
                }else
                    return 2;
            case 1 :
                if (fabricantes.containsKey(nome)){
                    if (fabricantes.get(nome).getPassword().equals(password)){
                        logged = fabricantes.get(nome).getId();
                        return 1;
                    }
                    else
                        return 0;
                }else
                    return 2;
            case 2 :
                if (vendedores.containsKey(nome)){
                    if (vendedores.get(nome).getPassword().equals(password)) {
                        logged = vendedores.get(nome).getId();
                        return 1;
                    }
                    else
                        return 0;
                }else
                    return 2;
            default:
                System.out.println("Tipo de utilizador invalido");
                return -1;
        }
    }

    public void adicionarConfiguracao(Configuracao config){
        configuracoes.put(config.getEstado(),config);
    }

    public void removeConfiguracao(Configuracao config){
        configuracoes.put(config.getEstado(),config);
    }

    public ObservableList<Configuracao> getConfiguracoes(){
        List<Configuracao> lc = new ArrayList<>(configuracoes.values());
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
