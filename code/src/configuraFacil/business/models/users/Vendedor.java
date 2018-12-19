package configuraFacil.business.models.users;

public class Vendedor extends Utilizador {

    public Vendedor(int id, String nome, String password, String email, String tel){
        super(id, nome, password, email, tel);
    }

    @Override
    public Utilizador clone() {
        return super.clone();
    }
}
