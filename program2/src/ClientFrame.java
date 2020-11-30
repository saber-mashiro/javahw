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

public class ClientFrame extends JFrame implements ActionListener,Runnable{//�ͻ������촰�ڣ�ʵ�������ӿڣ���Ϊ�����¼����������߳������ࡣ
    
    Socket soc; //�ͻ����׽���
    
    JTextField jf;  //�ı���
    
    JTextArea jta;  //�ı�����
    
    JButton jb; //��ť��
    
    JScrollPane jsp;    //��ť��
    
    InputStream in;    //������������ָ��Socket������ȡ��������������
    
    OutputStream out;   //�����������ָ��Socket������ȡ�������������
    
    byte[] byall = {0} ;

    String clientin;

    public ClientFrame() throws IOException{ //���췽����������ʼ�������Լ���һЩ���á�
        
        super("�ͻ���");       //���ó���JFrame�Ĺ��췽�����������ı��⡣
        
        soc= new Socket("127.0.0.1",8080);  //ʵ�����ͻ����׽��֣�ָ��Ҫ���ӵķ����������IP�Ͷ˿ڡ�
        
        in=soc.getInputStream();    //��ȡSocket����������
        
        out=soc.getOutputStream();  //��ȡSocket���������
        
        jf=new JTextField(20);      //��ʼ�����ı�����������Ϊ20���ַ���
        
        jta=new JTextArea(20,20);    //��ʼ���ı�����������Ϊ20�к�20�У�һ���ַ�����һ�У���
        
        jb=new JButton("����");      //��ʼ����ť��
        
        jsp=new JScrollPane(jta);   //��ʼ��������壬�����ı���������ڹ�������С�
        
        this.setLayout(new FlowLayout());  //���ô��岼��Ϊ��ʽ���֣��˲���Ϊ��������һ����������һ�зŲ�����ת���ڶ��У���
        
        this.add(jf);  //���ı���ӵ������С�
        
        this.add(jb);    //����ť�ӵ������С�
        
        this.add(jsp); //�ѹ������ӵ������С�

        jb.addActionListener(this); //Ϊ��ťע�ᶯ���¼����������������ťʱ���������¼�������Ϊ����ʵ���˶����¼��������ӿڣ����Ը�����������������
        
        jf.addActionListener(this);  //Ϊ�ı���ע�ᶯ���¼��������������»س����������¼���
        
        this.setBounds(300,300,400,400);    //���ô���߽�ʹ�С��
        
        this.setVisible(true);       //���ô���ɼ�������Ĭ���ǲ��ɼ��ģ���
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //���ô���Ĭ�Ϲرղ�����

        txtString();
        
    }

    public void txtString() {
        FileReader file = null;
        try {
            file = new FileReader("word.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(file);
        try {
            String line;
            int count = 0;
            String[] str = new String[3000];
            while ((line = br.readLine()) != null) {
                str[count] = line;
                count++;
            }
            int score = 20;
            zerofile("true.txt");
            zerofile("false.txt");
            while (score != 0) {
                int tmp = position(2100);
                char[] v = str[tmp].toCharArray();
                int tmp2 = position2(str[tmp].length());
                int tmp3 = position2(str[tmp].length());
                while (tmp3 == tmp2) {
                    tmp3 = position2(str[tmp].length());
                }
                char c1, c2;
                String result;
                result = str[tmp];
                if (tmp2 < tmp3) {
                    c1 = v[tmp2];
                    c2 = v[tmp3];
                    // System.out.println(tmp2 + " " + tmp3);
                } else {
                    c1 = v[tmp3];
                    c2 = v[tmp2];
                    // System.out.println(tmp3 + " " + tmp2);
                }
                v[tmp2] = '_';
                v[tmp3] = '_';

                String s = new String(v); 
                // for (int i = 0; i < str[tmp].length(); i++) {
                //     System.out.print(v[i]);
                // }
                jta.append(s+ "\n");
                //System.out.print("\n");
                jta.append(str[tmp + 1] + "\n");
                //System.out.println(str[tmp + 1]);
                // Scanner scanner = new Scanner(System.in);
                // char cha1 = scanner.next().charAt(0);
                // char cha2 = scanner.next().charAt(0);
                clientin = jf.getText();
                //System.out.println(clientin);
                char [] fit = clientin.toCharArray();
                
                jta.append(clientin);
                String getscore;
                if (fit[0] == c1 && fit[2] == c2) {
                    score++;
                    //System.out.println(score);
                    getscore = Integer.toString(score);
                    byte[] by= getscore.getBytes();
                    //System.out.println(by[0]);
                    byall = by.clone();
                    jta.append(getscore + "\n");
                    jta.append(result+"\n");
                    //System.out.println(result);
                    fileread(result, str[tmp + 1], "true.txt");
                } else {
                    score--;
                    getscore = Integer.toString(score);
                    byte[] by= getscore.getBytes();
                    byall = by.clone();
                    jta.append(getscore + "\n");
                    //System.out.println(score);
                    jta.append(result+"\n");
                    //System.out.println(result);// change into file
                    fileread(result, str[tmp + 1], "false.txt");
                }
                //jf.setText("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int position(int k) {
        int num = (int) (Math.random() * k);
        if (num % 2 == 0) {
            return num;
        } else {
            return num + 1;
        }

    }
    public static int position2(int k) {
        int num = (int) (Math.random() * k);
        return num;
    }
    public static void fileread(String needword, String cong, String file) {
        FileWriter fl;
        try {
            fl = new FileWriter(file, true);
            fl.write(needword);
            fl.write("  ");
            fl.write(cong);
            fl.write("\n");
            fl.flush();
            fl.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void zerofile(String file) {
        FileWriter fl;
        try {
            fl = new FileWriter(file);
            fl.write("");
            fl.flush();
            fl.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e){     //ActionListener�ӿ���ķ���������ʵ�֣��������������ť�������ı����»س���Ķ����¼���
        
        
        //String jfText = jf.getText();//��ȡ�ı����е����ݡ�
        
        //if(jfText.length()>0){ //���ı��������ַ������ȴ�����ʱ���������Ϊ0����û�����壩ִ��������䡣
            
        //    byte[] by= getscore;    //���ַ�����Ϊ�ֽ����顣
        clientin = jf.getText();
            try {
                out.write(byall);   //���ֽ�����д������������У��ɷ����������ա�
                //jta.append(clientin+"\n");   //���ͻ��˵���Ϣ��ʾ���ı����ڡ�
                jf.setText("");    //��������Ϣ������ı����Ա��´����룩��
                
            } catch (IOException ex) {
                Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);//һ���쳣�����������
                
            }
        }
    
    public void run(){ //Runnable�ӿ��еķ���������ʵ�֣����߳̿�����ִ�еĴ��롣
        String compare;
        while(true){        //Runnable�ӿ��еķ���������ʵ�֣����߳̿�����ִ�еĴ��롣
            
            byte[] b=new byte[1024];     //�������շ�������������Ϣ��
            
            try {
                int count=in.read(b);     //��������������ȡ���Է���������Ϣ�����ض�ȡ����Ч�ֽڸ�����
                compare = new String(b,0,count);
                jta.append(compare+"\n");
                // if (compare == 'false') {
                //     jta.append("you lose");
                // }
                // else{
                //     jta.append("you win");
                // }
                //jta.append("?????????"+new String(b,0,count)+"\n");   //����������������Ϣ��ʾ���ı����С�
            } catch (IOException ex) {
                Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);  //һ���쳣�����������
            }
        }
    }

}