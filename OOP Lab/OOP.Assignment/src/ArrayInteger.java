
import java.util.Arrays;
import java.util.Scanner;

public class ArrayInteger {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter: ");
        int n = sc.nextInt();

        int[] arr = new int[3];
        for (int i = 2; i >= 0; i--) {
            int temp = 0;
            temp = n % 10;
            n /= 10;
            arr[i] = temp;
            System.out.println(arr[i]);
        }
        System.out.println("new");
        Arrays.sort(arr);
        for (int element : arr) {
            System.out.println(element);
        }
    }
}
