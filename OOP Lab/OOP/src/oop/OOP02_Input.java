package OOP;

import java.util.Scanner;

public class OOP02_Input {

    public static void main(String[] arg) {
        int X;
        char C;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter A Integer: ");
        X = sc.nextInt();
        System.out.println(" 5 Times of Given Integer is: " + X * 5);
        System.out.print("Enter A Char: ");
        C = sc.next().charAt(0);
        System.out.printf(" 5 Times of Given char value is:" + "%s", C);
    }
}
