package configuraFacil.business.models;

import configuraFacil.business.models.items.Item;
import configuraFacil.business.models.users.Utilizador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Configuracao {

    private int id;
    private String estado;
    private float orcamento;
    private Cliente cliente;
    private Utilizador vendedor;
    private Map<Integer, Item> itens;


    public Configuracao(){
        this.id = -1;
        this.estado = "N";
        this.orcamento = 0;
        this.cliente = null;
        this.vendedor = null;
        this.itens = new HashMap<>();

    }

    public Configuracao(int id, String estado, float orc, Cliente cliente,Utilizador vendedor, Map<Integer, Item> itens) {
        this.id = id;
        this.estado = estado;
        this.orcamento = orc;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.itens = itens;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo(){
        try {
            return this.getItens().values().stream().filter(i -> i.getTipo().equals("Modelo")).map(Item::getNome).findFirst().get();
        }catch (Exception e){return null;} 
    }

    public String getCor(){
        try{
            return this.getItens().values().stream().filter(i -> i.getTipo().equals("Cor")).map(Item::getNome).findFirst().get();
        }catch (Exception e){return null;}
    }


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(float orc) {
        this.orcamento = orc;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Utilizador getVendedor() {
        return vendedor;
    }

    public void setVendedor(Utilizador vendedor) {
        this.vendedor = vendedor;
    }

    public Map<Integer,Item> getItens() {
        HashMap<Integer,Item> r = new HashMap<Integer,Item>();
        for(Item i: this.itens.values()){
            r.put(i.getId(),i.clone());
        }
        return r;
    }

    public void setItens(Map<Integer, Item> newItens) {
        HashMap<Integer,Item> r = new HashMap<Integer,Item>();
        for(Item i: newItens.values()) {
            r.put(i.getId(), i.clone());
        }
        this.itens = r;
    }


    public void addItem(Item item){
        this.itens.put(item.getId(),item);
    }

    public void removeItem(Item item){
        this.itens.remove(item.getId());
    }
}
