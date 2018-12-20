package configuraFacil.business.models.items;

public class Item {

    private int id;
    private String nome;
    private float preco;
    private int stock;


    public Item(int id, String nome, float preco, int stock) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.stock = stock;

    }

    public Item(Item i){
        this.id = i.getId();
        this.nome = i.getNome();
        this.preco = i.getPreco();
        this.stock = i.getStock();
    }

    public int getId() {
        return id;
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

    public Item clone(){
        return new Item(this);
    }

    @Override
    public String toString() {
        return "Item " + id +
                "\n     nome : '" + nome + '\'' +
                "\n     preço: " + preco +
                "\n     stock: " + stock;
    }
}

