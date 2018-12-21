package configuraFacil.business.models;

public class Cliente {

    private int id;
    private String nome;
    private String email;
    private String telemovel;

    public Cliente(){
        this.id = -1;
        this.nome = null;
        this.email = null;
        this.telemovel = null;
    }
    public Cliente(Cliente c){
        this.id = c.getId();
        this.nome = c.getNome();
        this.email = c.getEmail();
        this.telemovel = c.getTelemovel();
    }
    public Cliente(int id, String nome, String email, String telemovel) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telemovel = telemovel;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    public Cliente clone(){
        return new Cliente(this);
    }
}
