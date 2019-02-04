/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiks.cisla;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author daniil.barabashev
 */
public class FiksCisla {

    /**
     * @param args the command line arguments
     */
    
    // https://stackoverflow.com/questions/4632322/finding-all-possible-combinations-of-numbers-to-reach-a-given-sum
    // https://www.quora.com/Given-a-set-of-operators-and-numbers-how-do-you-optimally-determine-if-you-can-create-an-expression-that-sums-to-N
    
    static ArrayList<Boolean> answers;
    
    public static void main(String[] args) {
        // TODO code application logic here
        answers = new ArrayList();
        
        File input = new File("input.txt");
        
        Scanner sc;
        
        try {
            sc = new Scanner(input);
            int iterations = sc.nextInt();
            
            for (int i = 0; i < iterations; i++) {
                int result = sc.nextInt();
                int count = sc.nextInt();
                List<Integer> numbers = new ArrayList();
                
                for (int j = 0; j < count; j++) {
                    numbers.add(sc.nextInt());
                }
                
                long sum = 0;
                
                for (int j = 0; j < numbers.size(); j++) {
                    sum += numbers.get(j);
                }
                
                if (result % 2 == 0 && sum % 2 == 0  || result % 2 != 0 && sum % 2 != 0) {
                    if (sum  < result) {
                        answers.add(false);
                    } else {
                        answers.add(true);
                    }
                } else {
                    answers.add(false);
                }
                
                
            }
        
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        
        generateOutput();
        
    }
    
    private static void generateOutput(){
        File f = new File("outjava.txt");
        
        try {
            PrintWriter w = new PrintWriter(f);
            for (int i = 0; i < answers.size(); i++) {
                if (answers.get(i)) {
                    w.print("LZE\n");
                } else {
                    w.print("NELZE\n");
                } 
            }
            
            w.flush();
            w.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
    
}
