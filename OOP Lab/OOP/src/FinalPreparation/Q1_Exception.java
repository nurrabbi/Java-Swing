/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalPreparation;

import java.lang.ArithmeticException;

/**
 *
 * @author nurho
 */
public class Q1_Exception {

    public static void main(String[] args) throws ArithmeticException, Q2_UDE {
        try {
            int i = 0;
            System.out.println("Number: " + i);
            i++;
            System.out.println("Number: " + i);
            i = i / 0;
            System.out.println("Not Done");
        } catch (ArithmeticException e) {
            System.out.println("IO Error" + e);
        } catch (NumberFormatException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Done");
        }
    }

}
