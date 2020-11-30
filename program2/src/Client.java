import java.io.*;
public class Client {
    public static void main(String[] args) throws IOException {
        ClientFrame cf = new ClientFrame(); // ʵ�������ڶ���ͬʱ��Ҳ���߳��������
        Thread reciver = new Thread(cf); // ���߳�������󴴽�һ���̡߳�
        reciver.start(); // �����̣߳��������Է���������Ϣ��
    }

}