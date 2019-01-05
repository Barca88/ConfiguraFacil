package configuraFacil.dataBase;


import configuraFacil.business.models.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;


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
                sql = "SELECT * FROM Incompatibilidade\n" +
                        "INNER JOIN Item ON idItem = Item_idItem1 WHERE Item_idItem2 = ?";
                PreparedStatement stm3 = conn.prepareStatement(sql);
                stm3.setInt(1,(int) o);
                ResultSet rs3 = stm3.executeQuery();
                while(rs3.next()){
                    incomp.add(rs3.getInt("idItem"));
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
        int newid = 0;
        Item i = null;
        List<Integer> inc;
        List<Integer> dep;

        try{
            conn = Connect.connect();
            String sql = "SELECT * FROM Item WHERE idItem=?";
            PreparedStatement stm = conn.prepareStatement(sql);

            stm.setInt(1,item.getId());

            ResultSet rs = stm.executeQuery();

            if(rs.next()){ // EXISTE ITEM -> UPDATE
                sql = "UPDATE Item SET stock=? WHERE idItem=?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1,(item.getStock()));
                stm.setInt(2,(item.getId()));
                stm.executeUpdate();

            }else {

                sql = "INSERT INTO Item (nome,preco,stock,tipo)\n" + "VALUES (?,?,?,?)\n";
                stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                stm.setString(1, item.getNome());
                stm.setFloat(2, item.getPreco());
                stm.setFloat(3, item.getStock());
                stm.setString(3, item.getTipo());
                stm.executeUpdate();

                ResultSet rs2 = stm.getGeneratedKeys();

                if (rs2.next()) {
                    newid = rs2.getInt(1);
                    item.setId(newid);
                }

                if (!(item.getIncomp().isEmpty())) {
                    inc = item.getIncomp();

                    for (Integer incomp : inc) {
                        sql = "INSERT INTO Incompatibilidade VALUES (?,?)";

                        stm = conn.prepareStatement(sql);
                        stm.setInt(incomp, newid);
                        stm.executeUpdate();
                    }
                }

                if (!(item.getDepend().isEmpty())) {
                    dep = item.getDepend();

                    for (Integer depend : dep) {
                        sql = "INSERT INTO Dependencia VALUES (?,?)";

                        stm = conn.prepareStatement(sql);
                        stm.setInt(newid, depend);
                        stm.executeUpdate();
                    }
                }
            }

            i = item;
        }catch (Exception e) {
            e.printStackTrace();

        }finally {
            Connect.close(conn);
        }

        return i;
    }

    @Override
    public Item remove(Object o) {
        Item i = this.get(o);
        try{
            conn = Connect.connect();
            String sql = "DELETE FROM Item WHERE idItem =?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,(int) o);
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
        try{
            conn = Connect.connect();
            String sql = "DELETE FROM Item";
            Statement stm = conn.createStatement();
            stm.executeUpdate(sql);
        }catch (Exception e){
            throw new NullPointerException(e.getMessage());
        }finally {
            Connect.close(conn);
        }

    }

    @Override
    public Set<Integer> keySet() {
        throw new NullPointerException("Not implemented!");
    }

    @Override
    public Collection<Item> values() {
        Collection<Item> col = new HashSet<>();

        try{
            conn = Connect.connect();
            String sql = "SELECT * FROM Item";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while(rs.next()){
                int id = rs.getInt("idItem");
                List<Integer> incomp = new ArrayList<>();
                List<Integer> depend = new ArrayList<>();

                sql = "SELECT * FROM Incompatibilidade\n" +
                        "INNER JOIN Item ON idItem = Item_idItem2 WHERE Item_idItem1 = ?";
                PreparedStatement stm1 = conn.prepareStatement(sql);
                stm1.setInt(1,id);
                ResultSet rs1 = stm1.executeQuery();

                while(rs1.next()){
                    incomp.add(rs1.getInt("idItem"));
                }

                sql = "SELECT * FROM Dependencia\n" +
                        "INNER JOIN Item ON idItem = Item_idItem2 WHERE Item_idItem1 = ?";
                PreparedStatement stm2 = conn.prepareStatement(sql);
                stm2.setInt(1,id);
                ResultSet rs2 = stm2.executeQuery();

                while (rs2.next()){
                    depend.add(rs2.getInt("idItem"));
                }
                sql = "SELECT * FROM Incompatibilidade\n" +
                        "INNER JOIN Item ON idItem = Item_idItem1 WHERE Item_idItem2 = ?";
                PreparedStatement stm3 = conn.prepareStatement(sql);
                stm3.setInt(1, id);
                ResultSet rs3 = stm3.executeQuery();
                while(rs3.next()){
                    incomp.add(rs3.getInt("idItem"));
                }

                Item i = new Item(id,rs.getString("nome"),rs.getFloat("preco"),rs.getInt("stock"),rs.getString("tipo"),incomp,depend);
                col.add(i);
            }

        }
        catch (Exception e){ throw new NullPointerException(e.getMessage());}
        finally {
            Connect.close(conn);
        }

        col = col.stream().sorted(Comparator.comparingInt(Item::getId)).collect(Collectors.toList());

        return col;
    }

    @Override
    public Set<Entry<Integer, Item>> entrySet() {
        throw new NullPointerException("Not implemented!");
    }


}



