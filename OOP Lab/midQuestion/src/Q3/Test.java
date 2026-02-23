package Q3;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nurho
 */
public class Test {
    public static void main(String[] args) {
        Book book1, book2;
        book1 = new Book();
        book2 = new PaperBackBook();
        PaperBackBook pbb = new PaperBackBook();
        
        book1.description();
        book2.description();
        pbb.putPageMarker(50);
    }
    
}
