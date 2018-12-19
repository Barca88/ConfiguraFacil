package configuraFacil.business.models.users;

public class Vendedor extends User {

    public Vendedor(int id, String nome, String password, String email, String tel){
        super(id, nome, password, email, tel);
    }

    @Override
    public User Clone() {
        return super.Clone();
    }
}
