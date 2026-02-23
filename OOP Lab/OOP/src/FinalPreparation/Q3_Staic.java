/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalPreparation;

/**
 *
 * @author nurho
 */
public class Q3_Staic {

    public static int a = 10;

    public static void increment() {
        a++;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(i + ": " + a);
            Q3_Staic.increment();
        }
    }

}
