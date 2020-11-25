import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080); // �������������׽��֣�ָ���÷���������Ķ˿ڡ�

        while (true) { // ��������£�����������Ҫһֱ���У��Ա���ʱ���ܽ��տͻ��˵����ӡ�

            Socket soc = null; // �ͻ����׽��ֶ��󣬳�ʼ��Ϊ�ա�

            soc = ss.accept(); // ���������յ��ͻ������ӣ����ظÿͻ��˶��󣬴˷�����һֱ������ֱ�����յ��ͻ��˵����ӡ�

            if (soc != null) { // ����ͻ��������ˣ�ִ��������롣

                DealWithEveryClient dec = new DealWithEveryClient(soc); // Ϊÿ���ͻ��˿���һ�����촰�ڡ�

                dec.start(); // �������̡߳�
            }
        }
    }

}
