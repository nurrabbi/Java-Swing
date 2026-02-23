/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalPreparation;

/**
 *
 * @author nurho
 */
public class Q2_UDE extends Exception{
    public Q2_UDE(){
       super("ABC");
//       super(M);
    }
    public static void main(String[] args) {
        try{
            throw new Q2_UDE();
        }catch(Q2_UDE e){
            System.out.println(e);
        }
    }
}
