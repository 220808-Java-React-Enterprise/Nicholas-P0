package com.revature.hatshop.daos;

import com.revature.hatshop.models.Item;
import com.revature.hatshop.models.Order;
import com.revature.hatshop.models.OrderElement;
import com.revature.hatshop.utils.custom_exceptions.InvalidSQLException;
import com.revature.hatshop.utils.database.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderElementDAO implements CrudDAO<OrderElement> {

    @Override
    public void save(OrderElement obj) throws IOException {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO order_element (order_id, item, quantity) VALUES (?, ?, ?)");
            ps.setString(1, obj.getOrder_id());
            ps.setString(2, obj.getItem_id());
            ps.setString(3, obj.getQuantity());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(OrderElement obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public OrderElement getById(String id) {
        return null;
    }

    @Override
    public List<OrderElement> getAll() {
        return null;
    }

    public List<OrderElement> getAllByOrderId(Order order) {
        List<OrderElement> elems = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM order_element WHERE order_id=(?)");
            ps.setString(1, order.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderElement elem_n = new OrderElement(rs.getString("order_id"), rs.getString("item"), rs.getString("quantity"));
                elems.add(elem_n);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
        return elems;
    }

    public void saveItem(OrderElement oe) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO order_element (order_id, item, quantity) VALUES (?, ?, ?)");
            ps.setString(1, oe.getOrder_id());
            ps.setString(2, oe.getItem_id());
            ps.setString(3, oe.getQuantity());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }


//    public Object addElement(String id, String itemid, String quantity) {
//        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
//            PreparedStatement ps = con.prepareStatement("INSERT INTO order_element (order_id, item, quantity) VALUES (?, ?, ?)");
//            ps.setString(1, id);
//            ps.setString(2, itemid);
//            ps.setString(3, quantity);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
//        }
//    }
}
