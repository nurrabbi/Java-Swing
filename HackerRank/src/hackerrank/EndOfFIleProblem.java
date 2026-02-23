package hackerrank;


import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Eftial Hossain
 */
public class EndOfFIleProblem {
     public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        int count = 0;
       
        try {
            while (sc.hasNext() == true) {
                count++;
                System.out.println(count+" "+sc.nextLine());
            }
        } catch (Exception e) {
           
        }

    }

}
