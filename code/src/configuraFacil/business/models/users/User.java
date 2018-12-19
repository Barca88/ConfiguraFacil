package configuraFacil.business.models.users;

public class User {
    private int id;
    private String nome;
    private String password;
    private String email;
    private String tel;

    public User(){
        this.id = -1;
        this.nome = null;
        this.password = null;
        this.email = null;
        this.tel = null;
    }
    public User(User u){
        this.id = u.getId();
        this.nome = u.getNome();
        this.password = u.getPassword();
        this.email = u.getEmail();
        this.tel = u.getTel();
    }
    public User(int id, String nome, String password, String email, String tel) {
        this.id = id;
        this.nome = nome;
        this.password = password;
        this.email = email;
        this.tel = tel;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public User Clone(){
        return new User(this);
    }
}