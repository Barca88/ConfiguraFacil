package configuraFacil.dataBase;

import configuraFacil.business.models.Cliente;
import configuraFacil.business.models.items.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class ClienteDao implements Map<Integer, Cliente> {

    private Connection conn;

    @Override
    public int size() {
        int size = 0;
        try{
            conn = Connect.connect();
            String sql = "SELECT count(*) FROM Cliente";
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
            String sql = "SELECT * FROM Cliente";
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

    @Override
    public boolean containsKey(Object key) {
        boolean ret = false;
        try{
            conn = Connect.connect();
            String sql = "SELECT 'email' FROM Cliente WHERE id=?";
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
    public boolean containsValue(Object value) {
        Cliente c = (Cliente) value;
        return containsKey(c.getId());
    }

    @Override
    public Cliente get(Object key) {
        Cliente c = null;
        try{
            conn = Connect.connect();
            String sql = "SELECT * FROM Cliente WHERE id=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,(String)key);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                c = new Cliente(rs.getInt("idCliente"),
                        rs.getString("email"),
                        rs.getString("nome"),
                        rs.getString("telemovel"));
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            Connect.close(conn);
        }
        return c;
    }

    @Override
    public Cliente put(Integer key, Cliente cli) {
        Cliente c = null;
        try{
            conn = Connect.connect();
            PreparedStatement stm = conn.prepareStatement("INSERT INTO Cliente\n" + "VALUES (?,?,?)\n" +
                    "ON DUPLICATE KEY UPDATE nome=VALUES(nome), password=VALUES(password), email=VALUES(email), telemovel=VALUES(telemovel)", Statement.RETURN_GENERATED_KEYS);

            stm.setString(1,cli.getNome());
            stm.setString(2,cli.getEmail());
            stm.setString(3,cli.getTelemovel());
            stm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();
            if(rs.next()){
                int newid = rs.getInt(1);
                cli.setId(newid);
            }
            c = cli;
        }catch (Exception e) {
            e.printStackTrace();

        }finally {
            Connect.close(conn);
        }

        return c;
    }

    @Override
    public Cliente remove(Object key) {
        Cliente c = this.get(key);
        try{
            conn = Connect.connect();
            String sql = "DELETE FROM Cliente WHERE id=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,(String)key);
            stm.executeUpdate();
        }catch (Exception e){
            throw new NullPointerException(e.getMessage());
        }finally {
            Connect.close(conn);
        }
        return c;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Cliente> m) {
        for(Cliente a : m.values()){
            put(a.getId(),a);
        }
    }

    @Override
    public void clear() {
        try{
            conn = Connect.connect();
            String sql = "DELETE FROM Cliente";
            Statement stm = conn.createStatement();
            stm.executeUpdate(sql);
        }catch (Exception e){
            throw new NullPointerException(e.getMessage());
        }finally {
            Connect.close(conn);
        }
    }

    @Override
    public Set<Integer> keySet() {throw new NullPointerException("Not implemented!"); }

    @Override
    public Collection<Cliente> values() {
        Collection<Cliente> col = new HashSet<Cliente>();
        try {
            conn = Connect.connect();
            String sql = "SELECT * FROM Cliente";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Cliente c = new Cliente(rs.getInt("idCliente"),
                        rs.getString("email"),
                        rs.getString("nome"),
                        rs.getString("telemovel"));
                col.add(c);
            }

        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally {
            Connect.close(conn);
        }

       col = col.stream().sorted(Comparator.comparingInt(Cliente::getId)).collect(Collectors.toList());

        return col;
    }
    @Override
    public Set<Entry<Integer, Cliente>> entrySet() {throw new NullPointerException("Not implemented!");}
}
