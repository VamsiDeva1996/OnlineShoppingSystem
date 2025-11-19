
package models;
public class OrderItem {
    private int id; private int orderId; private Product product; private int qty; private double price;
    public OrderItem() {}
    public OrderItem(int id,int orderId,Product product,int qty,double price){this.id=id;this.orderId=orderId;this.product=product;this.qty=qty;this.price=price;}
    public int getId(){return id;} public int getOrderId(){return orderId;} public Product getProduct(){return product;} public int getQty(){return qty;} public double getPrice(){return price;}
}
