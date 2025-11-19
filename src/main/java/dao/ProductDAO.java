
package dao;
import db.DBConnection; import models.Product;
import java.sql.*; import java.util.*;
public class ProductDAO {
    public List<Product> allProducts() {
        List<Product> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM products");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getDouble("price"), rs.getInt("quantity"), rs.getString("image_path")));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    public Product findById(int id){
        try (Connection c = DBConnection.getConnection()){
            PreparedStatement ps = c.prepareStatement("SELECT * FROM products WHERE id=?"); ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return new Product(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getDouble("price"), rs.getInt("quantity"), rs.getString("image_path"));
        }catch(Exception e){e.printStackTrace();}
        return null;
    }
    public boolean addProduct(Product p){
        try (Connection c = DBConnection.getConnection()){
            PreparedStatement ps = c.prepareStatement("INSERT INTO products(name,description,price,quantity,image_path) VALUES(?,?,?,?,?)");
            ps.setString(1,p.getName()); ps.setString(2,p.getDescription()); ps.setDouble(3,p.getPrice()); ps.setInt(4,p.getQuantity()); ps.setString(5,p.getImagePath());
            return ps.executeUpdate()>0;
        }catch(Exception e){e.printStackTrace();}
        return false;
    }
    public boolean updateProduct(Product p){
        try (Connection c = DBConnection.getConnection()){
            PreparedStatement ps = c.prepareStatement("UPDATE products SET name=?,description=?,price=?,quantity=?,image_path=? WHERE id=?");
            ps.setString(1,p.getName()); ps.setString(2,p.getDescription()); ps.setDouble(3,p.getPrice()); ps.setInt(4,p.getQuantity()); ps.setString(5,p.getImagePath()); ps.setInt(6,p.getId());
            return ps.executeUpdate()>0;
        }catch(Exception e){e.printStackTrace();}
        return false;
    }
    public boolean deleteProduct(int id){
        try (Connection c = DBConnection.getConnection()){
            PreparedStatement ps = c.prepareStatement("DELETE FROM products WHERE id=?"); ps.setInt(1,id);
            return ps.executeUpdate()>0;
        }catch(Exception e){e.printStackTrace();}
        return false;
    }
    public List<Product> search(String q,double minPrice,double maxPrice){
        List<Product> list=new ArrayList<>();
        try (Connection c = DBConnection.getConnection()){
            String sql = "SELECT * FROM products WHERE name LIKE ? AND price BETWEEN ? AND ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, "%" + q + "%"); ps.setDouble(2, minPrice); ps.setDouble(3, maxPrice);
            ResultSet rs=ps.executeQuery();
            while(rs.next()) list.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getDouble("price"), rs.getInt("quantity"), rs.getString("image_path")));
        }catch(Exception e){e.printStackTrace();}
        return list;
    }
}
