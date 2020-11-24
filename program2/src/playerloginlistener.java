import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class playerloginlistener implements ActionListener {
    private JTextField text_name;
    private JFrame login;

    public playerloginlistener(JFrame login,JTextField text_name) {
        this.login = login;
        this.text_name = text_name;
    }

    public void actionPerformed(ActionEvent e) {
        // Dimension dim2 = new Dimension(100, 30);
        Dimension dim3 = new Dimension(300, 30);

        JFrame login2 = new JFrame();
        login2.setSize(400, 200);
        login2.setDefaultCloseOperation(3);
        login2.setLocationRelativeTo(null);
        login2.setFont(new Font("宋体", Font.PLAIN, 14));

        JPanel jp1 = new JPanel();

        if (!text_name.getText().equals(null)) {
            JLabel message = new JLabel("登录成功");
            message.setFont(new Font("宋体", Font.PLAIN, 14));
            message.setPreferredSize(dim3);
            jp1.add(message);
            login2.add(jp1, BorderLayout.CENTER);
            login2.setResizable(false);
            login2.setVisible(true);
            login.dispose();
        }
    }

}
