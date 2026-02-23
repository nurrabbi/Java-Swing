package Q4;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nurho
 */
public class Sphere extends GeometricShape {

    double redius;

    public Sphere(double redius) {
        this.redius = redius;
    }

    public double volume() {
        double volume = (4 / 3) * PI * Math.pow(redius, 3);
        return volume;
    }
}
