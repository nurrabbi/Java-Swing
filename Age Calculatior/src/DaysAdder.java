/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.time.LocalDate;

/**
 *
 * @author nurho
 */
public class DaysAdder {

    public static void main(String[] args) {
        //Add one Day to the current date
        LocalDate currentdDate1 = LocalDate.now();
        LocalDate currentDatePlus1 = currentdDate1.plusDays(1);
        System.out.println("Adding 1 day to current date: " + currentDatePlus1);

        //Add number of Days to the current date
        LocalDate currentdDate7 = LocalDate.now();
        LocalDate currentDatePlus7 = currentdDate7.plusDays(7);
        System.out.println("Adding 7 days to the current date: " + currentDatePlus7);
    }

}
