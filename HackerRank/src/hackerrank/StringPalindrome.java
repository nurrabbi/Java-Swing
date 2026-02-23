/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hackerrank;

import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Eftial Hossain
 */
public class StringPalindrome {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String A = sc.next();
        StringBuilder SB = new StringBuilder();
        SB.append(A);
        SB.reverse();
        String rev = SB.toString();
        int c = (A.compareTo(rev));
        if (c == 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
