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

    public void updateOrder(Order obj){
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps =con.prepareStatement("UPDATE orders SET dt = ? , total = ? , state = ? where orders.id = (select MAX(orders.id) FROM orders where orders.userid = ?)");
            ps.setString(1,obj.getDt());
            ps.setString(2,obj.getState());
            ps.setString(3,obj.getUser_id());
        } catch (SQLException e) {
            throw new RuntimeException("update broken");
        }
    }

    public void createOrder(Order obj){
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO orders (id, userid, dt, total, state) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getUser_id());
            ps.setString(3, obj.getDt());
            ps.setString(4, obj.getTotal());
            ps.setString(5, obj.getState());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    public Order loadNewestCart(User user) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from orders where orders.id = (select MAX(orders.id) FROM orders where orders.userid = ?)");
            ps.setString(1,user.getId());
            ResultSet rs = ps.executeQuery();
            rs.next();
            if (rs.getString("state").equals("CART")){
                return new Order(rs.getString("id"), rs.getString("userid"),rs.getString("dt"),rs.getString("total"),rs.getString("state"));
            }else{
                return new Order(String.valueOf(rs.getInt("id")+1), rs.getString("userid"),rs.getString("dt"),rs.getString("total"),rs.getString("state"));
            }

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
