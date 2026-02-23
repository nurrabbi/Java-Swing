
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WordReverse {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String Str;
        String[] Str2 = new String[10];
        String word;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("C:\\Users\\nurho\\Documents\\NetBeansProjects\\OOP.Assignment\\IOFile\\WordReverseInput.txt"));
            Str = bf.readLine();
            while (Str != null) {

                System.out.println("Read :" + Str);
                for (int i = 0; i <= Str.length(); i++) {
                    char temp = Str.charAt(i);
//                    word = word + temp;
                    if (temp == ' ') {

                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        } finally {
            System.out.println("File reading Started");
        }

        FileWriter fw = null;
        try {
            fw = new FileWriter("C:\\Users\\nurho\\Documents\\NetBeansProjects\\OOP.Assignment\\IOFile\\WordReverseOutput.txt");
        } catch (IOException e) {
            System.out.println("File Writing Error...!!!" + e);
        } finally {
            System.out.println("file writing completed.!");
        }
    }
}
