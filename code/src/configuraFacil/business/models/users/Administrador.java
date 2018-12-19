package configuraFacil.business.models.users;

public class Administrador extends User {

    public Administrador(int id, String nome, String password, String email, String tel){
        super(id, nome, password, email, tel);
    }

    @Override
    public User Clone() {
        return super.Clone();
    }
}
