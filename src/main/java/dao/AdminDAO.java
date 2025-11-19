
package dao;
import db.DBConnection; import models.Admin;
import java.sql.*;
public class AdminDAO {
    public Admin login(String u,String p){
        try (Connection c = DBConnection.getConnection()){
            PreparedStatement ps = c.prepareStatement("SELECT * FROM admin WHERE username=? AND password=?");
            ps.setString(1,u); ps.setString(2,p); ResultSet rs = ps.executeQuery();
            if(rs.next()) return new Admin(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
        }catch(Exception e){e.printStackTrace();}
        return null;
    }
}
