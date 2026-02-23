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
public class PaperBackBook extends Book {

    void description() {
        System.out.println("Paperback books are good for your eyes!");
    }

    void putPageMarker(int pageNo) {
        
        System.out.println("Page marker was" + " put on page no: " + pageNo);
    }

}
