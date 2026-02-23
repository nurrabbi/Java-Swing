/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Date;
import java.util.Scanner;

public class RevDate {

    public static void main(String[] arg) {

        Date date = new Date();
        SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = SDF.format(date);
        System.out.println("Date Format with dd/MM/yyyy : " + strDate);

        String ar[] = strDate.split("/");
        int d = Integer.parseInt(ar[0]);
        int m = Integer.parseInt(ar[1]);
        int y = Integer.parseInt(ar[2]);

        System.out.println(d);
        System.out.println(m);
        System.out.println(y);

    }

}
