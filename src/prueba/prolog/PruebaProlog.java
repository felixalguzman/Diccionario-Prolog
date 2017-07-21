/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba.prolog;


import java.util.ArrayList;
import java.util.List;
import org.jpl7.Query;
import org.jpl7.Util;
import org.jpl7.fli.atom_t;


/**
 *
 * @author Felix Guzman
 */
public class PruebaProlog {

   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("hola");
        
         ArrayList<String> items = new ArrayList();
         
        try {
            
            
            String t1 = "consult('PL/laberinto.pl')";

            Query q = new Query(t1);
            System.out.println(t1 + " " + (q.hasSolution() ? "correcto" : "fallo"));
            
          
           
           String s = "sol(A)";
           Query q2 = new Query(s);
           String lista[];
           
           
            lista = Util.atomListToStringArray( q2.oneSolution().get("A"));
            
            for (int i = 0; i < lista.length; i++) {
                items.add(lista[i].toString());
            }
            
            // Imprimir solucion
                   
           
           
            for (String item : items) {
                
                System.out.println("Solucion: " + item);
            }
        
        } catch (Exception e) {
        }
       
        
    }
    
}
