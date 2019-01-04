package configuraFacil.business.models.users;

public class Fabricante extends Utilizador {

    public Fabricante(int id, String nome, String password, String email, String tel){
        super(id, nome, password, email, tel);
    }

    @Override
    public Utilizador clone() {
        return super.clone();
    }
}
