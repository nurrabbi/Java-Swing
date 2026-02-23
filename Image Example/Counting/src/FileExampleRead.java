
import java.io.FileReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ud
 */
public class FileExampleRead {
    public static void main(String arg[]){
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
