package com.revature.hatshop.daos;

import com.revature.hatshop.models.Item;
import com.revature.hatshop.models.Order;
import com.revature.hatshop.models.OrderElement;
import com.revature.hatshop.utils.custom_exceptions.InvalidSQLException;
import com.revature.hatshop.utils.database.ConnectionFactory;
import com.sun.org.apache.xpath.internal.operations.Or;

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

    }

    @Override
    public void update(OrderElement n) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("UPDATE \"orderElement\" SET orderid = ?, userid = ?, itemid = ?, qty = ?, price = ? WHERE \"orderElement\".orderid  = (select MAX(orders.id) FROM orders where orders.userid = ?)");
            ps.setString(1,n.getOrderId());
            ps.setString(2,n.getUserId());
            ps.setString(3,n.getItemId());
            ps.setString(4,n.getQty());
            ps.setString(5,n.getPrice());
            ps.setString(6,n.getOrderId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public List<OrderElement> getCart(String userid) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            List<OrderElement> orderElements = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("select * from \"orderElement\" where \"orderElement\".orderid  = (select MAX(orders.id) FROM orders where orders.userid = ?)");
            ps.setString(1,userid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                orderElements.add(new OrderElement(rs.getString("orderid"), rs.getString("userid"), rs.getString("itemid"),rs.getString("qty"),rs.getString("price")));
            }
            return orderElements;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        return null;
    }



    public boolean exists(OrderElement n) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("select exists (select * from \"orderElement\" where \"orderElement\".orderid  = (select MAX(orders.id) FROM orders where orders.userid = ? and itemid = ?))");
            ps.setString(1, n.getUserId());
            ps.setString(2,n.getItemId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return rs.getBoolean(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void insert(OrderElement n) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO \"orderElement\"  (orderid , userid , itemid , qty , price) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1,n.getOrderId());
            ps.setString(2,n.getUserId());
            ps.setString(3,n.getItemId());
            ps.setString(4,n.getQty());
            ps.setString(5,n.getPrice());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<OrderElement> getPlaced(String id, String userid) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            List<OrderElement> orderElements = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM \"orderElement\" WHERE orderid = ? and userid = ?");
            ps.setString(1, id);
            ps.setString(2, userid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                orderElements.add(new OrderElement(rs.getString("orderid"), rs.getString("userid"), rs.getString("itemid"),rs.getString("qty"),rs.getString("price")));
            }
            return orderElements;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        return null;
    }
}
