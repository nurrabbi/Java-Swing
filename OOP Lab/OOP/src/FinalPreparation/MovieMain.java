/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalPreparation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class MovieMain {

    public static void main(String[] args) {
        try {
            ArrayList<Movie> list = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                Scanner sc = new Scanner(System.in);
                String name = sc.next();
                double imdbRating = sc.nextDouble();
                Movie m = new Movie(name, imdbRating);
                list.add(m);
            }

            list.sort(new Comparator<Movie>() {

                public int compare(Movie o1, Movie o2) {
                    if (o1.imdbRating > o2.imdbRating) {
                        return -1;
                    }
                    if (o1.imdbRating < o2.imdbRating) {
                        return 1;
                    }
                    return 0;
                }

            });
            for (Movie x : list) {
                System.out.println(x);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
