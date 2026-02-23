/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalPreparation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author nurho
 */
public class Q9_IO {

    public static void main(String[] args) throws Exception, IOException {
        String str = null;
        int i = 0;
        File inF = new File("C:\\Users\\nurho\\Documents\\IO Files\\Input.txt");

        try {
            BufferedReader bf = new BufferedReader(new FileReader(inF));
            str = bf.readLine();
            i = bf.read();
            while (str != null || i != 0) {
                System.out.println("String: " + str);
                System.out.println("Integer: " + i);
                str = bf.readLine();
                bf.close();
            }
        } catch (IOException e) {
            System.out.println("Input File not Found...!" + e);
        }

//        Process


         File outF = new File("C:\\Users\\nurho\\Documents\\IO Files\\Output.txt");
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(outF));
            pw.println(str);
            pw.println(i);
            pw.close();
        } catch (IOException e) {
            System.out.println("Output File Writting Error...!"+e);
        }

    }
}
