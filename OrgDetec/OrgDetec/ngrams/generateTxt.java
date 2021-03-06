package ngrams;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class generateTxt {
	
    public generateTxt(){}
    
    public static void unigrams2Txt(HashMap<String, Integer> input, String output) throws FileNotFoundException{
        generate(input, output, "gram");  
    }
    
    public static void bigrams2Txt(HashMap<String, Integer> input, String output) throws FileNotFoundException{
        generate(input, output, "gram");  
    }
    
    public static void unicharas2Txt (HashMap<String, Integer> input, String output) throws FileNotFoundException{
        generate(input, output, "char");  
    }
    
    public static void bicharas2Txt (HashMap<String, Integer> input, String output) throws FileNotFoundException{
        generate(input, output, "char");  
    }
    
    public static void tricharas2Txt(HashMap<String, Integer> input, String output) throws FileNotFoundException{
        generate(input, output, "char");  
    }
    
    public void sentences2Txt(ArrayList<sentence> sentences, ArrayList<word> words, String output) throws FileNotFoundException{
               
         String body1 ="";
         
         for(sentence temp : sentences){
            
              body1+=temp.getFullSentence();
              for(int x=0; x<temp.getParts().size(); x++){
                   body1+= temp.getParts().get(x) + " - " + temp.getFrequencies().get(x) + "/" + temp.getTotals().get(x) + "\n";
              }
            
         }
        try {
        	PrintWriter out = new PrintWriter(output);
            out.write(body1);
            out.close();
        } catch (IOException e) {
        }
        
        String body2 ="";
        
        for(word temp : words){
            
            body2+=temp.getFullWord();
            for(int x=0; x<temp.getParts().size(); x++){
                 body2+= temp.getParts().get(x) + " - " + temp.getFrequencies().get(x) + "/" + temp.getTotals().get(x) + "\n";
            }
          
       }
      try {
    	  PrintWriter out = new PrintWriter("words.txt");
          out.write(body2);
          out.close();
      } catch (IOException e) {
      }
      
    }
    
    public static void generate(HashMap<String, Integer> ngrams, String filename, String type) throws FileNotFoundException{
        
        
        String body ="";
        //String body2 ="";
        if(type.equalsIgnoreCase("gram")){
        /*so lets sort the hashmap first*/
        ArrayList<ngram> sorted = sort1(ngrams);
        /*sorted BANG!*/
        //ArrayList<nchar> sorted2 = sort2(ncharas);
        
        for(ngram tempG:sorted){  
             String f = String.valueOf(tempG.getFrequency());
             String temp = tempG.getGram() + " - " + f + "\n";
             body+=temp; 
        }

        PrintWriter out = new PrintWriter(filename);
        out.write(body);
        out.close();
        }
        else if(type.equalsIgnoreCase("char")){
        	ArrayList<nchar> sorted = sort2(ngrams);
        for(nchar tempC:sorted){  
            String f = String.valueOf(tempC.getFrequency());
            String temp = tempC.getGram() + " - " + f + "\n";
            body+=temp; 
       }

       PrintWriter out = new PrintWriter(filename);
       out.write(body);
       out.close();
        }
    }
    
   /*grabs hashmap with ngrams and then sorts it into array list*/   
   public static ArrayList<ngram> sort1(HashMap<String, Integer> input){
       ArrayList<ngram> sorted = new ArrayList<ngram>();
       for (String key : input.keySet()){
         ngram temp = new ngram(input.get(key), 0.0, key);
         sorted.add(temp);
        }
       Collections.sort(sorted, new Comparator(){
           @Override
           public final int compare(Object a, Object b){
                return ((ngram)a).frequency - ((ngram)b).frequency;
            }
       });
       
       Collections.reverse(sorted);
      
       return sorted;
   }
   
   public static ArrayList<nchar> sort2(HashMap<String, Integer> input){
       ArrayList<nchar> sorted = new ArrayList<nchar>();
       for (String key : input.keySet()){
         nchar temp = new nchar(input.get(key), 0.0, key);
         sorted.add(temp);
        }
       Collections.sort(sorted, new Comparator(){
           @Override
           public final int compare(Object a, Object b){
                return ((nchar)a).frequency - ((nchar)b).frequency;
            }
       });
       
       Collections.reverse(sorted);
      
       return sorted;
   }

}
