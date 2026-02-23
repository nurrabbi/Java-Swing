package OOP;

import java.util.Scanner;
import static javafx.application.Platform.exit;

public class OOP03_Calculation {

    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);
        int a, b, x = 0;
        char c = 0;
        System.out.print("-----Easy Calcutaor-----\n Enter First Integer:");
        a = sc.nextInt();
        System.out.print("\n Enter Second Integer: ");
        b = sc.nextInt();
        for (int i = 0; ; ) {
            System.out.println(" Enter A To Sum\n Enter B To Substract\n Enter c To Multiply\n Enter D To Divide.\n Enter Q to Exit");
            c = sc.next().charAt(0);
           
            if(c=='A'|| c == 'a'){
                x=1;
            }else if (c=='B'||c == 'b'){
                x=2;
            }else if (c=='C'|| c == 'c'){
                x = 3;
            }else if (c=='D'|| c == 'd'){
                x = 4;
            }
            else if ( c== 'Q'|| c == 'q'){
                x = 5;
            } else {
                System.out.println("Invalid Input...!!!");
            }
            switch(x){
                case  1 :
                        System.out.println("Summetion is:"+(a+b));
                        break;
                case 2:
                    int temp;
                    if(a>b){
                        temp = a-b;
                    }else{
                        temp = b-a;
                    }
                    System.out.println("Subtraction is:"+temp);
                    break;
                case 3:
                    System.out.println("Multiplication is:"+(a*b));
                    break;
                case 4:
                    int div;
                    if(a>b){
                        div =a/b;
                    }else{
                        div = b/a;
                    }
                    System.out.println("Diviton is:"+ (div));
                    break;
                case 5:
                    exit();
                default :
                    System.out.println("Error...!!");
            }
        }
    }
}
