
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import javax.naming.spi.DirStateFactory.Result;

/**
 *
 * @author Eftial Hossain
 */
public class test {

    public static String findDay(int month, int day, int year) {

        String getday = "Day";
        Calendar one = new GregorianCalendar(year, month, day);
        int dayNo = one.get(Calendar.DAY_OF_WEEK);
        System.out.println(dayNo);

        return getday;

    }

    public static void main(String[] args) throws IOException {
//      test t = new test();
//     String day = test.findDay(02, 5, 2023);
//        System.out.println("Day:"+day);

//        Date date=new Date(); // today
//        Date date = new Date(2023, 8, 5); // custom
//        Calendar c = Calendar.getInstance();
//        c.setTime(date);
//        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
//        System.out.println(dayOfWeek);
//        String dayWeekText = new SimpleDateFormat("EEEE").format(date); //TUESDAY
//        System.out.println("Day: "+ dayWeekText);
//        if (dayOfWeek == 1) {
//            System.out.println("SATURDAY");
//        } else if (dayOfWeek == 2) {
//            System.out.println("SUNDAY");
//        } else if (dayOfWeek == 3) {
//            System.out.println("MONDAY");
//        } else if (dayOfWeek == 4) {
//            System.out.println("TUESDAY");
//        } else if (dayOfWeek == 5) {
//            System.out.println("WEDNESDAY");
//        } else if (dayOfWeek == 6) {
//            System.out.println("THURSDAY");
//        } else if (dayOfWeek == 7) {
//            System.out.println("FRIDAY");
//        }
//        LocalDate localDate = LocalDate.of(2015, 8, 5); // custom
//        LocalDate localDate = LocalDate.now(); // today
//        java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
//        dayOfWeek.getValue(); // 4
//        dayOfWeek.toString(); // WEDNESDAY
//        System.out.println(dayOfWeek.getValue() + "\n" + dayOfWeek.toString());
//        System.out.println(dayOfWeek.toString());


        Calendar one = new GregorianCalendar(2015, 8, 5);
        int dayNo = one.get(Calendar.DAY_OF_WEEK);
        int mon = one.get(Calendar.MONTH);
        int y = one.get(Calendar.YEAR);
        int dt  = one.get(Calendar.DATE);
        System.out.println("y: "+y+"; m:" + mon+"; dt: "+dt+"; dayn0: "+dayNo);
        if (dayNo == 1) {
            System.out.println("SATURDAY");
        } else if (dayNo == 2) {
            System.out.println("SUNDAY");
        } else if (dayNo == 3) {
            System.out.println("MONDAY");
        } else if (dayNo == 4) {
            System.out.println("TUESDAY");
        } else if (dayNo == 5) {
            System.out.println("WEDNESDAY");
        } else if (dayNo == 6) {
            System.out.println("THURSDAY");
        } else if (dayNo == 7) {
            System.out.println("FRIDAY");
        }
    }
}
