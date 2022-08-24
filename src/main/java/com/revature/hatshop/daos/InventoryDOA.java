package com.revature.hatshop.daos;

import com.revature.hatshop.models.Inventory;
import com.revature.hatshop.utils.database.ConnectionFactory;

import javax.naming.ldap.PagedResultsResponseControl;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryDOA implements CrudDAO<Inventory>{
    @Override
    public void save(Inventory obj) throws IOException {

    }

    @Override
    public void update(Inventory obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Inventory getById(String id) {
        return null;
    }

    @Override
    public List<Inventory> getAll() {
        return null;
    }

    public List<Inventory> getStoreInventory(String id){
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            System.out.println("Pulling inventory of store"+id);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM inventory WHERE location = ?");
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            List<Inventory> inventoryList = new ArrayList<>();
            while(rs.next()){
                inventoryList.add(new Inventory(rs.getString("itemid"), rs.getString("location"), rs.getString("qty")));
            }
            return inventoryList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        return null;
    }

    public String getInvQuantity(String itemid, String location){
        try (Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT qty FROM inventory WHERE itemid = ? and location = ?");
            ps.setString(1, itemid);
            ps.setString(2, location);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return rs.getString("qty");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
