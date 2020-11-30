import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{
    private GamePanel gp;
    public GameFrame(){
        this.setLayout(new BorderLayout());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setAlwaysOnTop(true);//���ô�������ԣ���������������ʾ
        this.setAlwaysOnTop(true);
        String nickName = JOptionPane.showInputDialog("�����ǳ�");
        this.setTitle(nickName);
        gp = new GamePanel();
        this.add(gp);

        gp.setFocusable(true);
        this.setSize(gp.getWidth(),gp.getHeight());
        this.setResizable(false);
        this.setVisible(true);

    }
}
