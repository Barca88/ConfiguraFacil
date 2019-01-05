package configuraFacil.business.models;

import configuraFacil.business.models.users.Utilizador;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Configuracao {

    private int id;
    private String estado;
    private float orcamento;
    private float preco;
    private Cliente cliente;
    private Utilizador vendedor;
    private LocalDate data;
    private Map<Integer, Item> itens;


    public Configuracao(){
        this.id = -1;
        this.estado = "N";
        this.orcamento = 0;
        this.preco = 0;
        this.cliente = null;
        this.vendedor = null;
        this.data = null;
        this.itens = new HashMap<>();
    }

    public Configuracao(int id, String estado, float orc, float prc, Cliente cliente,Utilizador vendedor,LocalDate data, Map<Integer, Item> itens) {
        this.id = id;
        this.estado = estado;
        this.orcamento = orc;
        this.preco = prc;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.data = data;
        this.itens = itens;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
       try{return this.getItens().values().stream().filter(i -> i.getTipo().equals("Modelo")).map(Item::getNome).findFirst().get();}
       catch (Exception e){return null;}
    }

    public String getCor() {
        try {
            return this.getItens().values().stream().filter(i -> i.getTipo().equals("Cor")).map(Item::getNome).findFirst().get();
        } catch (Exception e) {
            return null;
        }
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

    public float getPreco() { return preco; }

    public void  setPreco(float prc) { this.preco = prc; }

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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Map<Integer,Item> getItens() {
        HashMap<Integer,Item> r = new HashMap<>();
        for(Item i: this.itens.values()){
            r.put(i.getId(),i.clone());
        }
        return r;
    }

    public void setItens(Map<Integer, Item> newItens) {
        HashMap<Integer,Item> r = new HashMap<>();
        for(Item i: newItens.values()) {
            r.put(i.getId(), i.clone());
        }
        this.itens = r;
    }


    public void addItem(Item item){
        this.itens.put(item.getId(),item);
    }

    public void removeItem(Item item){
        itens.remove(item.getId());
    }

    public void clearItens(){
        int modelo = this.getItens().values().stream().filter(i-> i.getTipo().equals("Modelo")).map(Item::getId).findAny().orElse(-1);
        int cor = this.getItens().values().stream().filter(i-> i.getTipo().equals("Cor")).map(Item::getId).findAny().orElse(-1);
        Item m = this.getItens().values().stream().filter(i-> i.getTipo().equals("Modelo")).findAny().orElse(null);
        Item c = this.getItens().values().stream().filter(i-> i.getTipo().equals("Cor")).findAny().orElse(null);

        this.itens.clear();

        if(modelo >= 0)
            itens.put(0,m);
        if(cor >= 0)
            itens.put(1,c);
    }
}
