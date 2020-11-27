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
 * 服务器与一个或多个客户端聊天，服务器端设计思路：
 * 1、创建服务器ServerSocket对象（指定端口），服务器在正常运行时处于永久开启状态（while(true)）。
 * 2、调用ServerSocket的方法接受客户端的连接请求。
 * 3、创建一个线程类，每当服务器接收到了一个客户端连接请求并成功连接时，为这个客户端打开一个聊天窗口。
 * 4、通过获取的客户端对象，获取与客户端进行数据通信的网络IO流。 5、在处理客户端的线程run方法里开启另一种线程，用来接收客户端发来的信息。
 * 6、通过事件监听机制把文本框中的消息打包成字节数组，通过网络输出流写到网络中，由客户端读入。 7、当客户端断开连接时，服务器断开与客户端的连接。
 * 
 */
public class ServerFrame extends JFrame implements ActionListener, Runnable { // 服务器聊天窗口，实现两个接口，作为动作事件侦听器和线程任务类。

    Socket soc; // 客户端对象，服务器接收到的客户端。

    JTextField jf; // 文本框。

    JTextArea jta; // 文本区域。

    //JButton jb; // 按钮。

    JScrollPane jsp; // 滚动面板。

    InputStream in; // 网络字节输入流。

    OutputStream out; // 网络字节输出流。

    String compare;

    public ServerFrame(Socket soc) throws IOException { // 构造方法，用来初始化对象以及做一些设置。

        super("服务器"); // 调用超类JFrame的构造方法设置聊天框的标题。

        this.soc = soc; // 传递服务器接收到的客户端套接字。

        in = soc.getInputStream(); // 服务器的输入流，拿的是客户端的输入流。这样，服务器与客户端进行数据传输便知道是与哪个客户端。

        out = soc.getOutputStream(); // 服务器的输出流，拿的是客户端的输出流。

        jf = new JTextField(20); // 初始化化文本框，设置容量为20个字符。

        jta = new JTextArea(20, 20); // 初始化文本区域并设置其为20行和20列（一个字符代表一列）。

        //jb = new JButton("发送"); // 初始化按钮。

        jsp = new JScrollPane(jta); // 初始化滚动面板，并把文本区域放置在滚动面板中。

        this.setLayout(new FlowLayout()); // 设置窗体布局为流式布局（此布局为从左向右一次添加组件，一行放不下了转到第二行）。

       // this.add(jf); // 将文本框加到窗体中。

        //this.add(jb); // 将按钮加到窗体中。

        this.add(jsp); // 把滚动面板加到窗体中。

        //jb.addActionListener(this); // 为按钮注册动作事件侦听器（当点击按钮时触发动作事件），因为该类实现了动作事件侦听器接口，所以该类对象就是侦听器。

        //jf.addActionListener(this); // 为文本框注册动作事件侦听器，当按下回车触发动作事件。

        this.setBounds(300, 300, 400, 400); // 设置窗体边界和大小。

        this.setVisible(true); // 设置窗体可见（窗体默认是不可见的）。

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗体默认关闭操作。

    }

    public void actionPerformed(ActionEvent e) { // ActionListener接口里的方法，必须实现，用来处理当点击按钮或者在文本框按下回车后的动作事件。

        //String jfText = jf.getText(); // 获取文本框中的内容。

        //if (jfText.length() > 0) { // 当文本框里面字符串长度大于零时（如果长度为0，则没有意义）执行下面语句。

            byte[] by = compare.getBytes(); // 将字符串变为字节数组。

            try {
                out.write(by); // 将字节数组写入网络输出流中，由客户端来接收。

                jta.append("score :" + compare + "\n"); // 将服务器的消息显示在文本区内。

            //    jf.setText(""); // 发送完消息后，清空文本框（以便下次输入）。

            } catch (IOException ex) {
                Logger.getLogger(ServerFrame.class.getName()).log(Level.SEVERE, null, ex); // 一种异常处理，不必深究。
            }
        //}
    }

    public void run() { // Runnable接口中的方法（必须实现），线程开启后执行的代码。

        
        while (true) { // 因为要不断地接收消息，所以线程要一直运行，所以用while(true)。

            byte[] by = new byte[1024]; // 用来接收客户端发来的消息。
            try {
                int count = in.read(by); // 用网络输入流读取来自客户端的消息，返回读取的有效字节个数。
                compare = new String(by,0,count);
                jta.setText("客户端" + new String(by,0,count)+ "\n"); // 将客户端发来的消息显示在文本区中。
                
            } catch (IOException ex) {
                Logger.getLogger(ServerFrame.class.getName()).log(Level.SEVERE, null, ex); // 一种异常处理，不必深究。
            }
        }
    }

}
