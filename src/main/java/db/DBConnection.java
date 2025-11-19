
package db;
import java.sql.*;
public class DBConnection {
    private static Connection con;
    public static Connection getConnection() throws SQLException {
        if (con == null || con.isClosed()) {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping","root","Vamsi9398@");
        }
        return con;
    }
}
