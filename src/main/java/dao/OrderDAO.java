
package dao;
import db.DBConnection; import models.Order; import models.OrderItem; import models.Product;
import java.sql.*; import java.util.*;
public class OrderDAO {
    public int placeOrder(int userId, double total, List<OrderItem> items){
        try (Connection c = DBConnection.getConnection()){
            c.setAutoCommit(false);
            PreparedStatement ps = c.prepareStatement("INSERT INTO orders(user_id,total) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,userId); ps.setDouble(2,total); ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int orderId = -1;
            if(rs.next()) orderId = rs.getInt(1);
            if(orderId==-1){ c.rollback(); c.setAutoCommit(true); return -1; }
            PreparedStatement it = c.prepareStatement("INSERT INTO order_items(order_id,product_id,qty,price) VALUES(?,?,?,?)");
            PreparedStatement upd = c.prepareStatement("UPDATE products SET quantity=quantity-? WHERE id=?");
            for(OrderItem oi: items){
                it.setInt(1, orderId); it.setInt(2, oi.getProduct().getId()); it.setInt(3, oi.getQty()); it.setDouble(4, oi.getPrice()); it.addBatch();
                upd.setInt(1, oi.getQty()); upd.setInt(2, oi.getProduct().getId()); upd.addBatch();
            }
            it.executeBatch(); upd.executeBatch();
            c.commit(); c.setAutoCommit(true);
            return orderId;
        }catch(Exception e){ e.printStackTrace(); try{ db.DBConnection.getConnection().rollback(); }catch(Exception ex){} }
        return -1;
    }
    public List<Order> getOrdersByUser(int userId){
        List<Order> list=new ArrayList<>();
        try (Connection c = DBConnection.getConnection()){
            PreparedStatement ps = c.prepareStatement("SELECT * FROM orders WHERE user_id=? ORDER BY order_date DESC"); ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Order o=new Order(rs.getInt("id"), rs.getInt("user_id"), rs.getDouble("total"), rs.getString("order_date"));
                // fetch items
                PreparedStatement its = c.prepareStatement("SELECT oi.*, p.name, p.description, p.price as pprice, p.image_path FROM order_items oi JOIN products p ON oi.product_id=p.id WHERE oi.order_id=?");
                its.setInt(1, o.getId());
                ResultSet r2 = its.executeQuery();
                List<OrderItem> items=new ArrayList<>();
                while(r2.next()){
                    Product p=new Product(r2.getInt("product_id"), r2.getString("name"), r2.getString("description"), r2.getDouble("price"), 0, r2.getString("image_path"));
                    items.add(new OrderItem(r2.getInt("id"), r2.getInt("order_id"), p, r2.getInt("qty"), r2.getDouble("price")));
                }
                o.setItems(items);
                list.add(o);
            }
        }catch(Exception e){e.printStackTrace();}
        return list;
    }
}
