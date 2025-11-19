
package ui;
import dao.UserDAO; import dao.AdminDAO; import models.User; import models.Admin;
import javax.swing.*; import java.awt.*;
public class LoginFrame extends JFrame{
    JTextField usernameField; JPasswordField passwordField;
    public LoginFrame(){
        setTitle("Login"); setSize(420,300); setLayout(null);
        JLabel l=new JLabel("Online Shopping - Login"); l.setBounds(120,10,200,25); add(l);
        JLabel l1=new JLabel("Username:"); l1.setBounds(40,50,100,30); add(l1);
        usernameField=new JTextField(); usernameField.setBounds(150,50,200,30); add(usernameField);
        JLabel l2=new JLabel("Password:"); l2.setBounds(40,100,100,30); add(l2);
        passwordField=new JPasswordField(); passwordField.setBounds(150,100,200,30); add(passwordField);
        JCheckBox adminCb = new JCheckBox("Login as Admin"); adminCb.setBounds(150,140,200,25); add(adminCb);
        JButton login=new JButton("Login"); login.setBounds(110,180,90,30); add(login);
        JButton register=new JButton("Register"); register.setBounds(220,180,90,30); add(register);
        login.addActionListener(e->{
            String u=usernameField.getText(); String p=new String(passwordField.getPassword());
            if(adminCb.isSelected()){
                AdminDAO adao=new AdminDAO(); models.Admin a=adao.login(u,p);
                if(a!=null){ JOptionPane.showMessageDialog(null,"Admin Login Success"); new AdminDashboard(); dispose(); }
                else JOptionPane.showMessageDialog(null,"Invalid admin credentials");
            } else {
                UserDAO dao=new UserDAO(); User user=dao.login(u,p);
                if(user!=null){ JOptionPane.showMessageDialog(null,"Login Success"); new DashboardFrame(user); dispose(); }
                else JOptionPane.showMessageDialog(null,"Invalid credentials"); 
            }
        });
        register.addActionListener(e-> new RegisterFrame().setVisible(true));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setVisible(true);
    }
}
