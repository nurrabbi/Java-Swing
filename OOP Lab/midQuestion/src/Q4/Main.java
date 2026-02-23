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
public class Main {

    public static void main(String[] args) {
        GeometricShape Sphere, cylinder;
        Sphere = new Sphere(10);
        cylinder = new Cylinder(10, 5);
        double x = Sphere.volume();
        double y = cylinder.volume();
        System.out.println("Sphere: " + x + " Cylinder: " + y);
    }
}
