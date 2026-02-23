

import java.util.Scanner;

public class Q1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type A String: ");
        String str1 = sc.next();
        String str2 = "";
        char ch;

        for (int i = 0; i < str1.length(); i++) {
            ch = str1.charAt(i);
            str2 = ch + str2;
        }
        System.out.println("Reversed String: " + str2);
    }
}
