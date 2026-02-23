package hackerrank;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Eftial Hossain
 */
public class CompareSubstring {

    public static String getSmallestAndLargest(String s, int k) {
        String smallest = "";
        String largest = "";
        java.util.List<String> al = new java.util.ArrayList<>();
        while (s.length() > k - 1) {
            al.add(s.substring(0, k));
//            System.out.println(s.substring(0, k));
            s = s.substring(1);
//            System.out.println(s);
        }
//        System.out.println(al);
        java.util.Collections.sort(al);
        for (int i = 0; i < al.size(); i++) {
            for (int j = al.size() - 1; j > i; j--) {
                int c = (al.get(i)).compareTo(al.get(j));
                if (c > 0) {
                    String tmp = al.get(i);
                    al.set(i, al.get(j));
                    al.set(j, tmp);
                }
            }
        }
//        System.out.println(al);
        smallest = al.get(0);
        largest = al.get(al.size() - 1);
        return smallest + "\n" + largest;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        int k = scan.nextInt();
        scan.close();

        System.out.println(getSmallestAndLargest(s, k));
    }

}
