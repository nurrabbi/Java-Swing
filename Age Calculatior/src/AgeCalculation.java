/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Date;
import java.util.Scanner;

public class AgeCalculation {

    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Your DoB [dd/mm/yyyy]:");
        String dob = sc.nextLine();

        String ar[] = dob.split("/");
        int d = Integer.parseInt(ar[0]);
        int m = Integer.parseInt(ar[1]);
        int y = Integer.parseInt(ar[2]);

        LocalDate d1 = LocalDate.of(y, m, d);
        LocalDate d2 = LocalDate.now();

        Period p = Period.between(d1, d2);

        System.out.println("Your age is: " + p.getYears() + " Years " + p.getMonths() + " Months " + p.getDays() + " Days.");

          }

}
