
package models;
public class Admin {
    private int id; private String username; private String password;
    public Admin() {} public Admin(int id,String u,String p){this.id=id;this.username=u;this.password=p;}
    public int getId(){return id;} public String getUsername(){return username;} public String getPassword(){return password;}
}
