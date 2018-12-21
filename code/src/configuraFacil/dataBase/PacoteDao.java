package configuraFacil.dataBase;

import configuraFacil.business.models.Pacote;
import configuraFacil.business.models.items.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class PacoteDao implements Map<Integer, Pacote> {

    private Connection conn;

    @Override
    public int size() {
        int size = 0;

        try{
            conn = Connect.connect();
            String sql = "SELECT count(*) FROM Pacote";
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
            String sql = "SELECT * FROM Pacote";
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
    public boolean containsKey(Object key){
        boolean ret = false;
        try {
            conn = Connect.connect();
            String sql = "SELECT 'nome' FROM 'Pacote' WHERE nome=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,(String)key);
            ResultSet rs = stm.executeQuery();
            ret = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Connect.close(conn);
        }
        return ret;
    }

    @Override
    public boolean containsValue(Object o) {
        Pacote i = (Pacote) o;
        return containsKey(i.getId());
    }

    @Override
    public Pacote get(Object o) {
        Map<Integer,Item> items = new HashMap<>();
        Pacote p = null;
        ItemDao itemDao = new ItemDao();

        try {
            conn = Connect.connect();
            String sql = "SELECT * FROM Pacote WHERE idPacote = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, (int) o);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                sql = "SELECT * FROM Pacote_Item\n" +
                        "INNER JOIN Item ON Item_idItem = idItem WHERE Pacote_idPacote = ?";
                PreparedStatement stm1 = conn.prepareStatement(sql);
                stm1.setInt(1, (int) o);
                ResultSet rs1 = stm1.executeQuery();

                while (rs1.next()) {
                    Item i = itemDao.get(rs1.getInt("idItem"));
                    items.put(i.getId(), i);
                }
            }

            p = new Pacote(rs.getInt("idPacote"),rs.getFloat("desconto"),rs.getString("nome"),items);
        }
        catch (Exception e){ throw new NullPointerException(e.getMessage());}
        finally {
            Connect.close(conn);
        }
        return p;
    }

    @Override
    public Pacote put(Integer integer, Pacote pacote) {
        return null;
    }

    @Override
    public Pacote remove(Object o) {
        return null;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Pacote> map) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<Integer> keySet() {
        return null;
    }

    @Override
    public Collection<Pacote> values() { //Todo Shaman
        Collection<Pacote> ret = new HashSet<>();
        Pacote p = null;
        ItemDao itemDao = new ItemDao();
        try {
            conn = Connect.connect();
            String sql = "SELECT * FROM Pacote";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                int pId = rs.getInt("idPacote");
                sql = "SELECT * FROM Pacote_Item\n" +
                        "INNER JOIN Item ON Item_idItem = idItem WHERE Pacote_idPacote = ?";
                PreparedStatement stm1 = conn.prepareStatement(sql);
                stm1.setInt(1,pId);
                ResultSet rs1 = stm1.executeQuery();
                Map<Integer,Item> itens = new HashMap<>();
                while(rs1.next()){
                    Item i = itemDao.get(rs1.getInt("idItem"));
                    itens.put(i.getId(),i);
                }
                p = new Pacote(pId,rs.getFloat("desconto"),rs.getString("nome"),itens);
                ret.add(p);


            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Connect.close(conn);
        }

        ret = ret.stream().sorted(Comparator.comparingInt(Pacote::getId)).collect(Collectors.toList());

        return ret;
    }

    @Override
    public Set<Entry<Integer, Pacote>> entrySet() {
        return null;
    }

}
