
import java.util.Scanner;

public class pyramid {

    public void pyramid(int x){
        
        for (int i = 1; i<=x; i++){
            for (int s = x-i; s>=1; s--){
                System.out.print("  ");
            }
            for(int j = 1; j<= i; j++){
                System.out.print(j+" ");
            }
            for(int r = i-1; r>= 1; r--){
                System.out.print(r+" ");
            }
            System.out.print("\n");
        }
    }
    public static void main(String [] args){
       Scanner sc = new Scanner(System.in);
        System.out.println("Enter the length of Pyramid:");
        int x = sc.nextInt();
        pyramid p = new pyramid();
        p.pyramid(x);
        
    }
}
