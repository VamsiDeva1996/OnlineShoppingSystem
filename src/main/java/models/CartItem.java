
package models;
public class CartItem {
    private int id; private int userId; private Product product; private int qty;
    public CartItem() {}
    public CartItem(int id, int userId, Product product, int qty){this.id=id;this.userId=userId;this.product=product;this.qty=qty;}
    public int getId(){return id;} public int getUserId(){return userId;} public Product getProduct(){return product;} public int getQty(){return qty;}
    public void setQty(int q){this.qty=q;}
}
