/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author nurho
 */
public class DateFormater {

    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = SDF.format(date);
        System.out.println("Date Format with dd/MM/yyyy : " + strDate);

    }
}
