
package ui;
import dao.ProductDAO; import models.Product; import javax.swing.*;
public class AddProductFrame extends JFrame {
    private JTextField nameF,descF,priceF,qtyF,imageF; private ProductDAO dao=new ProductDAO();
    public AddProductFrame(){ setTitle("Add Product"); setSize(420,360); setLayout(null);
        JLabel n=new JLabel("Name:"); n.setBounds(30,30,80,25); add(n); nameF=new JTextField(); nameF.setBounds(120,30,240,25); add(nameF);
        JLabel d=new JLabel("Description:"); d.setBounds(30,70,80,25); add(d); descF=new JTextField(); descF.setBounds(120,70,240,25); add(descF);
        JLabel p=new JLabel("Price:"); p.setBounds(30,110,80,25); add(p); priceF=new JTextField(); priceF.setBounds(120,110,240,25); add(priceF);
        JLabel q=new JLabel("Quantity:"); q.setBounds(30,150,80,25); add(q); qtyF=new JTextField(); qtyF.setBounds(120,150,240,25); add(qtyF);
        JLabel i=new JLabel("Image Path:"); i.setBounds(30,190,80,25); add(i); imageF=new JTextField(); imageF.setBounds(120,190,240,25); add(imageF);
        JButton add=new JButton("Add"); add.setBounds(150,240,100,30); add(add); add.addActionListener(e-> addProduct());
    }
    private void addProduct(){ try{ Product p=new Product(); p.setName(nameF.getText()); p.setDescription(descF.getText()); p.setPrice(Double.parseDouble(priceF.getText())); p.setQuantity(Integer.parseInt(qtyF.getText())); p.setImagePath(imageF.getText()); if(dao.addProduct(p)){ JOptionPane.showMessageDialog(this,"Added"); dispose(); } else JOptionPane.showMessageDialog(this,"Failed"); }catch(Exception ex){ JOptionPane.showMessageDialog(this,"Invalid input"); } }
}
