
package ui;
import dao.ProductDAO; import dao.CartDAO; import models.Product; import models.User;
import javax.swing.*; import javax.swing.table.DefaultTableModel;
import java.awt.*; import java.util.List;
public class ProductListFrame extends JFrame {
    private JTable table; private JLabel imgLabel; private JTextField searchField; private JTextField minF,maxF; private ProductDAO dao=new ProductDAO(); private CartDAO cdao=new CartDAO(); private User user;
    public ProductListFrame(User user){
        this.user=user;
        setTitle("Products"); setSize(900,600); setLayout(null);
        searchField=new JTextField(); searchField.setBounds(20,20,300,30); add(searchField);
        minF=new JTextField("0"); minF.setBounds(330,20,80,30); add(minF);
        maxF=new JTextField("9999999"); maxF.setBounds(420,20,80,30); add(maxF);
        JButton sBtn=new JButton("Search"); sBtn.setBounds(510,20,100,30); add(sBtn);
        sBtn.addActionListener(e-> loadProducts());
        table=new JTable(); JScrollPane sp=new JScrollPane(table); sp.setBounds(20,60,600,480); add(sp);
        imgLabel=new JLabel(); imgLabel.setBounds(640,60,220,220); imgLabel.setBorder(BorderFactory.createEtchedBorder()); add(imgLabel);
        JButton addCart=new JButton("Add to Cart"); addCart.setBounds(640,300,220,40); add(addCart);
        addCart.addActionListener(e-> addSelectedToCart());
        table.getSelectionModel().addListSelectionListener(e-> showSelectedImage());
        loadProducts();
        setVisible(true);
    }
    private void loadProducts(){
        String q=searchField.getText()==null?"":searchField.getText();
        double min=0,max=9999999;
        try{ min=Double.parseDouble(minF.getText()); max=Double.parseDouble(maxF.getText()); }catch(Exception ex){}
        List<Product> list = dao.search(q,min,max);
        String[] cols={"ID","Name","Description","Price","Quantity","ImagePath"};
        DefaultTableModel tm=new DefaultTableModel(cols,0);
        for(Product p: list) tm.addRow(new Object[]{p.getId(),p.getName(),p.getDescription(),p.getPrice(),p.getQuantity(),p.getImagePath()});
        table.setModel(tm);
    }
    private void showSelectedImage(){
        int r=table.getSelectedRow(); if(r<0) return;
        String path=(String)table.getValueAt(r,5);
        if(path==null||path.trim().isEmpty()){ imgLabel.setIcon(null); imgLabel.setText("No Image"); return; }
        ImageIcon ic=new ImageIcon(path); Image im=ic.getImage().getScaledInstance(220,220,Image.SCALE_SMOOTH); imgLabel.setIcon(new ImageIcon(im)); imgLabel.setText(""); 
    }
    private void addSelectedToCart(){
        int r=table.getSelectedRow(); if(r<0){ JOptionPane.showMessageDialog(this,"Select product"); return; }
        int pid=(int)table.getValueAt(r,0);
        String qtys = JOptionPane.showInputDialog(this, "Quantity:", "1"); int qty=1; try{qty=Integer.parseInt(qtys);}catch(Exception ex){}
        if(new CartDAO().addToCart(user.getId(), pid, qty)) JOptionPane.showMessageDialog(this,"Added to cart"); else JOptionPane.showMessageDialog(this,"Failed to add");
    }
}
