import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ��������һ�������ͻ������죬�����������˼·��
 * 1������������ServerSocket����ָ���˿ڣ�������������������ʱ�������ÿ���״̬��while(true)����
 * 2������ServerSocket�ķ������ܿͻ��˵���������
 * 3������һ���߳��࣬ÿ�����������յ���һ���ͻ����������󲢳ɹ�����ʱ��Ϊ����ͻ��˴�һ�����촰�ڡ�
 * 4��ͨ����ȡ�Ŀͻ��˶��󣬻�ȡ��ͻ��˽�������ͨ�ŵ�����IO���� 5���ڴ���ͻ��˵��߳�run�����￪����һ���̣߳��������տͻ��˷�������Ϣ��
 * 6��ͨ���¼��������ư��ı����е���Ϣ������ֽ����飬ͨ�����������д�������У��ɿͻ��˶��롣 7�����ͻ��˶Ͽ�����ʱ���������Ͽ���ͻ��˵����ӡ�
 * 
 */
public class ServerFrame extends JFrame implements ActionListener, Runnable { // ���������촰�ڣ�ʵ�������ӿڣ���Ϊ�����¼����������߳������ࡣ

    Socket soc; // �ͻ��˶��󣬷��������յ��Ŀͻ��ˡ�

    JTextField jf; // �ı���

    JTextArea jta; // �ı�����

    //JButton jb; // ��ť��

    JScrollPane jsp; // ������塣

    InputStream in; // �����ֽ���������

    OutputStream out; // �����ֽ��������

    String compare;

    public ServerFrame(Socket soc) throws IOException { // ���췽����������ʼ�������Լ���һЩ���á�

        super("������"); // ���ó���JFrame�Ĺ��췽�����������ı��⡣

        this.soc = soc; // ���ݷ��������յ��Ŀͻ����׽��֡�

        in = soc.getInputStream(); // �����������������õ��ǿͻ��˵�����������������������ͻ��˽������ݴ����֪�������ĸ��ͻ��ˡ�

        out = soc.getOutputStream(); // ����������������õ��ǿͻ��˵��������

        jf = new JTextField(20); // ��ʼ�����ı�����������Ϊ20���ַ���

        jta = new JTextArea(20, 20); // ��ʼ���ı�����������Ϊ20�к�20�У�һ���ַ�����һ�У���

        //jb = new JButton("����"); // ��ʼ����ť��

        jsp = new JScrollPane(jta); // ��ʼ��������壬�����ı���������ڹ�������С�

        this.setLayout(new FlowLayout()); // ���ô��岼��Ϊ��ʽ���֣��˲���Ϊ��������һ����������һ�зŲ�����ת���ڶ��У���

       // this.add(jf); // ���ı���ӵ������С�

        //this.add(jb); // ����ť�ӵ������С�

        this.add(jsp); // �ѹ������ӵ������С�

        //jb.addActionListener(this); // Ϊ��ťע�ᶯ���¼����������������ťʱ���������¼�������Ϊ����ʵ���˶����¼��������ӿڣ����Ը�����������������

        //jf.addActionListener(this); // Ϊ�ı���ע�ᶯ���¼��������������»س����������¼���

        this.setBounds(300, 300, 400, 400); // ���ô���߽�ʹ�С��

        this.setVisible(true); // ���ô���ɼ�������Ĭ���ǲ��ɼ��ģ���

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���Ĭ�Ϲرղ�����

    }

    public void actionPerformed(ActionEvent e) { // ActionListener�ӿ���ķ���������ʵ�֣��������������ť�������ı����»س���Ķ����¼���

        //String jfText = jf.getText(); // ��ȡ�ı����е����ݡ�

        //if (jfText.length() > 0) { // ���ı��������ַ������ȴ�����ʱ���������Ϊ0����û�����壩ִ��������䡣

            byte[] by = compare.getBytes(); // ���ַ�����Ϊ�ֽ����顣

            try {
                out.write(by); // ���ֽ�����д������������У��ɿͻ��������ա�

                jta.append("score :" + compare + "\n"); // ������������Ϣ��ʾ���ı����ڡ�

            //    jf.setText(""); // ��������Ϣ������ı����Ա��´����룩��

            } catch (IOException ex) {
                Logger.getLogger(ServerFrame.class.getName()).log(Level.SEVERE, null, ex); // һ���쳣�����������
            }
        //}
    }

    public void run() { // Runnable�ӿ��еķ���������ʵ�֣����߳̿�����ִ�еĴ��롣

        
        while (true) { // ��ΪҪ���ϵؽ�����Ϣ�������߳�Ҫһֱ���У�������while(true)��

            byte[] by = new byte[1024]; // �������տͻ��˷�������Ϣ��
            try {
                int count = in.read(by); // ��������������ȡ���Կͻ��˵���Ϣ�����ض�ȡ����Ч�ֽڸ�����
                compare = new String(by,0,count);
                jta.setText("�ͻ���" + new String(by,0,count)+ "\n"); // ���ͻ��˷�������Ϣ��ʾ���ı����С�
                
            } catch (IOException ex) {
                Logger.getLogger(ServerFrame.class.getName()).log(Level.SEVERE, null, ex); // һ���쳣�����������
            }
        }
    }

}
