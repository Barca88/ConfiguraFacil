package configuraFacil.business.models;


import java.util.List;

public class Item {

    private int id;
    private String nome;
    private float preco;
    private int stock;
    private String tipo;
    private List<Integer> incomp;
    private List<Integer> depend;


    public Item(int id, String nome, float preco, int stock, String tipo, List<Integer> incomp, List<Integer> depend) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.stock = stock;
        this.tipo = tipo;
        this.incomp = incomp;
        this.depend = depend;

    }

    public Item(Item i){
        this.id = i.getId();
        this.nome = i.getNome();
        this.preco = i.getPreco();
        this.stock = i.getStock();
        this.tipo = i.getTipo();
        this.depend = i.getDepend();
        this.incomp = i.getIncomp();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Item clone(){
        return new Item(this);
    }

    public List<Integer> getIncomp() {
        return incomp;
    }

    public void setIncomp(List<Integer> incomp) {
        this.incomp = incomp;
    }

    public List<Integer> getDepend() {
        return depend;
    }

    public void setDepend(List<Integer> depend) {
        this.depend = depend;
    }

    @Override
    public String toString() {
        return "Item " + id +
                "\n     nome : '" + nome + '\'' +
                "\n     preço: " + preco +
                "\n     stock: " + stock;
    }
}

