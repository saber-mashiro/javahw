import java.io.*;
public class Client {
    public static void main(String[] args) throws IOException {
        ClientFrame cf = new ClientFrame(); // 实例化窗口对象，同时其也是线程任务对象。
        Thread reciver = new Thread(cf); // 由线程任务对象创建一个线程。
        reciver.start(); // 开启线程，接收来自服务器的消息。
    }

}