
import java.util.Scanner;
import java.io.*;

public class Rev {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        File f = new File("C:\\Users\\nurho\\Documents\\NetBeansProjects\\OOP.Assignment\\IOFile\\WordReverseInput.txt");
        Scanner input = new Scanner(f);
        String result = "";
        while (input.hasNextLine()) {
            String fjala = input.next();
            for (int i = fjala.length() - 1; i >= 0; i--) {
                result += fjala.charAt(i);
            }

        }
        input.close();
        System.out.print(result + " ");

        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        try {
            fw = new FileWriter("names.txt", true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);

            pw.println("Shane");
            pw.println("Root");
            pw.println("Ben");

            System.out.println("Data Successfully appended into file");
            pw.flush();

        } finally {
            try {
                pw.close();
                bw.close();
                fw.close();
            } catch (IOException io) {
                System.out.println(io);
            }

        }

    }
}
