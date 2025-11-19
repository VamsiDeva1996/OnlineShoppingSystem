
package dao;
import db.DBConnection; import models.CartItem; import models.Product; import dao.ProductDAO;
import java.sql.*; import java.util.*;
public class CartDAO {
    ProductDAO pdao = new ProductDAO();
    public List<CartItem> getCart(int userId){
        List<CartItem> list=new ArrayList<>();
        try (Connection c = DBConnection.getConnection()){
            PreparedStatement ps = c.prepareStatement("SELECT c.id as cid, c.product_id, c.qty, p.* FROM cart c JOIN products p ON c.product_id=p.id WHERE c.user_id=?");
            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Product p=new Product(rs.getInt("product_id"), rs.getString("name"), rs.getString("description"), rs.getDouble("price"), rs.getInt("quantity"), rs.getString("image_path"));
                list.add(new CartItem(rs.getInt("cid"), userId, p, rs.getInt("qty")));
            }
        }catch(Exception e){e.printStackTrace();}
        return list;
    }
    public boolean addToCart(int userId,int productId,int qty){
        try (Connection c = DBConnection.getConnection()){
            // if exists, update
            PreparedStatement ps = c.prepareStatement("SELECT id, qty FROM cart WHERE user_id=? AND product_id=?");
            ps.setInt(1,userId); ps.setInt(2,productId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                PreparedStatement up = c.prepareStatement("UPDATE cart SET qty=? WHERE id=?");
                up.setInt(1, rs.getInt("qty")+qty); up.setInt(2, rs.getInt("id")); return up.executeUpdate()>0;
            } else {
                PreparedStatement ins = c.prepareStatement("INSERT INTO cart(user_id,product_id,qty) VALUES(?,?,?)");
                ins.setInt(1,userId); ins.setInt(2,productId); ins.setInt(3,qty); return ins.executeUpdate()>0;
            }
        }catch(Exception e){e.printStackTrace();}
        return false;
    }
    public boolean updateQty(int cartId,int qty){
        try (Connection c = DBConnection.getConnection()){
            PreparedStatement ps = c.prepareStatement("UPDATE cart SET qty=? WHERE id=?"); ps.setInt(1,qty); ps.setInt(2,cartId);
            return ps.executeUpdate()>0;
        }catch(Exception e){e.printStackTrace();}
        return false;
    }
    public boolean removeItem(int cartId){
        try (Connection c = DBConnection.getConnection()){
            PreparedStatement ps = c.prepareStatement("DELETE FROM cart WHERE id=?"); ps.setInt(1,cartId);
            return ps.executeUpdate()>0;
        }catch(Exception e){e.printStackTrace();}
        return false;
    }
    public boolean clearCart(int userId){
        try (Connection c = DBConnection.getConnection()){
            PreparedStatement ps = c.prepareStatement("DELETE FROM cart WHERE user_id=?"); ps.setInt(1,userId); return ps.executeUpdate()>=0;
        }catch(Exception e){e.printStackTrace();}
        return false;
    }
}
