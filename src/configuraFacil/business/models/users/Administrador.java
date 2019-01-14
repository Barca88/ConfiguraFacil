package configuraFacil.business.models.users;

public class Administrador extends Utilizador {

    public Administrador(int id, String nome, String password, String email, String tel){
        super(id, nome, password, email, tel);
    }

    @Override
    public Utilizador clone() {
        return super.clone();
    }
}
