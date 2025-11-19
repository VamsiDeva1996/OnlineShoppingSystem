
package models;
public class User {
    private int id; private String username; private String password; private String email; private String address;
    public User() {}
    public User(int id, String username, String password, String email, String address){
        this.id=id;this.username=username;this.password=password;this.email=email;this.address=address;
    }
    public int getId(){return id;} public void setId(int id){this.id=id;}
    public String getUsername(){return username;} public void setUsername(String u){this.username=u;}
    public String getPassword(){return password;} public void setPassword(String p){this.password=p;}
    public String getEmail(){return email;} public void setEmail(String e){this.email=e;}
    public String getAddress(){return address;} public void setAddress(String a){this.address=a;}
}
