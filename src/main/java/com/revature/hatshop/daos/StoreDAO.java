package com.revature.hatshop.daos;

import com.revature.hatshop.models.Store;
import com.revature.hatshop.utils.custom_exceptions.InvalidSQLException;
import com.revature.hatshop.utils.database.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreDAO implements CrudDAO<Store>{

    @Override
    public void save(Store obj) throws IOException {

    }

    @Override
    public void update(Store obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Store getById(String id) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM stores WHERE id = ?");
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                return new Store(rs.getString("id"),rs.getString("location") );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Did not get Store by ID");
        }
        return null;
    }

    @Override
    public List getAll() {
        List<Store> stores = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM stores");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Store sto = new Store(rs.getString("id"), rs.getString("location"));
                stores.add(sto);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return stores;
    }
//    public void getNameById(String id){
//        try (Connection con = ConnectionFactory.getInstance().getConnection()){
//            PreparedStatement ps = con.prepareStatement("")
//        }
//    }
}
