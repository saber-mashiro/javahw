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
    // ѡ��ABCD
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

    // canRun�������쳣���˳���Ϸʱ�˳���ѭ��
    private boolean canRun = true;

    public GamePanel() {
        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);
        this.setSize(1200, 500);

        this.add(lbLife);
        lbLife.setFont(new Font("����", Font.BOLD, 20));
        lbLife.setBackground(Color.WHITE);
        lbLife.setForeground(Color.WHITE);
        lbLife.setBounds(0, 0, 500, 20);

        this.add(lb);

        lb.setForeground(Color.WHITE);

        lb.setBackground(Color.WHITE);

        lb.setFont(new Font("����", Font.BOLD, 15));

        lb.setBounds(0, 400, 300, 50);

        this.add(lbMoveChar);
        lbMoveChar.setFont(new Font("����", Font.BOLD, 20));
        lbMoveChar.setForeground(Color.WHITE);

        //lbLife.setText("��ǰ����ֵ��" + life);
        this.init();
        this.addKeyListener(this);
        try {
            s = new Socket("127.0.0.1", 8080);

            // JOptionPane.showMessageDialog(this,"���ӳɹ�");
            InputStream is = s.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            OutputStream os = s.getOutputStream();
            ps = new PrintStream(os);
            new Thread(this).start();
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "��Ϸ�쳣�˳���");
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
            // br�˴��������ж�ȡ�ĵ�
            // in++;
            // System.out.println("readLineFile��ʼִ��:"+in);
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
        // System.out.println("init��ʼִ��:"+ij);

        // ij++;
        lbLife.setText("��ǰ����ֵ��" + life);
        // System.out.println("��������ֵ");

        // String str = String.valueOf((char)('A'+rnd.nextInt(26)));
        // lbMoveChar.setText(str);
        // ����Ҫ�ͻ��˲���������ˣ����������������
        // int il = rnd.nextInt(10);
        // ps.println("RND#"+il);
        readLineFile("D:\\vscode\\javahw\\program\\src\\word.txt", il);
        // System.out.println("aaa:"+il);
        lb.setBounds(0, 400, 300, 50);

        lbMoveChar.setText(word);

        lbMoveChar.setBounds(500, 0, 200, 50);

        // System.out.println("initִ�����:"+ij);
    }

    // ��ȡ��������������Ϣ����������ֵ
    public void run() {
        try {
            while (canRun) {
                String str = br.readLine();
                String[] strs = str.split("#");
                // �жϴӷ��������յ�����Ϣ�ǳ�ʼ��number������������ʣ�
                if (strs[0].equals("START")) {
                    il = Integer.parseInt(strs[1]);
                    // �ж��Ǽ�����ֵ����Ϣ
                } else if (strs[0].equals("LIFE")) {
                    // ����Ϣ�ǼȰ���LIFE�ְ���RND
                    int score = Integer.parseInt(strs[1]);

                    // ʵ������ֵ�ļ���

                    life += score;
                    checkFail();
                    // System.out.println("life+=score;������checkFail");
                    // ���strs[]�ĸ�ʽΪ��LIFE#-1#START#srn"
                    if (strs[2].equals("START")) {
                        il = Integer.parseInt(strs[3]);

                    }
                } else if (strs[0].equals("UWIN")) {
                    // ��Ӯ�˲��˳���Ϸ
                    timer.stop();
                    javax.swing.JOptionPane.showMessageDialog(this, "��Ϸ��������Ӯ�ˣ�");
                    System.exit(0);

                }
                init();
                // checkFail();
                // System.out.println("ִ���������������������init");
            }
        } catch (Exception ex) {
            canRun = false;
            javax.swing.JOptionPane.showMessageDialog(this, "��Ϸ�쳣�˳���");
            System.exit(0);
        }
    }

    // ĳ�û��������ֵ���ȱ�Ϊ0�֣������䣬��Ϸ�˳���
    public void checkFail() {
        // init();
        // System.out.println("��ʼִ��checkFail��"+ic);
        lbLife.setText("��ǰ����ֵ��" + life) ;

        // ic++;

        // System.out.println("����ccheckFail��"+ic);
        if (life <= 0) {
            ps.println("WIN#");
            timer.stop();
            javax.swing.JOptionPane.showMessageDialog(this, "����ֵ�ľ�����Ϸʧ�ܣ�");
            System.exit(0);
        }
    }

    // ���ʻ�����û�����ײ�����������û�����û����ѡ�������û���1��
    public void actionPerformed(ActionEvent e) {
        if (lbMoveChar.getY() >= this.getHeight()) {
            writeFile("wrong.txt", strSave);
            //life--;
            //checkFail();
            // ������ɷ���������
            ps.println("LIFE#-1");
            // System.out.println("�ײ�����checkFail");
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
                // �������������ʶ��Ҫ��������Է�����ֵ��һ
                // System.out.println("ѡcuowu��" + life) ;
                ps.println("LIFE#1");
                flag = 0;
                checkFail();
                // init();
            } else if (keyStr.equalsIgnoreCase(second) && flag == 1) {
                flag++;
                // System.out.println(flag);
                // �ж��Ƿ�ѡ��ѡ��
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
                // �������������ʶ��Ҫ��������Է�����ֵ��һ
                // System.out.println("ѡcuowu��" + life) ;
                ps.println("LIFE#1");
                flag = 0;
                checkFail();
                // init();
            }
            // ʵ��ѡ������lbMoveChar
            // ProLocation();
            // (Timer.scheduleAtFixedRate(TimerTask task,long delay,long period)
            // ����ָ����������ָ�����ӳٺ�ʼ�����ظ��Ĺ̶�����ִ�У�
            /*
             * timer.stop(); Timer timer2 = new Timer(); timer2.scheduleAtFixedRate()
             */

            // init();
            // checkFail();

            // System.out.println("�����������Ϣ��checkFail");

        } catch (Exception ex) {
            canRun = false;
            javax.swing.JOptionPane.showMessageDialog(this, "��Ϸ�쳣�˳���");
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
