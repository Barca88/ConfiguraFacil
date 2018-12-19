package configuraFacil.business.models;

public class Configuracao {

    private int id;
    private String estado;

    public Configuracao(){
        this.id = -1;
        this.estado = null;
    }
    public Configuracao(Configuracao f){
        this.id = f.getId();
        this.estado = f.getEstado();
    }
    public Configuracao(int id, String estado) {
        this.id = id;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Configuracao clone(){
        return new Configuracao(this);
    }
}
