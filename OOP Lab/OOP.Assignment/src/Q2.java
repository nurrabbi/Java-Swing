
import java.util.Scanner;

public class Q2 {

    static void reverse(String a[], int n) {
        String[] b = new String[n];
        int j = n;
        for (int i = 0; i < n; i++) {
            b[j - 1] = a[i];
            j = j - 1;
        }

        System.out.println("Reversed array is: \n");
        for (int k = 0; k < n; k++) {
            System.out.println(b[k]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = 10;
        String[] str = new String[x];

        for (int i = 0; i < x; i++) {
            System.out.print("Enter " + (i + 1) + ": ");
            str[i] = sc.nextLine();
        }

        reverse(str, str.length);
    }
}
