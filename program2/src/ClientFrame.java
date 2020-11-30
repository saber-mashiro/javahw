import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 *服务器与一个或多个客户端聊天，客户端设计思路：
 * 1、创建客户端Socket对象，指定要连接的服务器IP和端口号。
 * 2、建立连接后，通过Socket的方法获取网络IO流。
 * 3、通过事件监听机制把文本框中的消息打包成字节数组，通过网络输出流写到网络中，由服务器读入。
 * 4、事先开启一个线程，通过网络输入流，接收来自服务器的消息，并显示在聊天文本区域。
 * 5、当聊天窗口关闭时，断开与服务器的连接。
 */

public class ClientFrame extends JFrame implements ActionListener,Runnable{//客户端聊天窗口，实现两个接口，作为动作事件侦听器和线程任务类。
    
    Socket soc; //客户端套接字
    
    JTextField jf;  //文本框。
    
    JTextArea jta;  //文本区域。
    
    JButton jb; //按钮。
    
    JScrollPane jsp;    //按钮。
    
    InputStream in;    //输入流，用来指向Socket方法获取的网络输入流。
    
    OutputStream out;   //输出流，用来指向Socket方法获取的网络输出流。
    
    byte[] byall = {0} ;

    String clientin;

    public ClientFrame() throws IOException{ //构造方法，用来初始化对象以及做一些设置。
        
        super("客户端");       //调用超类JFrame的构造方法设置聊天框的标题。
        
        soc= new Socket("127.0.0.1",8080);  //实例化客户端套接字，指定要连接的服务器程序的IP和端口。
        
        in=soc.getInputStream();    //获取Socket的输入流。
        
        out=soc.getOutputStream();  //获取Socket的输出流。
        
        jf=new JTextField(20);      //初始化化文本框，设置容量为20个字符。
        
        jta=new JTextArea(20,20);    //初始化文本区域并设置其为20行和20列（一个字符代表一列）。
        
        jb=new JButton("发送");      //初始化按钮。
        
        jsp=new JScrollPane(jta);   //初始化滚动面板，并把文本区域放置在滚动面板中。
        
        this.setLayout(new FlowLayout());  //设置窗体布局为流式布局（此布局为从左向右一次添加组件，一行放不下了转到第二行）。
        
        this.add(jf);  //将文本框加到窗体中。
        
        this.add(jb);    //将按钮加到窗体中。
        
        this.add(jsp); //把滚动面板加到窗体中。

        jb.addActionListener(this); //为按钮注册动作事件侦听器（当点击按钮时触发动作事件），因为该类实现了动作事件侦听器接口，所以该类对象就是侦听器。
        
        jf.addActionListener(this);  //为文本框注册动作事件侦听器，当按下回车触发动作事件。
        
        this.setBounds(300,300,400,400);    //设置窗体边界和大小。
        
        this.setVisible(true);       //设置窗体可见（窗体默认是不可见的）。
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置窗体默认关闭操作。

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

    public void actionPerformed(ActionEvent e){     //ActionListener接口里的方法，必须实现，用来处理当点击按钮或者在文本框按下回车后的动作事件。
        
        
        //String jfText = jf.getText();//获取文本框中的内容。
        
        //if(jfText.length()>0){ //当文本框里面字符串长度大于零时（如果长度为0，则没有意义）执行下面语句。
            
        //    byte[] by= getscore;    //将字符串变为字节数组。
        clientin = jf.getText();
            try {
                out.write(byall);   //将字节数组写入网络输出流中，由服务器来接收。
                //jta.append(clientin+"\n");   //将客户端的消息显示在文本区内。
                jf.setText("");    //发送完消息后，清空文本框（以便下次输入）。
                
            } catch (IOException ex) {
                Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);//一种异常处理，不必深究。
                
            }
        }
    
    public void run(){ //Runnable接口中的方法（必须实现），线程开启后执行的代码。
        String compare;
        while(true){        //Runnable接口中的方法（必须实现），线程开启后执行的代码。
            
            byte[] b=new byte[1024];     //用来接收服务器发来的消息。
            
            try {
                int count=in.read(b);     //用网络输入流读取来自服务器的消息，返回读取的有效字节个数。
                compare = new String(b,0,count);
                jta.append(compare+"\n");
                // if (compare == 'false') {
                //     jta.append("you lose");
                // }
                // else{
                //     jta.append("you win");
                // }
                //jta.append("?????????"+new String(b,0,count)+"\n");   //将服务器发来的消息显示在文本区中。
            } catch (IOException ex) {
                Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);  //一种异常处理，不必深究。
            }
        }
    }

}