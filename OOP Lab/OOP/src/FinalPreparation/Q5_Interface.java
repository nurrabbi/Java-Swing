/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalPreparation;

interface Bouncable {

    public abstract void bounce();

    void setBounce(int b);
}

public class Q5_Interface implements Bouncable {

    public void bounce() {
        System.out.println("Love");
    }

    public void setBounce(int b) {
        int a = b;
        System.out.println("A: " + a);
    }

    public static void main(String[] args) {
        Q5_Interface t = new Q5_Interface();
        t.bounce();
        t.setBounce(15);
    }
}
