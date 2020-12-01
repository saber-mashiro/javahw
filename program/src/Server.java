import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;

public class Server extends JFrame implements Runnable{
    private Socket s = null;
    private ServerSocket ss = null;
    //private JTextField jtf = new JTextField();
    private JTextArea jta = new JTextArea();

    private Random rnd = new Random();

    private ArrayList<ChatThread>clients = new ArrayList<ChatThread>();
    public Server() throws Exception{
        this.setTitle("��������");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(jta,BorderLayout.CENTER);
        jta.setBackground(Color.WHITE);
        this.setSize(400,200);
        this.setVisible(true);
        ss = new ServerSocket(8080);
        new Thread(this).start();
    }
    public void run(){
        try{
            while(true){
                s = ss.accept();
                ChatThread ct = new ChatThread(s);
                clients.add(ct);
                ct.start();
            }
        }catch (Exception ex){
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this,"��Ϸ�쳣�˳���");
            System.exit(0);

        }
    }


    class ChatThread extends Thread{
        private Socket s = null;
        private BufferedReader br = null;
        private PrintStream ps = null;
        private boolean canRun = true;
        public ChatThread(Socket s)throws Exception{
            this.s = s;
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            ps = new PrintStream(s.getOutputStream());
        }
        public void run(){
            try{
                //��ʼʱ����������������һ����С�����Ƶ�����������������еĿͻ���
                int wn = rnd.nextInt(2001);
                System.out.println("Start:"+wn);
                String swn = "START#" + Integer.toString(wn);
                sendMessage(swn);
                while (canRun){
                    //������
                    String str = br.readLine();
                    String[] strs = str.split("#");
                    if(strs[0].equals("LIFE")){
                        //������ֵת�������еĿͻ���
                       // sendMessage(strs[1]);
                        int rn = rnd.nextInt(2001);

                        System.out.println("RdNumber:"+rn);
                        System.out.println("���������ֵ");

                        String srn = "START#" + Integer.toString(rn);
                        sendMessage("LIFE#"+strs[1]+"#"+srn);
                    }else if(strs[0].equals("WIN")){
                        //��һ������ֵ�Ѿ���Ϊ0����һ��ʤ��
                        String msgWIN = "UWIN#";
                        sendMessage(msgWIN);
                    }else if(strs[0].equals("ASKRN")){
                        int rn1 = rnd.nextInt(2001);
                        String swn1 = "START#" + Integer.toString(rn1);
                        System.out.println("������ͬ��");
                        sendMessage(swn1);
                    }
                    //��strת�������еĿͻ���
                    //sendMessage(str);

                }
        }catch (Exception ex){
                canRun = false;
                clients.remove(this);
            }
        }
    }

    //����Ϣת�������еĿͻ���
    public void sendMessage(String msg){
        for(ChatThread ct:clients){
            ct.ps.println(msg);
        }
    }

    public static void main(String[] args)throws Exception{
        new Server();
    }

}
