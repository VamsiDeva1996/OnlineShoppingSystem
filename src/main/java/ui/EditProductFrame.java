
package ui;
import dao.ProductDAO; import models.Product; import javax.swing.*;
public class EditProductFrame extends JFrame {
    private JTextField nameF,descF,priceF,qtyF,imageF; private ProductDAO dao=new ProductDAO(); private Product product;
    public EditProductFrame(Product p){ this.product=p; setTitle("Edit Product"); setSize(420,360); setLayout(null);
        JLabel n=new JLabel("Name:"); n.setBounds(30,30,80,25); add(n); nameF=new JTextField(p.getName()); nameF.setBounds(120,30,240,25); add(nameF);
        JLabel d=new JLabel("Description:"); d.setBounds(30,70,80,25); add(d); descF=new JTextField(p.getDescription()); descF.setBounds(120,70,240,25); add(descF);
        JLabel pr=new JLabel("Price:"); pr.setBounds(30,110,80,25); add(pr); priceF=new JTextField(String.valueOf(p.getPrice())); priceF.setBounds(120,110,240,25); add(priceF);
        JLabel q=new JLabel("Quantity:"); q.setBounds(30,150,80,25); add(q); qtyF=new JTextField(String.valueOf(p.getQuantity())); qtyF.setBounds(120,150,240,25); add(qtyF);
        JLabel i=new JLabel("Image Path:"); i.setBounds(30,190,80,25); add(i); imageF=new JTextField(p.getImagePath()); imageF.setBounds(120,190,240,25); add(imageF);
        JButton upd=new JButton("Update"); upd.setBounds(150,240,100,30); add(upd); upd.addActionListener(e-> update());
    }
    private void update(){ try{ product.setName(nameF.getText()); product.setDescription(descF.getText()); product.setPrice(Double.parseDouble(priceF.getText())); product.setQuantity(Integer.parseInt(qtyF.getText())); product.setImagePath(imageF.getText()); if(dao.updateProduct(product)){ JOptionPane.showMessageDialog(this,"Updated"); dispose(); } else JOptionPane.showMessageDialog(this,"Failed"); }catch(Exception ex){ JOptionPane.showMessageDialog(this,"Invalid input"); } }
}
