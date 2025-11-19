
package ui;
import dao.CartDAO; import dao.OrderDAO; import models.CartItem;
import models.OrderItem;
import models.User;
import javax.swing.*; import javax.swing.table.DefaultTableModel; import java.util.*;
import java.io.FileWriter;
public class CartFrame extends JFrame {
    private User user; private JTable table; private CartDAO dao = new CartDAO();
    public CartFrame(User u){ this.user=u; setTitle("Cart - " + user.getUsername()); setSize(720,480); setLayout(null);
        table=new JTable(); JScrollPane sp=new JScrollPane(table); sp.setBounds(20,20,660,320); add(sp);
        JButton refresh=new JButton("Refresh"); refresh.setBounds(20,360,100,30); add(refresh); refresh.addActionListener(e->load());
        JButton remove=new JButton("Remove"); remove.setBounds(140,360,100,30); add(remove); remove.addActionListener(e-> removeSelected());
        JButton checkout=new JButton("Checkout"); checkout.setBounds(540,360,140,40); add(checkout); checkout.addActionListener(e-> checkout());
        load(); setVisible(true);
    }
    private void load(){ List<CartItem> list=dao.getCart(user.getId()); String[] cols={"CartId","ProductId","Name","Price","Qty","Subtotal"}; DefaultTableModel tm=new DefaultTableModel(cols,0);
        for(CartItem c: list) tm.addRow(new Object[]{c.getId(), c.getProduct().getId(), c.getProduct().getName(), c.getProduct().getPrice(), c.getQty(), c.getProduct().getPrice()*c.getQty()});
        table.setModel(tm);
    }
    private void removeSelected(){ int r=table.getSelectedRow(); if(r<0){ JOptionPane.showMessageDialog(this,"Select item"); return; } int cid=(int)table.getValueAt(r,0); if(dao.removeItem(cid)){ JOptionPane.showMessageDialog(this,"Removed"); load(); } else JOptionPane.showMessageDialog(this,"Failed"); }
    private void checkout(){
        // prepare order items from cart and call OrderDAO
        List<CartItem> list = dao.getCart(user.getId());
        if(list.isEmpty()){ JOptionPane.showMessageDialog(this,"Cart empty"); return; }
        List<models.OrderItem> items = new ArrayList<>();
        double total=0;
        for(CartItem c: list){ items.add(new OrderItem(0,0,c.getProduct(),c.getQty(),c.getProduct().getPrice())); total += c.getQty()*c.getProduct().getPrice(); }
        // show payment simulation
        String[] opts={"Pay by Card","Pay by UPI","Cash on Delivery"}; int sel = JOptionPane.showOptionDialog(this, "Choose payment method", "Payment", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opts, opts[0]);
        if(sel<0) return;
        // place order
        int orderId = new OrderDAO().placeOrder(user.getId(), total, items);
        if(orderId>0){
            dao.clearCart(user.getId());
            // generate simple invoice file
            try{
                String fname = "/mnt/data/invoice_"+orderId+".txt";
                FileWriter fw = new FileWriter(fname);
                fw.write("Invoice for Order #"+orderId+"\nUser: "+user.getUsername()+"\nTotal: "+total+"\n\nItems:\n");
                for(models.OrderItem oi: items) fw.write(oi.getProduct().getName()+" x"+oi.getQty()+" = " + (oi.getQty()*oi.getPrice()) + "\n");
                fw.close();
                JOptionPane.showMessageDialog(this, "Order placed! Invoice: " + fname);
            }catch(Exception ex){ ex.printStackTrace(); JOptionPane.showMessageDialog(this, "Order placed but failed to write invoice file"); }
            load();
        } else JOptionPane.showMessageDialog(this, "Failed to place order");
    }
    // small inner class for order item to avoid import issues
    static class OrderItem extends models.OrderItem {
        public OrderItem(int id,int orderId,models.Product product,int qty,double price){ super(); try{ java.lang.reflect.Field f1=models.OrderItem.class.getDeclaredField("id"); }catch(Exception e){} }
    }
}
