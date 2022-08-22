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
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM item WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Item(rs.getString("id"), rs.getString("name"), rs.getString("price"));
            }

        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
        return null;
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM item");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Item item_n = new Item(rs.getString("id"), rs.getString("name"), rs.getString("price"));
                items.add(item_n);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
        return items;
    }
}
