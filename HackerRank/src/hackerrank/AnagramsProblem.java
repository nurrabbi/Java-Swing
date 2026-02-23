package hackerrank;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Eftial Hossain
 */
public class AnagramsProblem {

    static boolean isAnagram(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        int n1 = a.length();
        int n2 = b.length();

        if (n1 != n2) {
            return false;
        }

        char[] A = a.toCharArray();
        char[] B = b.toCharArray();

        Arrays.sort(A);
        Arrays.sort(B);

        // Compare sorted strings
        for (int i = 0; i < n1; i++) {
            if (A[i] != B[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String a = scan.next();
        String b = scan.next();
        scan.close();
        boolean ret = isAnagram(a, b);
        System.out.println((ret) ? "Anagrams" : "Not Anagrams");
    }
}
