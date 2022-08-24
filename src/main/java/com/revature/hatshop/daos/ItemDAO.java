package com.revature.hatshop.daos;

import com.revature.hatshop.models.Item;
import com.revature.hatshop.models.User;
import com.revature.hatshop.utils.custom_exceptions.InvalidSQLException;
import com.revature.hatshop.utils.database.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO implements CrudDAO<Item> {
    @Override
    public void save(Item obj) throws IOException {

    }

    @Override
    public void update(Item obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Item getById(String id) {

        return null;
    }

    public String getNameById(String s){
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT name FROM item WHERE id = ?");
            ps.setString(1, s);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return rs.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Item> getAll() {
        return null;
    }

    public String getPrice(String itemId) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT price FROM item WHERE id = ?");
            ps.setString(1,itemId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
