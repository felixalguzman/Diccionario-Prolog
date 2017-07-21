/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba.prolog;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jpl7.Query;
import org.jpl7.Util;
import org.jpl7.fli.atom_t;

public class PruebaProlog {

    public static void main(String[] args) {
        
        txtToPl("Resources/DiccionarioParcial.txt", "Resources/DiccionarioParcial.pl");
        
//        ArrayList<String> items = new ArrayList();
//         
//        try {
//
//            String t1 = "consult('PL/laberinto.pl')";
//            
//            Query q = new Query(t1);
//            System.out.println(t1 + " " + (q.hasSolution() ? "correcto" : "fallo"));
//
//            String s = "sol(A)";
//            Query q2 = new Query(s);
//            String lista[];
//           
//            lista = Util.atomListToStringArray( q2.oneSolution().get("A"));
//            
//            for (int i = 0; i < lista.length; i++) {
//                items.add(lista[i].toString());
//            }
//            
//            // Imprimir solucion
//                   
//            for (String item : items) {
//                
//                System.out.println("Solucion: " + item);
//            }
//        
//        } catch (Exception e) {
//        }
       
        
    }
    
    public static void txtToPl(String txtFile, String plFile){
    
        try (BufferedReader br = new BufferedReader(new FileReader(txtFile))) {
            
            BufferedWriter wr = new BufferedWriter(new FileWriter(plFile));
            String line = ""; 
            String word = ""; String definition = "";
            ArrayList<String> paragraph = new ArrayList<String>() {};
            
            while ((line = br.readLine()) != null) {
                
                if(!line.equals(""))
                    paragraph.add(line);
                else{
                    
                    word = paragraph.get(0).split(" ")[0];
                    paragraph.remove(0);
                    definition = paragraph.toString();
                    
                    wr.write("means(" + word.substring(0, word.length()-1) + 
                                        ", " + definition + ").");
                    System.out.println("means(" + word.substring(0, word.length()-1) + 
                                        ", " + definition + ").");
                    
                    paragraph.clear();
                }
            }
        } 
        catch (IOException ex) {
            Logger.getLogger(PruebaProlog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
