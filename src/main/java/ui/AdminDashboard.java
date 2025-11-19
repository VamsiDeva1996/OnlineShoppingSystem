
package ui;
import dao.ProductDAO; import dao.OrderDAO; import models.Product; import models.Order;
import javax.swing.*; import javax.swing.table.DefaultTableModel; import java.util.*;
public class AdminDashboard extends JFrame {
    private JTable table; private ProductDAO dao=new ProductDAO();
    public AdminDashboard(){ setTitle("Admin Dashboard"); setSize(900,600); setLayout(null);
        JButton add=new JButton("Add Product"); add.setBounds(20,20,140,30); add(add);
        JButton edit=new JButton("Edit Product"); edit.setBounds(170,20,140,30); add(edit);
        JButton del=new JButton("Delete Product"); del.setBounds(320,20,160,30); add(del);
        table=new JTable(); JScrollPane sp=new JScrollPane(table); sp.setBounds(20,70,840,420); add(sp);
        add.addActionListener(e-> new AddProductFrame().setVisible(true));
        edit.addActionListener(e-> {
            int r=table.getSelectedRow(); if(r<0) { JOptionPane.showMessageDialog(this,"Select product"); return; }
            int id=(int)table.getValueAt(r,0); Product p=dao.findById(id); if(p!=null) new EditProductFrame(p).setVisible(true);
        });
        del.addActionListener(e-> { int r=table.getSelectedRow(); if(r<0){ JOptionPane.showMessageDialog(this,"Select product"); return;} int id=(int)table.getValueAt(r,0); if(dao.deleteProduct(id)){ JOptionPane.showMessageDialog(this,"Deleted"); load();} else JOptionPane.showMessageDialog(this,"Failed"); });
        load(); setVisible(true);
    }
    public void load(){ List<Product> list=dao.allProducts(); String[] cols={"ID","Name","Description","Price","Qty","ImagePath"}; DefaultTableModel tm=new DefaultTableModel(cols,0); for(Product p:list) tm.addRow(new Object[]{p.getId(),p.getName(),p.getDescription(),p.getPrice(),p.getQuantity(),p.getImagePath()}); table.setModel(tm); }
}
