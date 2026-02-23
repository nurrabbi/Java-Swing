
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ud
 */
public class FileExample {

    public static void main(String arg[]) {

        try {
            
            FileWriter fw = new FileWriter("D:\\abc.txt");
            Scanner sc= new Scanner(System.in);
            System.out.println("Enter Masg: ");
            String text=sc.nextLine();
            fw.append(text);
           // fw.write("\n Java IO Example");
            fw.close();

        } catch (Exception e) {

        }
        System.out.println("Successfully Saved ");
         try{
            FileReader fr = new FileReader("D:\\abc.txt");
            int i;
            while((i=fr.read())!=-1)
            {
               System.out.print((char)i);
            }
            fr.close();
        }catch(Exception e){
            
        }
    }

}
