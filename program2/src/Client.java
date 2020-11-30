import java.io.*;
public class Client {
    public static void main(String[] args) throws IOException {
        ClientFrame cf = new ClientFrame(); 
        
        Thread reciver = new Thread(cf); 
        reciver.start(); 
        cf.txtString();
    }

}