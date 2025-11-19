
package ui;
import dao.UserDAO; import models.User; import javax.swing.*;
public class ProfileFrame extends JFrame {
    private User user; private JTextField emailF,addressF; private JPasswordField passF; private UserDAO dao=new UserDAO();
    public ProfileFrame(User user){ this.user=user; setTitle("Profile - "+user.getUsername()); setSize(420,320); setLayout(null);
        JLabel p=new JLabel("Password:"); p.setBounds(30,40,80,25); add(p); passF=new JPasswordField(user.getPassword()); passF.setBounds(120,40,240,25); add(passF);
        JLabel f=new JLabel("Email:"); f.setBounds(30,90,80,25); add(f); emailF=new JTextField(user.getEmail()); emailF.setBounds(120,90,240,25); add(emailF);
        JLabel a=new JLabel("Address:"); a.setBounds(30,140,80,25); add(a); addressF=new JTextField(user.getAddress()); addressF.setBounds(120,140,240,25); add(addressF);
        JButton upd=new JButton("Update"); upd.setBounds(150,200,100,30); add(upd); upd.addActionListener(e-> update());
        setVisible(true);
    }
    private void update(){ user.setPassword(new String(passF.getPassword())); user.setEmail(emailF.getText()); user.setAddress(addressF.getText()); if(dao.updateProfile(user)) JOptionPane.showMessageDialog(this,"Updated"); else JOptionPane.showMessageDialog(this,"Failed"); }
}
