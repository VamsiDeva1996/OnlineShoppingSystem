
package ui;
import dao.UserDAO; import models.User;
import javax.swing.*;
public class RegisterFrame extends JFrame{
    JTextField usernameField,emailField,addressField; JPasswordField passwordField;
    public RegisterFrame(){ setTitle("Register"); setSize(420,360); setLayout(null);
        JLabel l=new JLabel("Create Account"); l.setBounds(140,10,200,25); add(l);
        JLabel u=new JLabel("Username:"); u.setBounds(40,50,100,25); add(u);
        usernameField=new JTextField(); usernameField.setBounds(150,50,200,25); add(usernameField);
        JLabel p=new JLabel("Password:"); p.setBounds(40,90,100,25); add(p);
        passwordField=new JPasswordField(); passwordField.setBounds(150,90,200,25); add(passwordField);
        JLabel e=new JLabel("Email:"); e.setBounds(40,130,100,25); add(e);
        emailField=new JTextField(); emailField.setBounds(150,130,200,25); add(emailField);
        JLabel a=new JLabel("Address:"); a.setBounds(40,170,100,25); add(a);
        addressField=new JTextField(); addressField.setBounds(150,170,200,25); add(addressField);
        JButton reg=new JButton("Register"); reg.setBounds(150,220,120,30); add(reg);
        reg.addActionListener(ev->{ User uo=new User(); uo.setUsername(usernameField.getText()); uo.setPassword(new String(passwordField.getPassword())); uo.setEmail(emailField.getText()); uo.setAddress(addressField.getText()); if(new UserDAO().register(uo)){ JOptionPane.showMessageDialog(null,"Registered"); dispose(); } else JOptionPane.showMessageDialog(null,"Failed"); });
    }
}
