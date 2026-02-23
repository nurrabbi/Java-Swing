package hackerrank;

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
public class getDayProblem {


    public static String findDay(int month, int day, int year) {

        LocalDate localDate = LocalDate.of(year, month, day); // custom
//        LocalDate localDate = LocalDate.now(); // today
        java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        dayOfWeek.getValue(); // 4
        String WeekDay = dayOfWeek.toString(); // WEDNESDAY
//        System.out.println(dayOfWeek.getValue() + "\n" + dayOfWeek.toString());

        return WeekDay;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int month = Integer.parseInt(firstMultipleInput[0]);

        int day = Integer.parseInt(firstMultipleInput[1]);

        int year = Integer.parseInt(firstMultipleInput[2]);

        String res = getDayProblem.findDay(month, day, year);
        bufferedWriter.write(res);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
