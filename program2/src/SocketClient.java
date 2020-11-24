import java.io.*;
import java.net.*;
import java.util.*;

public class SocketClient {
    private Socket socket;

    public SocketClient() throws Exception {
        try {
            System.out.println("正在连接服务器");
            socket = new Socket("localhost", 8080);
            System.out.println("已连接服务器");
        } catch (Exception e) {
            throw e;
        }
    }

    public void start() {
        try {
            Scanner scan = new Scanner(System.in);
            OutputStream out = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
            PrintWriter pw = new PrintWriter(osw, true);
            ServerHandle handle = new ServerHandler();
            Thread t = new Thread(handle);
            t.start();
            String message = null;
            while (true) {
                message = scan.nextLine();
                pw.println(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class ServerHandle implements Runnable {
        public void run()
        {
            try{
            InputStream in = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(in,"utf-8");
            BufferedReader br = new BufferedReader(isr);
            String message = null;
            while((message = br.readLine())!= null)
            {
                System.out.println(message);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        }
    }

    public static void main(String[] args) {
        try {
            SocketClient client = new SocketClient();
            client.start();


        } catch (Exception e) {
            e.printStackTrace();
    }
    }
}

