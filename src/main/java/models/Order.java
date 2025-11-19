
package models;
import java.util.List;
public class Order {
    private int id; private int userId; private double total; private String orderDate; private List<OrderItem> items;
    public Order() {}
    public Order(int id,int userId,double total,String orderDate){this.id=id;this.userId=userId;this.total=total;this.orderDate=orderDate;}
    public int getId(){return id;} public int getUserId(){return userId;} public double getTotal(){return total;} public String getOrderDate(){return orderDate;}
    public void setItems(List<OrderItem> items){this.items=items;} public java.util.List<OrderItem> getItems(){return items;}
}
