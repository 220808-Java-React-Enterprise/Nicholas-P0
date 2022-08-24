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

    public List<OrderElement> getCart(String userid) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            List<OrderElement> orderElements = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("select * from \"orderElement\" where \"orderElement\".orderid  = (select MAX(orders.id) FROM orders where orders.userid = ?)");
            ps.setString(1,userid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                orderElements.add(new OrderElement(rs.getString("orderid"), rs.getString("userid"), rs.getString("itemid"),rs.getString("qty"),rs.getString("price")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }



}
