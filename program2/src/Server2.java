import java.io.*;
import java.net.*;
import java.util.*;

public class Server2 {
    private ServerSocket server;
    private List<PrintWriter> allout;

    public Server2() throws Exception{
        try{
        server = new ServerSocket(8080);
        allout = new ArrayList<PrintWriter>();
        }catch(Exception e){
            throw e;
         }
    }

    public void start()
    {
        try {
            while (true) {
                Socket socket = server.accept();
                ClientHandler handle = new ClientHandler(socket);
                Thread t = new Thread(handle);
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class ClientHandler implements Runnable{
        private Socket socket;
        private String host;
        public ClientHandler(Socket socket)
        {
            this.socket = socket;
            InetAddress address = socket.getInetAddress();
            host = address.getHostAddress();
        }

        public void run(){
            PrintWriter pw =null;
            try {
                InputStream in = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(in,"utf-8");
                BufferedReader br = new BufferedReader(isr);
                OutputStream out = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(out,"utf-8");
                pw = new PrintWriter(osw,true);
                synchronized(allout)
                {
                    allout.add(pw);
                }
                String message = null;
                while ((message = br.readLine())!= null) {
                    Thread t= Thread.currentThread();
                    System.out.println("客户" + t.getName() + ":" + message);
                    sendMessage(host + ":" + message);
                    
                }
            } catch (Exception e) {
            }finally{
                synchronized(allout)
                {
                    allout.remove(pw);
                }
                sendMessage(host + "下线了");
                try {
                    socket.close();
                } catch (Exception e) {
                }
            }
        }
    }

    private void sendMessage(String message)
    {
        synchronized(allout)
        {
            for(PrintWriter o:allout)
            {
                o.println(message);
            }
        }
    }
      public static void main(String[] args) {
          try {
              Server2 server2 = new Server2();
              server2.start();
          } catch (Exception e) {
            e.printStackTrace();
          }
      }
}
