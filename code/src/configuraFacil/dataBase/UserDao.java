package configuraFacil.dataBase;

import configuraFacil.business.models.users.Administrador;
import configuraFacil.business.models.users.Fabricante;
import configuraFacil.business.models.users.User;
import configuraFacil.business.models.users.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class UserDao implements Map<String, User> {

    private Connection conn;


    public boolean containsKey(Object key){
        boolean ret = false;
        try{
            conn = Connect.connect();
            String sql = "SELECT 'nome' FROM 'utilizador' WHERE nome=?";
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


    public User get(Object key){
        User u = null;
        try{
            conn = Connect.connect();
            String sql = "SELECT * FROM 'utilizador' WHERE nome=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,(String)key);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                switch (rs.getString("tipo")){

                    case "a":
                        u = (Administrador) new User(rs.getInt("id"),rs.getString("nome"),rs.getString("password"),rs.getString("email"),rs.getString("telemovel"));
                        break;

                    case "f":
                        u = (Fabricante) new User(rs.getInt("id"),rs.getString("nome"),rs.getString("password"),rs.getString("email"),rs.getString("telemovel"));
                        break;

                    case "v":
                        u = (Vendedor) new User(rs.getInt("id"),rs.getString("nome"),rs.getString("password"),rs.getString("email"),rs.getString("telemovel"));
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
    public User put(String s, User user) {
        User u = null;
        try{
            conn = Connect.connect();
            PreparedStatement stm = conn.prepareStatement("INSERT INTO utilizador\n" + "VALUES (?,?,?,?,?)\n" +
                    "ON DUPLICATE KEY UPDATE nome=VALUES(nome), password=VALUES(password), email=VALUES(email), telemovel=VALUES(telemovel)", Statement.RETURN_GENERATED_KEYS);

            stm.setString(1,user.getNome());
            stm.setString(2,user.getPassword());
            stm.setString(4,user.getEmail());
            stm.setString(5,user.getTel());
            switch(user.getClass().getName()){
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
                user.setId(newid);
            }
            u = user;
        }catch (Exception e) {
            e.printStackTrace();

        }finally {
            Connect.close(conn);
        }

        return u;
    }

    @Override
    public User remove(Object o) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends User> map) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<User> values() {
        return null;
    }

    @Override
    public Set<Entry<String, User>> entrySet() {
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
