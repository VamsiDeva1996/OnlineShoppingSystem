
package ui;
import dao.OrderDAO; import models.Order; import models.OrderItem; import models.User;
import javax.swing.*; import javax.swing.table.DefaultTableModel; import java.util.*;
public class OrderHistoryFrame extends JFrame {
    private User user; private JTable table;
    public OrderHistoryFrame(User u){ this.user=u; setTitle("Order History - "+user.getUsername()); setSize(800,500); setLayout(null);
        table=new JTable(); JScrollPane sp=new JScrollPane(table); sp.setBounds(20,20,740,400); add(sp);
        load(); setVisible(true);
    }
    private void load(){ List<Order> list = new OrderDAO().getOrdersByUser(user.getId()); String[] cols={"OrderId","Total","Date","Items"}; DefaultTableModel tm=new DefaultTableModel(cols,0);
        for(Order o: list){
            StringBuilder sb=new StringBuilder();
            if(o.getItems()!=null) for(Object oiObj: o.getItems()){ models.OrderItem oi = (models.OrderItem) oiObj; sb.append(oi.getProduct().getName()+" x"+oi.getQty()+"; "); }
            tm.addRow(new Object[]{o.getId(), o.getTotal(), o.getOrderDate(), sb.toString()});
        }
        table.setModel(tm);
    }
}
