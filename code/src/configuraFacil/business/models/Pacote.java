package configuraFacil.business.models;

import configuraFacil.business.models.items.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;

public class Pacote {

    private int id;
    private float desconto;
    private String nome;
    private Map<Long, Item> items;

    public Pacote(int id, float desconto, String nome) {
        this.id = id;
        this.desconto = desconto;
        this.nome = nome;
        this.items = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public float getDesconto() {
        return desconto;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public Map<Long,Item> getItems(){
        HashMap<Long,Item> r = new HashMap<Long,Item>();
        for(Item i : this.items.values()){
            r.put(i.getId(),i.clone());
        }
        return r;
    }
    public void setItems(HashMap<Long,Item> newItems){
        HashMap<Long,Item> r = new HashMap<Long,Item>();
        for(Item i : newItems.values()){
            r.put(i.getId(),i.clone());
        }
        this.items = r;
    }

    @Override
    public String toString() {
        return "Pacote{" +
                "id=" + id +
                ", desconto=" + desconto +
                ", nome='" + nome + '\'' +
                '}';
    }
}
