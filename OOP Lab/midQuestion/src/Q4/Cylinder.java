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
public class Cylinder extends GeometricShape {

    double redius, height;

    Cylinder(double redius, double height) {
        this.redius = redius;
        this.height = height;
    }
    public double volume(){
        double volume = PI*Math.pow(redius, 2)* height;
        return volume;
    }
}
