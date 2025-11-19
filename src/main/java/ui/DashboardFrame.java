
package ui;
import models.User;
import javax.swing.*;
public class DashboardFrame extends JFrame {
    private User user;
    public DashboardFrame(User user){ this.user=user; setTitle("Dashboard - "+user.getUsername()); setSize(480,360); setLayout(null);
        JButton viewProducts=new JButton("View Products"); viewProducts.setBounds(100,40,280,40); add(viewProducts);
        JButton viewCart=new JButton("My Cart"); viewCart.setBounds(100,100,280,40); add(viewCart);
        JButton orders=new JButton("My Orders"); orders.setBounds(100,160,280,40); add(orders);
        JButton profile=new JButton("Profile"); profile.setBounds(100,220,280,40); add(profile);
        viewProducts.addActionListener(e-> new ProductListFrame(user).setVisible(true));
        viewCart.addActionListener(e-> new CartFrame(user).setVisible(true));
        orders.addActionListener(e-> new OrderHistoryFrame(user).setVisible(true));
        profile.addActionListener(e-> new ProfileFrame(user).setVisible(true));
        setVisible(true); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
