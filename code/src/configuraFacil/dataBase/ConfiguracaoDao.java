package configuraFacil.dataBase;

import configuraFacil.business.models.Cliente;
import configuraFacil.business.models.Configuracao;
import configuraFacil.business.models.items.Item;
import configuraFacil.business.models.users.Utilizador;
import configuraFacil.business.models.users.Vendedor;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

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
    public Configuracao put(Integer integer, Configuracao configuracao){
        Configuracao config = null;
        Set<Integer> i_set = configuracao.getItens().keySet();
        Cliente cliente = configuracao.getCliente();
        int id_Cli = 0 ,id_Config = 0;
        int id_V = configuracao.getVendedor().getId();

        try {
            conn = Connect.connect();
            String sql = "SELECT * FROM Cliente WHERE email=?";
            PreparedStatement stm = conn.prepareStatement(sql);

            stm.setString(1,cliente.getEmail());

            ResultSet rs = stm.executeQuery();

            if(rs.next()){ // EXISTE CLIENTE -> METEMOS O ID CERTO
                id_Cli = rs.getInt(1);
                cliente.setId(id_Cli);

            }else{ //NÃO EXISTE CLIENTE -> INSERE NA BD
                sql = "INSERT INTO Cliente (nome,email,telemovel)\n" +
                        "VALUES (?,?,?)\n";

                stm = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                stm.setString(1,cliente.getNome());
                stm.setString(2,cliente.getEmail());
                stm.setString(3,cliente.getTelemovel());
                stm.executeUpdate();

                ResultSet rs1 = stm.getGeneratedKeys();


                if(rs1.next()){ // METEMOS O ID CERTO
                    id_Cli = rs1.getInt(1);
                    cliente.setId(id_Cli);
                }


            }

            // PROCEDEMOS A INSERIR A CONFIGURAÇÃO
            sql = "INSERT INTO Configuracao (validade,orcamento,Utilizador_idUtilizador,Cliente_idCliente)\n" +
                    "VALUES (?,?,?,?)\n";

            stm = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            stm.setString(1,configuracao.getEstado());
            stm.setFloat(2,configuracao.getOrcamento());
            stm.setInt(3,id_V);
            stm.setInt(4,id_Cli);
            stm.executeUpdate();

            ResultSet rs2 = stm.getGeneratedKeys();

            if(rs2.next()){ // METER ID CERTO
                id_Config = rs2.getInt(1);
                configuracao.setId(id_Config);
            }

            // PROCEDEMOS A INSERIR OS ITEMS NA TABELA CONFIGURAÇÃO_ITEM
            for(Integer i: i_set){
                sql = "INSERT INTO Configuracao_Item\n" +
                        "VALUES (?,?)";

                stm = conn.prepareStatement(sql);
                stm.setInt(1,id_Config);
                stm.setInt(2,i);
                stm.executeUpdate();
            }

            config = configuracao;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Connect.close(conn);
        }

        return config;

    }

    @Override
    public Configuracao remove(Object o) {
        //TODO - REMOVE
        return null;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Configuracao> map) {
        //TODO - PUT_ALL
    }

    @Override
    public void clear() {
        //TODO - CLEAR
    }

    @Override
    public Set<Integer> keySet() {
        throw new NullPointerException("Not implemented!");
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

                String cor = itens.values().stream().filter(i -> i.getTipo().equals("Cor")).map(i-> i.getNome()).collect(Collectors.joining());
                String modelo = itens.values().stream().filter(i -> i.getTipo().equals("Modelo")).map(i-> i.getNome()).collect(Collectors.joining());

                if(rs3.next()){
                    cli = new Cliente(rs3.getInt("idCliente"),rs3.getString("nome"),rs3.getString("email"),rs3.getString("telemovel"));
                }

                c = new Configuracao(cId,modelo,cor,rs.getString("validade"),rs.getFloat("orcamento"),cli,u,itens);
                configs.add(c);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            Connect.close(conn);
        }

        configs = configs.stream().sorted(Comparator.comparingInt(Configuracao::getId)).collect(Collectors.toList());

        return configs;
    }

    @Override
    public Set<Entry<Integer, Configuracao>> entrySet() {
        return null;
    }
}
