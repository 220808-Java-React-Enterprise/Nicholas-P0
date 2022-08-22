package com.revature.hatshop.daos;

import com.revature.hatshop.models.Item;
import com.revature.hatshop.models.Order;
import com.revature.hatshop.models.User;
import com.revature.hatshop.utils.custom_exceptions.InvalidSQLException;
import com.revature.hatshop.utils.database.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDAO implements CrudDAO<Order> {


//    public static String newOrderID(User user) {
//        try(Connection con = ConnectionFactory.getInstance().getConnection()){
//            PreparedStatement ps = con.prepareStatement("SELECT * FROM order WHERE id=(SELECT max(id)) AND WHERE user_id=(?)");
//            ResultSet rs = ps.executeQuery();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return (user.getId() +
//    }

    @Override
    public void save(Order obj) throws IOException {

    }

    @Override
    public void update(Order obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Order getById(String id) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    public void createOrder(Order obj){
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, user_id, dt, total) VALUES (?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getUser_id());
            ps.setString(3, obj.getDt());
            ps.setString(4, obj.getTotal());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    public boolean isCurrentPlaced(User user) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT MAX() FROM orders WHERE user_id = ?");
            ps.setString(1,user.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.getString("state") == "CART"){
                return true;
            }else{return false;}

        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to reach the database. Failed trying to determine if current order has been placed.");
        }
//        return false;
    }

    public String getMax(User user) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT MAX() FROM orders WHERE user_id = ?");
            ps.setString(1,user.getId());
            ResultSet rs = ps.executeQuery();
            return rs.getString(1);


        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to reach the database. Could not retrieve the most recent order");
        }
    }
}
