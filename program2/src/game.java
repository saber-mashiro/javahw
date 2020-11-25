import java.io.*;
import java.awt.*;
import java.lang.*;
import java.util.Scanner;
import java.util.Vector;

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
        // Vector v = new Vector<>();

        BufferedReader br = new BufferedReader(file);
        try {
            String line;
            int count = 0;
            String[] str = new String[3000];
            while ((line = br.readLine()) != null) {
                str[count] = line;
                count++;
            }
            int tmp = position(2100);
            System.out.println(str[tmp]);
            // v.resize(str[tmp].size());
            // System.out.println(v[0]);
            char[] v = str[tmp].toCharArray();
            int tmp2 = position2(str[tmp].length());
            // char c1 = v[tmp2];

            int tmp3 = position2(str[tmp].length());
            while (tmp3 == tmp2) {
                tmp3 = position2(str[tmp].length());
            }
            System.out.println(str[tmp + 1]);
            char c1, c2;
            if (tmp2 < tmp3) {
                c1 = v[tmp2];
                c2 = v[tmp3];
                System.out.println(tmp2 + "  " + tmp3);
            } else {
                c1 = v[tmp3];
                c2 = v[tmp2];
                System.out.println(tmp3 + "  " + tmp2);
            }
            // char c2 = v[tmp3];
            
            // System.out.println(tmp2 + " " + tmp3);
            Scanner scanner = new Scanner(System.in);
            char cha1 = scanner.next().charAt(0);
            char cha2 = scanner.next().charAt(0);
            if (cha1 == c1 && cha2 == c2) {
                System.out.println("right");
            }
        } catch (IOException e) {
            e.printStackTrace();
            // adjfkald akjdlf
        }
    }

    public static int position(int k) {
        int num = (int) (Math.random() * k);
        if (num % 2 == 0) {
            return num;
        } else {
            return num + 1;
        }

    }

    public static int position2(int k) {
        int num = (int) (Math.random() * k);
        return num;
    }

}
