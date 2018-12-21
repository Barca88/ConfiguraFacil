package configuraFacil.dataBase;

import configuraFacil.business.models.Configuracao;
import configuraFacil.business.models.items.Item;
import configuraFacil.business.models.users.Administrador;
import configuraFacil.business.models.users.Fabricante;
import configuraFacil.business.models.users.Utilizador;
import configuraFacil.business.models.users.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class UtilizadorDao implements Map<String, Utilizador> {

    private Connection conn;


    public Utilizador get(Object key){
        Utilizador u = null;
        try{
            conn = Connect.connect();
            String sql = "SELECT * FROM Utilizador WHERE email=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,(String)key);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                switch (rs.getString("tipo")){

                    case "a":
                        u = new Administrador(rs.getInt("idUtilizador"),rs.getString("nome"),rs.getString("password"),rs.getString("email"),rs.getString("telemovel"));
                        break;

                    case "f":
                        u = new Fabricante(rs.getInt("idUtilizador"),rs.getString("nome"),rs.getString("password"),rs.getString("email"),rs.getString("telemovel"));
                        break;

                    case "v":
                        u = new Vendedor(rs.getInt("idUtilizador"),rs.getString("nome"),rs.getString("password"),rs.getString("email"),rs.getString("telemovel"));
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
            PreparedStatement stm = conn.prepareStatement("INSERT INTO Utilizador\n" + "VALUES (?,?,?,?,?)\n" +
                    "ON DUPLICATE KEY UPDATE nome=VALUES(nome), password=VALUES(password), email=VALUES(email), telemovel=VALUES(telemovel)", Statement.RETURN_GENERATED_KEYS);

            stm.setString(1,Utilizador.getNome());
            stm.setString(2,Utilizador.getPassword());
            stm.setString(4,s);
            stm.setString(5,Utilizador.getTel());
            switch(Utilizador.getClass().getSimpleName()){
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
        Utilizador u = this.get(o);
        try{
            conn = Connect.connect();
            String sql = "DELETE FROM Utilizador WHERE email=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,(String)o);
            stm.executeUpdate();
        }catch (Exception e){
            throw new NullPointerException(e.getMessage());
        }finally {
            Connect.close(conn);
        }
        return u;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Utilizador> map) {
        for(Utilizador a : map.values()){
            put(a.getEmail(),a);
        }
    }

    @Override
    public void clear() {
        try{
            conn = Connect.connect();
            String sql = "DELETE FROM Utilizador";
            Statement stm = conn.createStatement();
            stm.executeUpdate(sql);
        }catch (Exception e){
            throw new NullPointerException(e.getMessage());
        }finally {
            Connect.close(conn);
        }
    }

    @Override
    public Set<String> keySet() {
        throw new NullPointerException("Not implemented!");
    }

    @Override
    public Collection<Utilizador> values() {
        Collection<Utilizador> col = new HashSet<Utilizador>();
        try{
            conn = Connect.connect();
            String sql = "SELECT * FROM Utilizador";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                Utilizador u = null;
                switch (rs.getString("tipo")){

                    case "a":
                        u = new Administrador(rs.getInt("idUtilizador"),rs.getString("nome"),rs.getString("password"),rs.getString("email"),rs.getString("telemovel"));
                        break;
                    case "f":
                        u = new Fabricante(rs.getInt("idUtilizador"),rs.getString("nome"),rs.getString("password"),rs.getString("email"),rs.getString("telemovel"));
                        break;

                    case "v":
                        u = new Vendedor(rs.getInt("idUtilizador"),rs.getString("nome"),rs.getString("password"),rs.getString("email"),rs.getString("telemovel"));
                        break;
                }
                col.add(u);
            }


        }catch (Exception e){
            throw new NullPointerException(e.getMessage());
        }finally {
            Connect.close(conn);
        }

        col = col.stream().sorted(Comparator.comparingInt(Utilizador::getId)).collect(Collectors.toList());

        return col;
    }

    @Override
    public Set<Entry<String, Utilizador>> entrySet() {
        return null;
    }

    public int size(){
        int size = 0;
        try{
            conn = Connect.connect();
            String sql = "SELECT count(*) FROM Utilizador";
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
        boolean ret = true;
        try{
            conn = Connect.connect();
            String sql = "SELECT * FROM Utilizador";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()){
                ret = false;
            }

        }
        catch (Exception e){ throw new NullPointerException(e.getMessage());}
        finally {
            Connect.close(conn);
        }
        return ret;
    }


    public boolean containsKey(Object key) throws NullPointerException{
        boolean ret = false;

        try{
            conn = Connect.connect();
            String sql = "SELECT 'email' FROM Utilizador WHERE email=?";
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
        Utilizador u = (Utilizador) o;
        return containsKey(u.getId());
    }


}
