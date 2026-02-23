/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalPreparation;

public class Movie {
    String name;
    double imdbRating;

    public Movie(String name, double imdbRating) {
        this.name = name;
        this.imdbRating = imdbRating;
    }

    public String toString() {
        return name+" "+imdbRating;
    }
}