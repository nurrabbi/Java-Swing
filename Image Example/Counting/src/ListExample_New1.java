import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ud
 */
public class ListExample {

    public static void main(String args[]) {

        List<String> al = new ArrayList<>();//creating arraylist    

        System.out.println("Enter Value");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        String name2 = sc.nextLine();

        al.add(name);//adding object in arraylist    
        al.add(name2);
 // al.add("Michael");    
        //al.add("James");    

        //traversing elements using Iterator  
        Iterator itr = al.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }

}
