import java.io.*;
import java.awt.*;
import java.lang.*;

public class game {
    public static void main(String[] args) {
        // readword();
        // }
        //

        // public static void readword(){
        // FileReader reader = null;//??????????
        // try{
        // reader = new FileReader("word.txt");//??????��?
        // char [] buffer = new char [2000];//???????��
        // StringBuffer builder = new StringBuffer();
        // int count;
        // while((count = reader.read(buffer))!= -1)
        // {
        // builder.append(buffer,0,count);//count???????????????stringbuffer.append?buffer???????????????
        // // System.out.println(buffer);
        // }
        // System.out.println(builder.toString()); //tostring ??????????????
        // }catch(IOException e){
        // e.printStackTrace();
        // }finally{
        // if (reader != null) {
        // try {
        // reader.close();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }
        // }
        // }
        FileReader file = null; 
        try {
            file = new FileReader("word.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        txtString(file);
    }

    public static void txtString(FileReader file) {
        BufferedReader br = new BufferedReader(file);
        try {
            String line;
            int count = 0;
            String[] str = new String[3000];
            while ((line = br.readLine()) != null) {
                str[count] = line;
                count++;
            }
            System.out.println(str[1]);
        } catch (IOException e) {
            e.printStackTrace();
            //adjfkald akjdlf
        }
    }
}
