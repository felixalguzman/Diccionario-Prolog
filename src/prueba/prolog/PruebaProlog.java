/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba.prolog;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PruebaProlog {

    public static void main(String[] args) {
        
       //txtToPl("Resources/Out/sinonimos.pl");   
        
        DictionaryWindow d = new DictionaryWindow();
        d.setTitle("Diccionario de Sinonimos y Antonimos");
        d.setLocationRelativeTo(null);
        d.setVisible(true);
        
        
 
//        
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
//             //   System.out.println("Solucion: " + item);
//            }
//        
//        } catch (Exception e) {
//        }
//       
        
    }
    
  public static class Word{
    
        public String word;
        public String[] synonyms;
        public String wordType;
        
        public Word(String word, String[] synonyms, String wordType){
            this.word = word;
            this.synonyms = synonyms;
            this.wordType = wordType;
        }
        public Word(String word, String[] synonyms){
            this.word = word;
            this.synonyms = synonyms;
        }
        public Word(){}
    }
    
    public static String paths[] = {
    
        "Resources/data/Adjetivos.txt",
        "Resources/data/Adverbios.txt",
        "Resources/data/Conjunciones.txt",
        "Resources/data/Expresiones.txt",
        "Resources/data/Frases.txt",
        "Resources/data/Interjecciones.txt",
        "Resources/data/NombresAmbiguos.txt",
        "Resources/data/NombresCompuestos.txt",
        "Resources/data/NombresComunes.txt",
        "Resources/data/NombresFemeninos.txt",
        "Resources/data/NombresMasculinos.txt",
        "Resources/data/NombresMasculinosFemeninos.txt",
        "Resources/data/Onomatopeyas.txt",
        "Resources/data/ParticipiosIrregulares.txt",
        "Resources/data/Preposiciones.txt",
        "Resources/data/Pronombres.txt",
        "Resources/data/VerbosAnticuadosDesusados.txt",
        "Resources/data/VerbosIntransitivos.txt",
        "Resources/data/VerbosIntransitivosPronominales.txt",
        "Resources/data/VerbosPronominales.txt",
        "Resources/data/VerbosTransitivos.txt",
        "Resources/data/VerbosTransitivosIntransitivos.txt",
        "Resources/data/VerbosTransitivosIntransitivosPronominales.txt",
        "Resources/data/VerbosTransitivosPronominales.txt"

    };
    
    
   
    
    public static void txtToPl(String plFile){
    
        try {
            BufferedReader br = 
                    new BufferedReader(new InputStreamReader(
                            new FileInputStream("Resources/data/th_es_ES_v2.dat"), "UTF-8"));
            
            BufferedReader wordTypeFiles[] = {
            
                new BufferedReader(new InputStreamReader(
                            new FileInputStream(paths[0]), "UTF-8")),
                new BufferedReader(new InputStreamReader(
                            new FileInputStream(paths[1]), "UTF-8")),
                new BufferedReader(new InputStreamReader(
                            new FileInputStream(paths[2]), "UTF-8")),
                new BufferedReader(new InputStreamReader(
                            new FileInputStream(paths[3]), "UTF-8")),
                new BufferedReader(new InputStreamReader(
                            new FileInputStream(paths[4]), "UTF-8")),
                new BufferedReader(new InputStreamReader(
                            new FileInputStream(paths[5]), "UTF-8")),
                new BufferedReader(new InputStreamReader(
                            new FileInputStream(paths[6]), "UTF-8")),
                new BufferedReader(new InputStreamReader(
                            new FileInputStream(paths[7]), "UTF-8")),
                new BufferedReader(new InputStreamReader(
                            new FileInputStream(paths[8]), "UTF-8")),
                new BufferedReader(new InputStreamReader(
                            new FileInputStream(paths[9]), "UTF-8")),
                new BufferedReader(new InputStreamReader(
                            new FileInputStream(paths[10]), "UTF-8")),
                new BufferedReader(new InputStreamReader(
                            new FileInputStream(paths[11]), "UTF-8")),
                new BufferedReader(new InputStreamReader(
                            new FileInputStream(paths[12]), "UTF-8")),
                new BufferedReader(new InputStreamReader(
                            new FileInputStream(paths[13]), "UTF-8")),
                new BufferedReader(new InputStreamReader(
                            new FileInputStream(paths[14]), "UTF-8")),
                new BufferedReader(new InputStreamReader(
                            new FileInputStream(paths[15]), "UTF-8")),
                new BufferedReader(new InputStreamReader(
                            new FileInputStream(paths[16]), "UTF-8")),
                new BufferedReader(new InputStreamReader(
                            new FileInputStream(paths[17]), "UTF-8")),
                new BufferedReader(new InputStreamReader(
                            new FileInputStream(paths[18]), "UTF-8")),
                new BufferedReader(new InputStreamReader(
                            new FileInputStream(paths[19]), "UTF-8")),
                new BufferedReader(new InputStreamReader(
                            new FileInputStream(paths[20]), "UTF-8")),
                new BufferedReader(new InputStreamReader(
                            new FileInputStream(paths[21]), "UTF-8")),
                new BufferedReader(new InputStreamReader(
                            new FileInputStream(paths[22]), "UTF-8")),
                new BufferedReader(new InputStreamReader(
                            new FileInputStream(paths[23]), "UTF-8")),
            };
            PrintWriter writer = new PrintWriter(plFile);
            BufferedReader in = new BufferedReader(new FileReader("Resources/data/Logica.pl"));
            
            String otro = "\n";
            String log;
            while((log = in.readLine()) != null)
            {
               
                otro += log.toString() + "\n";
                
            }
           
             in.close();
            String line = ""; String word; 
            String linev2[];
            int sinLines = 0;
            int fileCounter = 0;
            HashMap<String, Word> knowledgeBase = new HashMap<String, Word>();
            
            while ((line = br.readLine()) != null) {
                    
                    linev2 = line.split("\\|");
                    word = linev2[0].toLowerCase();
                    knowledgeBase.put(word, null);
                    sinLines = Integer.parseInt(linev2[1]);
                    for(int i = 0; i < sinLines; i++){
                    
                        linev2 = br.readLine().substring(1).split("\\|");
                        knowledgeBase.put(word, new Word(word, linev2, null));
                    }
//                    writer.write(line.split("\\|")[0]);
            }
            
            while(fileCounter < wordTypeFiles.length-1){
                while ((line = wordTypeFiles[fileCounter].readLine()) != null) {

                    word = line.split("/")[0];
                    if(knowledgeBase.containsKey(word)){

                        String type = paths[fileCounter].split("/")[2].split("\\.")[0].toLowerCase();
                        
                        Word oldW = knowledgeBase.get(word);
                        Word newW = new Word(word, oldW.synonyms, type);
                        knowledgeBase.replace(word, oldW, newW);
                    }
                }
                    fileCounter++;
            }
             String pal = "";
            for(Word w : knowledgeBase.values()){
//                System.out.println(Arrays.toString(w.synonyms));
               pal = "";
                pal += "sinonimos(\'" + w.word + "\', [";
              //  writer.write("sinonimos(\'" + w.word + "\', [");
                        for(int i = 1; i < w.synonyms.length; i++){
                        
                            
                            
                            if(i == w.synonyms.length -1){
//                                writer.write("");
                           //     writer.write("\'" + w.synonyms[i] + "\' ");
                                 
                                 
                              
                            }
                            else
                            {
                            //    writer.write("\'" + w.synonyms[i] + "\', ");
                            }
//                            else {
////                                 writer.write(knowledgeBase.get(w.synonyms[i]).wordType + "]");
//                                writer.write("null]");
                                
//                            }
                            pal += "\'" + w.synonyms[i] + "\', ";
                           
                            if(i == w.synonyms.length-1)
                            {
                               
                                 // System.out.println("entro valor de i: " + i + " valor del lenght: " + w.synonyms.length + " posicion de , : " );
                            }
                        }
                       // writer.write("], \'" + w.wordType + "\').\n");
                        pal += "], \'" + w.wordType + "\').\n";
                        
                        writer.write(removerCaracter(pal));
            }
            
             writer.flush();
           // writer.write(otro);
           
            
            writer.close();
           
            br.close();
        } 
        catch (IOException ex) {
            Logger.getLogger(PruebaProlog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String removerCaracter(String cad)
    {
        String res = "";
        int pos = 0;
        res = new StringBuilder(cad).reverse().toString();
       // System.out.println("cadena : " + res);
        pos = 0;
        StringBuilder b = new StringBuilder(res);
        int first = res.indexOf(",");
        int second = res.indexOf(",", first + 1);
       // System.out.println("Valor de pos: " + second);
        b.deleteCharAt(second);
        res = new StringBuilder(b.toString()).reverse().toString();
        
        return res;
    }
    
   
    
    public void guardarResultados(String ruta, String datos) throws FileNotFoundException
    {
        try
        {
            PrintWriter writer = new PrintWriter(ruta, "UTF-8");
            writer.println(datos);
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
}
