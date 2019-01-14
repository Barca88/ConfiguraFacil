package configuraFacil.business.models;

import java.util.HashMap;
import java.util.Map;

public class Pacote {

    private int id;
    private float desconto;
    private String nome;
    private Map<Integer, Item> itens;

    public Pacote(){
        this.id = -1;
        this.desconto = 0;
        this.nome = null;
        this.itens = new HashMap<>();
    }
    public Pacote(Pacote p){
        this.id = p.getId();
        this.desconto = p.getDesconto();
        this.nome = p.getNome();
        this.itens = p.getItens();
    }
    public Pacote(int id, float desconto, String nome,Map<Integer,Item> i) {
        this.id = id;
        this.desconto = desconto;
        this.nome = nome;
        this.itens = i;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

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
    public Map<Integer,Item> getItens(){
        HashMap<Integer,Item> r = new HashMap<Integer,Item>();
        for(Item i : this.itens.values()){
            r.put(i.getId(),i.clone());
        }
        return r;
    }
    public void setItens(HashMap<Integer,Item> newItens){
        HashMap<Integer,Item> r = new HashMap<Integer,Item>();
        for(Item i : newItens.values()){
            r.put(i.getId(),i.clone());
        }
        this.itens = r;
    }



    public Pacote clone(){
        return new Pacote(this);
    }
}
