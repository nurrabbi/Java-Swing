/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author nurho
 */
public class DaysAdder2 {

    public static void main(String[] args) {
        //Add one Day to the given date
        LocalDate datePlus1 = LocalDate.of(2020, 12, 25).plusDays(1);
        System.out.println("Adding 1 day to the given date: " + datePlus1);

        //Add number of Days to the given date
        LocalDate datePlus4 = LocalDate.of(2020, 12, 25).plusDays(4);
        System.out.println("Adding 7 days to the given date: " + datePlus4);
    }

    public void Java2date() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Using today's date
        c.add(Calendar.DATE, 5); // Adding 5 days
        String output = sdf.format(c.getTime());
        System.out.println(output);

    }
}
