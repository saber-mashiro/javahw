import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 *��������һ�������ͻ������죬�ͻ������˼·��
 * 1�������ͻ���Socket����ָ��Ҫ���ӵķ�����IP�Ͷ˿ںš�
 * 2���������Ӻ�ͨ��Socket�ķ�����ȡ����IO����
 * 3��ͨ���¼��������ư��ı����е���Ϣ������ֽ����飬ͨ�����������д�������У��ɷ��������롣
 * 4�����ȿ���һ���̣߳�ͨ���������������������Է���������Ϣ������ʾ�������ı�����
 * 5�������촰�ڹر�ʱ���Ͽ�������������ӡ�
 */

public class ClientFrame extends JFrame implements ActionListener,Runnable{ //�ͻ������촰�ڣ�ʵ�������ӿڣ���Ϊ�����¼����������߳������ࡣ
    
    Socket soc; //�ͻ����׽��֡�
    
    JTextField jf;  //�ı���
    
    JTextArea jta;  //�ı�����
    
    JButton jb; //��ť��
    
    JScrollPane jsp;    //������塣
    
    InputStream in;     //������������ָ��Socket������ȡ��������������
    
    OutputStream out;   //�����������ָ��Socket������ȡ�������������
    
    public ClientFrame() throws IOException{    //���췽����������ʼ�������Լ���һЩ���á�
        
        super("�ͻ��������");      //���ó���JFrame�Ĺ��췽�����������ı��⡣
        
        soc= new Socket("127.0.0.1",8080);  //ʵ�����ͻ����׽��֣�ָ��Ҫ���ӵķ����������IP�Ͷ˿ڡ�
        
        in=soc.getInputStream();    //��ȡSocket����������
        
        out=soc.getOutputStream();  //��ȡSocket���������
        
        jf=new JTextField(20);      //��ʼ�����ı�����������Ϊ20���ַ���
        
        jta=new JTextArea(20,20);   //��ʼ���ı�����������Ϊ20�к�20�У�һ���ַ�����һ�У���
        
        jb=new JButton("����");     //��ʼ����ť��
        
        jsp=new JScrollPane(jta);   //��ʼ��������壬�����ı���������ڹ�������С�
        
        this.setLayout(new FlowLayout());   //���ô��岼��Ϊ��ʽ���֣��˲���Ϊ��������һ����������һ�зŲ�����ת���ڶ��У���
        
        this.add(jf);   //���ı���ӵ������С�
        
        this.add(jb);   //����ť�ӵ������С�
        
        this.add(jsp);  //�ѹ������ӵ������С�
        
        jb.addActionListener(this); //Ϊ��ťע�ᶯ���¼����������������ťʱ���������¼�������Ϊ����ʵ���˶����¼��������ӿڣ����Ը�����������������
        
        jf.addActionListener(this); //Ϊ�ı���ע�ᶯ���¼��������������»س����������¼���
        
        this.setBounds(300,300,400,400);    //���ô���߽�ʹ�С��
        
        this.setVisible(true);      //���ô���ɼ�������Ĭ���ǲ��ɼ��ģ���
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //���ô���Ĭ�Ϲرղ�����
        
    }
    public void actionPerformed(ActionEvent e){     //ActionListener�ӿ���ķ���������ʵ�֣��������������ť�������ı����»س���Ķ����¼���
        
        String jfText=jf.getText(); //��ȡ�ı����е����ݡ�
        
        if(jfText.length()>0){  //���ı��������ַ������ȴ�����ʱ���������Ϊ0����û�����壩ִ��������䡣
            
            byte[] by=jfText.getBytes();    //���ַ�����Ϊ�ֽ����顣
            
            try {
                out.write(by);  //���ֽ�����д������������У��ɷ����������ա�
                
                jta.append("��˵��"+jfText+"\n");   //���ͻ��˵���Ϣ��ʾ���ı����ڡ�
                
                jf.setText("");     //��������Ϣ������ı����Ա��´����룩��
                
            } catch (IOException ex) {
                Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);//һ���쳣�����������
                
            }
        }
    }
    public void run(){  //Runnable�ӿ��еķ���������ʵ�֣����߳̿�����ִ�еĴ��롣
        
        while(true){        //��ΪҪ���ϵؽ�����Ϣ�������߳�Ҫһֱ���У�������while(true)��
            
            byte[] b=new byte[1024];    //�������շ�������������Ϣ��
            
            try {
                int count=in.read(b);   //��������������ȡ���Է���������Ϣ�����ض�ȡ����Ч�ֽڸ�����
                
                jta.append("������˵��"+new String(b,0,count)+"\n");    //����������������Ϣ��ʾ���ı����С�
                
            } catch (IOException ex) {
                Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);  //һ���쳣�����������
            }
        }
    }

}