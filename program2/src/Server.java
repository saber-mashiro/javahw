import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class Server {
    private static int localHostport = 500;
    public static void main(String[] args) throws IOException{
        ServerSocket s = new ServerSocket(localHostport);
        System.out.println("服务器开启");
        while(true)
        {
            Socket socket = s.accept();
            System.out.println(socket);
            GetMessage getMessage = new GetMessage();
            Thread thread = new Thread(getMessage);
            thread.start();
        }
    }
}

class GetMessage implements Runnable{
    private int remotePort = 5001;
    private String remoteAdress = "localhost";
    private InputStream inputStream;
    private OutputStream outputStream;
    private Socket socketGet;
    private Socket socketSendmessage;
    private boolean socketIsExits = false;
    private int sum = 0;

    private byte[] buffer;

    public GetMessage(Socket socket)
    {
        this.socketGet = socket;
        try{
            inputStream = socketGet.getInputStream();
            outputStream = socketGet.getOutputStream();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void run(){
        String str = "";
        int n = 0;
        while (true) {
            try{
                buffer = new byte[2048];
                n = inputStream.read(buffer);
                str = new String(buffer,0,n);
                System.out.print("客户端：" + str);
                sendMessage();
            }catch (IOException e){
                e.printStackTrace();
                break;
            }
            if(str.equals("q"))
            {
                break;
            }
        }
        try{
            if(socketGet != null)
            {
                socketGet.close();
            }
            if(inputStream != null)
            {
                inputStream.close();
            }
        }catch(Exception e){}
    }


public void sendMessage() throws IOException{
    if (socketIsExits) {
        try {
            String input = "======" + (sum++);
            System.out.println("服务器发送 socket :" + this.socketSendmessage);
            outputStream.write(input.getBytes());
            System.out.println("服务器 ： " + input);
            outputStream.flush();
        } catch (Exception e) {
            System.out.println("客户端不存在");
            checkSocket();
        }
        
    }
    else
    {
        checkSocket();
    }
}

private void checkSocket()
{
    try{
        socketSendmessage = new Socket(remoteAdress,remotePort);
        outputStream = socketSendmessage.getOutputStream();
        socketIsExits = true;
    }catch (Exception e){
        socketIsExits = false;
    }
}
}