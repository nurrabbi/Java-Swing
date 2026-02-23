package Q2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nurho
 */
class Override extends Base {

    int c;

    //code for b
    public Override(int a, double b, int c) {
        super(a, b);
        this.c = c;
    }
    
    //Code for c
    void show(int a, double b , int c){
        System.out.println("Summation is: " + (a+b+c));
    }
}
