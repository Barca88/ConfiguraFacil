package configuraFacil.dataBase;

import configuraFacil.business.models.Cliente;
import configuraFacil.business.models.Configuracao;
import configuraFacil.business.models.items.Item;
import configuraFacil.business.models.users.Utilizador;
import configuraFacil.business.models.users.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class ConfiguracaoDao implements Map<Integer,Configuracao> {

    private Connection conn;

    @Override
    public int size() {
        int size = 0;
        try{
            conn = Connect.connect();
            String sql = "SELECT count(*) FROM Configuracao";
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
            String sql = "SELECT * FROM Configuracao";
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
            String sql = "SELECT 'idConfiguracao' FROM Configuracao WHERE idConfiguracao=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,(int)o);
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
        Configuracao c = (Configuracao) o;
        return containsKey(c.getId());
    }


    @Override
    public Configuracao get(Object o) {
        UtilizadorDao users = new UtilizadorDao();
        ItemDao itensDao = new ItemDao();
        Cliente cli = null;
        Utilizador u = null;
        Map<Integer, Item> itens = new HashMap<>();
        Configuracao c = null;
        try {
            conn = Connect.connect();
            String sql = "SELECT * FROM Configuracao WHERE idConfiguracao=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, (int) o);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){

                sql = "SELECT * FROM Configuracao_Item\n" +
                        "INNER JOIN Item ON Item_idItem = idItem\n" +
                        "WHERE Configuracao_idConfiguracao=?";
                PreparedStatement stm1 = conn.prepareStatement(sql);
                stm1.setInt(1,(int) o);
                ResultSet rs1 = stm1.executeQuery();
                while(rs1.next()){
                    Item i = itensDao.get(rs1.getInt("idItem"));
                    itens.put(i.getId(),i);
                }
                sql = "SELECT * FROM Configuracao\n" +
                        "INNER JOIN Utilizador ON Utilizador_idUtilizador = idUtilizador\n" +
                        "WHERE idConfiguracao=?";
                PreparedStatement stm2 = conn.prepareStatement(sql);
                stm2.setInt(1,(int) o);
                ResultSet rs2 = stm2.executeQuery();
                if(rs2.next()){
                    u = users.get(rs2.getInt("idConfiguracao"));
                }
                sql = "SELECT * FROM Configuracao\n" +
                        "INNER JOIN Cliente ON Cliente_idCliente = idCliente\n" +
                        "WHERE idConfiguracao=?";
                PreparedStatement stm3 = conn.prepareStatement(sql);
                stm2.setInt(1,(int) o);
                ResultSet rs3 = stm3.executeQuery();
                if(rs3.next()){
                    cli = new Cliente(rs3.getInt("idCliente"),rs3.getString("nome"),rs3.getString("email"),rs3.getString("telemovel"));
                }

                c = new Configuracao(rs.getInt("idConfiguracao=?"),rs.getString("modelo"),rs.getString("cor"),rs.getString("validade"),rs.getFloat("orcamento"),cli,u,itens);

            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            Connect.close(conn);
        }
        return c;
    }

    @Override
    public Configuracao put(Integer integer, Configuracao configuracao) {
        return null;
    }

    @Override
    public Configuracao remove(Object o) {
        return null;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Configuracao> map) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<Integer> keySet() {
        return null;
    }

    public Collection<Configuracao> values() {
        UtilizadorDao users = new UtilizadorDao();
        ItemDao itensDao = new ItemDao();
        Collection<Configuracao> configs = new HashSet<>();
        try {
            conn = Connect.connect();
            String sql = "SELECT * FROM Configuracao";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                int cId = rs.getInt("idConfiguracao");
                Cliente cli = null;
                Utilizador u = null;
                Configuracao c = null;
                Map<Integer, Item> itens = new HashMap<>();
                sql = "SELECT * FROM Configuracao_Item\n" +
                        "INNER JOIN Item ON Item_idItem = idItem\n" +
                        "WHERE Configuracao_idConfiguracao=?";
                PreparedStatement stm1 = conn.prepareStatement(sql);
                stm1.setInt(1,cId);
                ResultSet rs1 = stm1.executeQuery();
                while(rs1.next()){
                    Item i = itensDao.get(rs1.getInt("idItem"));
                    itens.put(i.getId(),i);
                }
                sql = "SELECT * FROM Configuracao\n" +
                        "INNER JOIN Utilizador ON Utilizador_idUtilizador = idUtilizador\n" +
                        "WHERE idConfiguracao=?";
                PreparedStatement stm2 = conn.prepareStatement(sql);
                stm2.setInt(1,cId);
                ResultSet rs2 = stm2.executeQuery();
                if(rs2.next()){
                    u = users.get(rs2.getString("email"));
                }
                sql = "SELECT * FROM Configuracao\n" +
                        "INNER JOIN Cliente ON Cliente_idCliente = idCliente\n" +
                        "WHERE idConfiguracao=?";
                PreparedStatement stm3 = conn.prepareStatement(sql);
                stm3.setInt(1,cId);
                ResultSet rs3 = stm3.executeQuery();
                if(rs3.next()){
                    cli = new Cliente(rs3.getInt("idCliente"),rs3.getString("nome"),rs3.getString("email"),rs3.getString("telemovel"));
                }

                c = new Configuracao(cId,rs.getString("modelo"),rs.getString("cor"),rs.getString("validade"),rs.getFloat("orcamento"),cli,u,itens);
                configs.add(c);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            Connect.close(conn);
        }
        return configs;
    }

    @Override
    public Set<Entry<Integer, Configuracao>> entrySet() {
        return null;
    }
}
