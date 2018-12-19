package configuraFacil.dataBase;

import configuraFacil.business.models.Pacote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class PacoteDao implements Map<Integer, Pacote> {

    private Connection conn;

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
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
        return false;
    }

    @Override
    public Pacote get(Object o) {
        return null;
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
        List<Pacote> ret = new ArrayList<>();
        Pacote p = new Pacote();
        try {
            conn = Connect.connect();
            String sql = "SELECT * FROM 'Pacote' sortby 'id'" + "inner join 'Pacote_has_Item' on 'Pacote.idPacote'='Pacote_has_Item.'\n" + "inerjoin 'Item'\n" + "where Pacote_idPacote=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setDesconto(rs.getFloat("desconto"));
                p.setNome(rs.getString("nome"));
                //Todo!
                ret.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Connect.close(conn);
        }
        return ret;
    }

    @Override
    public Set<Entry<Integer, Pacote>> entrySet() {
        return null;
    }

}
