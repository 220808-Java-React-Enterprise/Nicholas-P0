package com.revature.hatshop.daos;

import com.revature.hatshop.models.Item;
import com.revature.hatshop.models.Order;
import com.revature.hatshop.models.OrderElement;
import com.revature.hatshop.models.User;
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

public class OrderDAO implements CrudDAO<Order> {

    @Override
    public void save(Order obj) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO orders (id, userid, dt, total, state, location) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getUserid());
            ps.setString(3, obj.getDt());
            ps.setString(4, obj.getTotal());
            ps.setString(5, obj.getState());
            ps.setString(6, obj.getLocation());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Order obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Order getById(String id) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("select * from orders where orders.id = (select MAX(orders.id) FROM orders where orders.userid = ?)");
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new Order(rs.getString("id"),rs.getString("userid"),rs.getString("dt"),rs.getString("total"),rs.getString("state"),rs.getString("location"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Order getByUsername(String id) {
        return null;
    }
    
    public Order getByStore(String id){
        return null;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    public void placeOrder(Order ord) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("UPDATE orders SET state = 'PLACED' WHERE orders.id = (select MAX(orders.id) FROM orders where orders.userid = ?) and userid = ?" );
            ps.setString(1,ord.getUserid());
            ps.setString(2,ord.getUserid());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (OrderElement n: ord.getOrderElements()){
            try(Connection con = ConnectionFactory.getInstance().getConnection()){
                PreparedStatement ps = con.prepareStatement("UPDATE inventory SET qty = ? WHERE itemid = ? and location = ? " );
//                System.out.println(String.valueOf(Integer.valueOf(new InventoryDOA().getInvQuantity(n.getItemId(), new UserDAO().getById(n.getUserId()).getLocation())) - Integer.valueOf(n.getQty()))
//                        +" --- " + n.getItemId() + " --- " + (new UserDAO().getById(n.getUserId())).getLocation());
                ps.setString(1, String.valueOf(Integer.valueOf(new InventoryDOA().getInvQuantity(n.getItemId(), new UserDAO().getById(n.getUserId()).getLocation())) - Integer.valueOf(n.getQty())));
                ps.setString(2,n.getItemId());
                ps.setString(3, (new UserDAO().getById(n.getUserId())).getLocation());

                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static List<Order> getPlaced(User user) {
        List<Order> orderList= new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("select * from orders where userid = ? and state = 'PLACED'");
            ps.setString(1, user.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                orderList.add(new Order(rs.getString("id"),rs.getString("userid"),rs.getString("dt"),rs.getString("total"),rs.getString("state"),rs.getString("location"),false));
            }
            return orderList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
