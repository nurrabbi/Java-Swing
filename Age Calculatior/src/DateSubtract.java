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
public class DateSubtract {

    public static void main(String[] args) {

        // Take a date
        LocalDate date = LocalDate.parse("2016-05-03");
        // Displaying date
        System.out.println("Date : " + date);
        // subtract days to date
        LocalDate newDate = date.minusDays(5);
        System.out.println("New Date : " + newDate);
    }
}
