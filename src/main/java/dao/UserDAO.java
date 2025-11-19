
package dao;
import db.DBConnection; import models.User;
import java.sql.*;
public class UserDAO {
    public User login(String u, String p){
        try (Connection c = DBConnection.getConnection()){
            PreparedStatement ps = c.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
            ps.setString(1,u); ps.setString(2,p);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("address"));
        }catch(Exception e){e.printStackTrace();}
        return null;
    }
    public boolean register(User user){
        try (Connection c = DBConnection.getConnection()){
            PreparedStatement ps = c.prepareStatement("INSERT INTO users(username,password,email,address) VALUES(?,?,?,?)");
            ps.setString(1,user.getUsername()); ps.setString(2,user.getPassword()); ps.setString(3,user.getEmail()); ps.setString(4,user.getAddress());
            return ps.executeUpdate()>0;
        }catch(Exception e){e.printStackTrace();}
        return false;
    }
    public boolean updateProfile(User user){
        try (Connection c = DBConnection.getConnection()){
            PreparedStatement ps = c.prepareStatement("UPDATE users SET password=?, email=?, address=? WHERE id=?");
            ps.setString(1,user.getPassword()); ps.setString(2,user.getEmail()); ps.setString(3,user.getAddress()); ps.setInt(4,user.getId());
            return ps.executeUpdate()>0;
        }catch(Exception e){e.printStackTrace();}
        return false;
    }
}
