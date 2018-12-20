package configuraFacil.dataBase;

import com.sun.javafx.scene.control.skin.IntegerFieldSkin;
import configuraFacil.business.models.items.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;


public class ItemDao implements Map<Integer, Item> {

    private Connection conn;

    @Override
    public int size() {
        int size = 0;
        try{
            conn = Connect.connect();
            String sql = "SELECT count(*) FROM Item";
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
            String sql = "SELECT * FROM Item";
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
    public boolean containsKey(Object o) {
        boolean ret = false;
        try{
            conn = Connect.connect();
            String sql = "SELECT * FROM Item WHERE idItem = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,(int) o);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                ret = true;
            }

        }
        catch (Exception e){ throw new NullPointerException(e.getMessage());}
        finally {
            Connect.close(conn);
        }
        return ret;
    }

    @Override
    public boolean containsValue(Object o) {
        Item i = (Item) o;
        return containsKey(i.getId());
    }

    @Override
    public Item get(Object o) {
        Item i = null;
        List<Integer> incomp = new ArrayList<>();
        List<Integer> depend = new ArrayList<>();
        try{
            conn = Connect.connect();
            String sql = "SELECT * FROM Item WHERE idItem = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,(int) o);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                sql = "SELECT * FROM Incompatibilidade\n" +
                        "INNER JOIN Item ON idItem = Item_idItem2 WHERE Item_idItem1 = ?";
                PreparedStatement stm1 = conn.prepareStatement(sql);
                stm1.setInt(1,(int) o);
                ResultSet rs1 = stm1.executeQuery();
                while(rs1.next()){
                    incomp.add(rs1.getInt("idItem"));
                }
                sql = "SELECT * FROM Dependencia\n" +
                        "INNER JOIN Item ON idItem = Item_idItem2 WHERE Item_idItem1 = ?";
                PreparedStatement stm2 = conn.prepareStatement(sql);
                stm2.setInt(1,(int) o);
                ResultSet rs2 = stm2.executeQuery();
                while (rs2.next()){
                    depend.add(rs2.getInt("idItem"));
                }
                i = new Item(rs.getInt("idItem"),rs.getString("nome"),rs.getFloat("preco"),rs.getInt("stock"),rs.getString("tipo"),incomp,depend);
            }

        }
        catch (Exception e){ throw new NullPointerException(e.getMessage());}
        finally {
            Connect.close(conn);
        }
        return i;
    }

    @Override
    public Item put(Integer integer, Item item) {
        //TO DO
        return null;
    }

    @Override
    public Item remove(Object o) {
        Item i = this.get(o);
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
        return i;
    }


    @Override
    public void putAll(Map<? extends Integer, ? extends Item> map) {
        for(Item i : map.values()){
            put(i.getId(),i);
        }
    }

    @Override
    public void clear() {

    }

    @Override
    public Set<Integer> keySet() {
        return null;
    }

    @Override
    public Collection<Item> values() {
        return null;
    }

    @Override
    public Set<Entry<Integer, Item>> entrySet() {
        return null;
    }
}
