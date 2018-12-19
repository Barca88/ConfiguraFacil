package configuraFacil.dataBase;

import configuraFacil.business.models.users.Administrador;
import configuraFacil.business.models.users.Fabricante;
import configuraFacil.business.models.users.Utilizador;
import configuraFacil.business.models.users.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class UtilizadorDao implements Map<String, Utilizador> {

    private Connection conn;





    public boolean containsKey(Object key) throws NullPointerException{
        boolean ret = false;

        try{
            conn = Connect.connect();
            String sql = "SELECT 'email' FROM 'utilizador' WHERE email=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,(String)key);
            ResultSet rs = stm.executeQuery();
            ret = rs.next();

        } catch (Exception e){
            throw  new NullPointerException(e.getMessage());

        } finally {
             Connect.close(conn);
        }

        return ret;
    }

    @Override
    public boolean containsValue(Object o) {
        return false;
    }


    public Utilizador get(Object key){
        Utilizador u = null;
        try{
            conn = Connect.connect();
            String sql = "SELECT * FROM 'utilizador' WHERE email=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,(String)key);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                switch (rs.getString("tipo")){

                    case "a":
                        u = (Administrador) new Utilizador(rs.getInt("id"),rs.getString("nome"),rs.getString("password"),rs.getString("email"),rs.getString("telemovel"));
                        break;

                    case "f":
                        u = (Fabricante) new Utilizador(rs.getInt("id"),rs.getString("nome"),rs.getString("password"),rs.getString("email"),rs.getString("telemovel"));
                        break;

                    case "v":
                        u = (Vendedor) new Utilizador(rs.getInt("id"),rs.getString("nome"),rs.getString("password"),rs.getString("email"),rs.getString("telemovel"));
                        break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            Connect.close(conn);
        }
        return u;
    }

    @Override
    public Utilizador put(String s, Utilizador Utilizador) {
        Utilizador u = null;
        try{
            conn = Connect.connect();
            PreparedStatement stm = conn.prepareStatement("INSERT INTO utilizador\n" + "VALUES (?,?,?,?,?)\n" +
                    "ON DUPLICATE KEY UPDATE nome=VALUES(nome), password=VALUES(password), email=VALUES(email), telemovel=VALUES(telemovel)", Statement.RETURN_GENERATED_KEYS);

            stm.setString(1,Utilizador.getNome());
            stm.setString(2,Utilizador.getPassword());
            stm.setString(4,Utilizador.getEmail());
            stm.setString(5,Utilizador.getTel());
            switch(Utilizador.getClass().getName()){
                case "Administrador":
                    stm.setString(3,"a");
                    break;

                case "Fabricante":
                    stm.setString(3,"f");
                    break;

                case "Vendedor":
                    stm.setString(3,"v");
                    break;
            }
            stm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();
            if(rs.next()){
                int newid = rs.getInt(1);
                Utilizador.setId(newid);
            }
            u = Utilizador;
        }catch (Exception e) {
            e.printStackTrace();

        }finally {
            Connect.close(conn);
        }

        return u;
    }

    @Override
    public Utilizador remove(Object o) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Utilizador> map) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<Utilizador> values() {
        return null;
    }

    @Override
    public Set<Entry<String, Utilizador>> entrySet() {
        return null;
    }

    public int size(){
        int size = 0;
        try{
            conn = Connect.connect();
            String sql = "SELECT count(*) FROM utilizador";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()){
                size = rs.getInt(1);
            }

        }
        catch (Exception e){ throw new NullPointerException(e.getMessage());}
        finally {
            Connect.close(conn);
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

}
