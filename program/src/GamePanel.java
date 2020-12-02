import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Vector;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable, ActionListener, KeyListener {
    private int life = 20;
    private char keyChar;
    private JLabel lbMoveChar = new JLabel();
    private JLabel lbLife = new JLabel();
    // 选项ABCD
    private JLabel lb = new JLabel();

    private Socket s = null;
    private Timer timer = new Timer(300, this);

    private Random rnd = new Random();
    private BufferedReader br = null;
    private PrintStream ps = null;

    private String word = null;
    // private String Opt = null;
    private int il;
    String strSave = null;
    String keyStr = null;
    String[] vocabulary = new String[3000];
    private String first = null;
    private String second = null;
    int flag = 0;

    // int in = 0;
    // int ij = 0;
    // int ic = 0;

    // canRun用来在异常或退出游戏时退出死循环
    private boolean canRun = true;

    public GamePanel() {
        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);
        this.setSize(1200, 500);

        this.add(lbLife);
        lbLife.setFont(new Font("黑体", Font.BOLD, 20));
        lbLife.setBackground(Color.WHITE);
        lbLife.setForeground(Color.WHITE);
        lbLife.setBounds(0, 0, 500, 20);

        this.add(lb);

        lb.setForeground(Color.WHITE);

        lb.setBackground(Color.WHITE);

        lb.setFont(new Font("黑体", Font.BOLD, 15));

        lb.setBounds(0, 400, 300, 50);

        this.add(lbMoveChar);
        lbMoveChar.setFont(new Font("黑体", Font.BOLD, 20));
        lbMoveChar.setForeground(Color.WHITE);

        //lbLife.setText("当前生命值：" + life);
        this.init();
        this.addKeyListener(this);
        try {
            s = new Socket("127.0.0.1", 8080);

            // JOptionPane.showMessageDialog(this,"连接成功");
            InputStream is = s.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            OutputStream os = s.getOutputStream();
            ps = new PrintStream(os);
            new Thread(this).start();
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "游戏异常退出！");
            System.exit(0);

        }
        timer.start();

    }

    public void writeFile(String filename, String str) {

        try {
            FileOutputStream fos = new FileOutputStream(filename, true);
            byte[] b = str.getBytes();
            fos.write(b);
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void readLineFile(String filename, int il) {
        try {
            // FileReader file = null;
            // try {
            // file = new FileReader("word.txt");
            // } catch (FileNotFoundException e) {
            // e.printStackTrace();
            // }
            FileInputStream fi = new FileInputStream(filename);
            InputStreamReader isr = new InputStreamReader(fi, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            // br此处用来按行读取文档
            // in++;
            // System.out.println("readLineFile开始执行:"+in);
            // int count = 0;
            while (br.readLine() != null && il >= 0) {
                il--;
                if (il < 0) {
                    String str1 = br.readLine();
                    strSave = str1 + "\r\n";
                    String[] strs1 = str1.split("\\s+");
                    String answer;
                    answer = strs1[1];
                    // System.out.println(answer);
                    lb.setText(strs1[2]);
                    int tmp2 = position(answer.length());
                    int tmp3 = position(answer.length());
                    while (tmp2 == tmp3) {
                        tmp3 = position(answer.length());
                    }

                    char[] select = answer.toCharArray();
                    // System.out.println(select[0]);
                    // System.out.println(select[0]+select[1]);

                    char[] first1 = new char[1];
                    char[] second1 = new char[1];
                    // System.out.println(tmp2);
                    // System.out.println(tmp3);
                    if (tmp2 < tmp3) {
                        first1[0] = select[tmp2];
                        second1[0] = select[tmp3];
                    } else {
                        first1[0] = select[tmp3];
                        second1[0] = select[tmp2];
                    }
                    // System.out.println(select[tmp2]+" "+first1[0]+" "+second1[0]);
                    String character1 = new String(first1);
                    first = character1;
                    String character2 = new String(second1);
                    second = character2;
                    System.out.println(first + " " + second);
                    select[tmp2] = '_';
                    select[tmp3] = '_';
                    String strall = new String(select);
                    answer = strall;
                    // System.out.println(answer);
                    word = answer;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static int position(int k) {
        int num = (int) (Math.random() * k);
        return num;
    }

    public void init() {
        // System.out.println("init开始执行:"+ij);

        // ij++;
        lbLife.setText("当前生命值：" + life);
        // System.out.println("设置生命值");

        // String str = String.valueOf((char)('A'+rnd.nextInt(26)));
        // lbMoveChar.setText(str);
        // 不需要客户端产生随机数了，服务器产生随机数
        // int il = rnd.nextInt(10);
        // ps.println("RND#"+il);
        readLineFile("D:\\vscode\\javahw\\program\\src\\word.txt", il);
        // System.out.println("aaa:"+il);
        lb.setBounds(0, 400, 300, 50);

        lbMoveChar.setText(word);

        lbMoveChar.setBounds(500, 0, 200, 50);

        // System.out.println("init执行完成:"+ij);
    }

    // 读取服务器发来的消息，操作生命值
    public void run() {
        try {
            while (canRun) {
                String str = br.readLine();
                String[] strs = str.split("#");
                // 判断从服务器接收到的消息是初始化number（用作随机读词）
                if (strs[0].equals("START")) {
                    il = Integer.parseInt(strs[1]);
                    // 判断是减生命值的消息
                } else if (strs[0].equals("LIFE")) {
                    // 若消息是既包含LIFE又包含RND
                    int score = Integer.parseInt(strs[1]);

                    // 实现生命值的减少

                    life += score;
                    checkFail();
                    // System.out.println("life+=score;语句调用checkFail");
                    // 如果strs[]的格式为“LIFE#-1#START#srn"
                    if (strs[2].equals("START")) {
                        il = Integer.parseInt(strs[3]);

                    }
                } else if (strs[0].equals("UWIN")) {
                    // 你赢了并退出游戏
                    timer.stop();
                    javax.swing.JOptionPane.showMessageDialog(this, "游戏结束，你赢了！");
                    System.exit(0);

                }
                init();
                // checkFail();
                // System.out.println("执行完服务器来的任务后调用init");
            }
        } catch (Exception ex) {
            canRun = false;
            javax.swing.JOptionPane.showMessageDialog(this, "游戏异常退出！");
            System.exit(0);
        }
    }

    // 某用户如果生命值率先变为0分，则判输，游戏退出。
    public void checkFail() {
        // init();
        // System.out.println("开始执行checkFail："+ic);
        lbLife.setText("当前生命值：" + life) ;

        // ic++;

        // System.out.println("结束ccheckFail："+ic);
        if (life <= 0) {
            ps.println("WIN#");
            timer.stop();
            javax.swing.JOptionPane.showMessageDialog(this, "生命值耗尽，游戏失败！");
            System.exit(0);
        }
    }

    // 若词汇掉到用户界面底部，如果两个用户都还没做出选择，两个用户减1分
    public void actionPerformed(ActionEvent e) {
        if (lbMoveChar.getY() >= this.getHeight()) {
            writeFile("wrong.txt", strSave);
            //life--;
            //checkFail();
            // 随机数由服务器产生
            ps.println("LIFE#-1");
            // System.out.println("底部调用checkFail");
        }
        lbMoveChar.setLocation(lbMoveChar.getX(), lbMoveChar.getY() + 10);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyChar = e.getKeyChar();
        keyStr = String.valueOf(keyChar);
        System.out.println(keyStr);
        try {
            if (keyStr.equalsIgnoreCase(first) && flag == 0) {
                flag++;
                // System.out.println(flag);
            } else if (!keyStr.equalsIgnoreCase(first) && flag == 0) {
                writeFile("D:\\vscode\\javahw\\program\\src\\wrong.txt", strSave);
                life = life - 2;
                // 用于向服务器标识需要随机数，对方生命值加一
                // System.out.println("选cuowu了" + life) ;
                ps.println("LIFE#1");
                flag = 0;
                checkFail();
                // init();
            } else if (keyStr.equalsIgnoreCase(second) && flag == 1) {
                flag++;
                // System.out.println(flag);
                // 判断是否选对选项
                // if (flag == 2) {
                writeFile("D:\\vscode\\javahw\\program\\src\\right.txt", strSave);
                life = life + 2;
                //System.out.println("right " + life);
                checkFail();
                // ps.println("ASKRN#");
                ps.println("LIFE#-1");
                flag = 0;
                
                // init();
                // }
            } else if (!keyStr.equalsIgnoreCase(second) && flag == 1) {
                writeFile("D:\\vscode\\javahw\\program\\src\\wrong.txt", strSave);
                life = life - 2;
                // 用于向服务器标识需要随机数，对方生命值加一
                // System.out.println("选cuowu了" + life) ;
                ps.println("LIFE#1");
                flag = 0;
                checkFail();
                // init();
            }
            // 实现选项射向lbMoveChar
            // ProLocation();
            // (Timer.scheduleAtFixedRate(TimerTask task,long delay,long period)
            // 安排指定的任务在指定的延迟后开始进行重复的固定速率执行．
            /*
             * timer.stop(); Timer timer2 = new Timer(); timer2.scheduleAtFixedRate()
             */

            // init();
            // checkFail();

            // System.out.println("向服务器发消息后checkFail");

        } catch (Exception ex) {
            canRun = false;
            javax.swing.JOptionPane.showMessageDialog(this, "游戏异常退出！");
            System.exit(0);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        new GameFrame();
    }

}
