package Facade;

import java.util.HashMap;
import java.util.Map;

public class ConfiguraFacil {

    private Map<String,Administrador> admins;
    private Map<String,Fabricante> fabricantes;
    private Map<String,Vendedor> vendedores;
    private int logged;

    public ConfiguraFacil() {
        admins = new HashMap<>();
        fabricantes = new HashMap<>();
        vendedores = new HashMap<>();
        logged = -1;

        Administrador ad1 = new Administrador(1,"shama","leal");
        admins.put(ad1.getNome(),ad1);

        Fabricante fa1 = new Fabricante(1,"donatelo","curopos");
        fabricantes.put(fa1.getNome(),fa1);
        Fabricante fa2 = new Fabricante(1,"ana","cerineu72");
        fabricantes.put(fa2.getNome(),fa2);

        Vendedor vd1 = new Vendedor(1,"barca","matias");
        vendedores.put(vd1.getNome(),vd1);
    }

    public int login(String nome, String password, int userkind) {
        logged = userkind;
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
}
