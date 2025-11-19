
package models;
public class Product {
    private int id; private String name; private String description; private double price; private int quantity; private String imagePath;
    public Product() {}
    public Product(int id, String name, String description, double price, int quantity, String imagePath){
        this.id=id;this.name=name;this.description=description;this.price=price;this.quantity=quantity;this.imagePath=imagePath;
    }
    // getters & setters
    public int getId(){return id;} public void setId(int id){this.id=id;}
    public String getName(){return name;} public void setName(String name){this.name=name;}
    public String getDescription(){return description;} public void setDescription(String d){this.description=d;}
    public double getPrice(){return price;} public void setPrice(double p){this.price=p;}
    public int getQuantity(){return quantity;} public void setQuantity(int q){this.quantity=q;}
    public String getImagePath(){return imagePath;} public void setImagePath(String p){this.imagePath=p;}
}
