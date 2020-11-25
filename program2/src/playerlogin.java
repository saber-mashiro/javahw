import java.awt.*;
import javax.swing.*;

public class playerlogin {
    public static void main(String[] args) throws Exception {
        playerlogin login  = new playerlogin();
        login.initUI();
    }
    public void initUI()
    {
        JFrame JF = new JFrame();
        JF.setTitle("英语六级学习系统");
        JF.setSize(400,250);
        JF.setDefaultCloseOperation(3);
        JF.setLocationRelativeTo(null);
        JF.setFont(new Font("宋体",Font.PLAIN,14));
        
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER,10,10);
        JF.setLayout(fl);

        JLabel labname = new JLabel("昵称：");
        labname.setFont(new Font("宋体",Font.PLAIN,14));
        JF.add(labname);

        JTextField text_name = new JTextField();
        Dimension dim1 =  new Dimension(300,30);
        text_name.setPreferredSize(dim1);
        JF.add(text_name);
        JButton button1 = new JButton();
        Dimension dim2 = new Dimension(100,30);
        button1.setText("登录");
        button1.setFont(new Font("宋体",Font.PLAIN,14));
        button1.setSize(dim2);
        JF.add(button1);
        JF.setVisible(true);
        playerloginlistener pll = new playerloginlistener(JF,text_name);
        button1.addActionListener(pll);
    }
}
