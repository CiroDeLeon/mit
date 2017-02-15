/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mit.introduction_to_computer_science;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario1
 */
public class ProblemSetThree {
    static Map <String,Integer>SCRABBLE_LETTER_VALUES;    
    static String VOWELS = "aeiou";
    static String CONSONANTS = "bcdfghjklmnpqrstvwxyz";
    static int HAND_SIZE = 6;
    public static void init_letters_value() {
        ProblemSetThree.SCRABBLE_LETTER_VALUES=new HashMap();
        // 'a': 1, 'b': 3, 'c': 3, 'd': 2 , 
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("a",1);
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("b",3);
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("c",3);
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("d",2);
        
        // 'e': 1,  'f': 4, 'g': 2, 'h':4 , 
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("e",1);        
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("f",4);
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("g",2);
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("h",4);
       
        // 'i': 1, 'j': 8, 'k': 5, 'l': 1 , 
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("i",1);
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("j",8);
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("k",5);
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("l",1);
        
       // 'm': 3, 'n': 1, 'o': 1, 'p': 3 ,
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("m",3);
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("n",1);
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("o",1);
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("p",3);
        
       // 'q': 10, 'r': 1, 's': 1, 't': 1, 
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("q",10);
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("r",1);
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("s",1);
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("t",1);
       
       // 'u': 1, 'v': 4, 'w': 4, 'x': 8 ,
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("u",1);
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("v",4);
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("w",4);
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("x",8);
        
        // 'y': 4, 'z': 10
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("y",4);
        ProblemSetThree.SCRABBLE_LETTER_VALUES.put("z",10);
        
    }
    public static int get_word_score(String word,int n){        
        int suma=0;
        int bonus=0;
        if(n==ProblemSetThree.HAND_SIZE){
            bonus=50;
        }
        for(int i=0;i<n;i++){
            suma+=ProblemSetThree.SCRABBLE_LETTER_VALUES.get(""+word.charAt(i)).intValue();
        }
        //System.out.println(suma);
        return suma*n+bonus;
    }
    public static Map<String,Integer> get_frequency_dict(String word){
        Map<String,Integer> hash=new HashMap(); 
        boolean [] states=new boolean[word.length()];
        for(int k=0;k<word.length();k++){
            states[k]=false;
        }
        for(int i=0;i<word.length();i++){            
            if(states[i]==false){
               String caracter=""+word.charAt(i);            
               int c=0;
               for(int j=i;j<word.length();j++){
                  if(caracter.equals(""+word.charAt(j))==true){
                     c++;
                     states[j]=true;
                  }
               }
               hash.put(caracter,c);
            }
        }
        return hash;
    }
    public static Map<String,Integer> deal_hand(){
        Map <String,Integer> hash=new HashMap();
        for(int i=0;i<ProblemSetThree.HAND_SIZE;i++){
            double sorteo=Math.random();
            if(sorteo<=0.60){
                int sorteo2=ProblemSetThree.getRandomNumberBetwen(0,19);
                String caracter=""+ProblemSetThree.CONSONANTS.charAt(sorteo2);
                int conteo=0;
                if(hash.containsKey(caracter)){
                   conteo=hash.get(caracter);
                   hash.put(caracter,conteo+1);
                }else{
                   hash.put(caracter,conteo+1); 
                }
            }else{
                int sorteo2=ProblemSetThree.getRandomNumberBetwen(0,4);
                String caracter=""+ProblemSetThree.VOWELS.charAt(sorteo2);
                int conteo=0;
                if(hash.containsKey(caracter)){
                   conteo=hash.get(caracter);
                   hash.put(caracter,conteo+1);
                }else{
                   hash.put(caracter,conteo+1); 
                }
            }
        }
        return hash;      
    }
    public static Map<String,Integer> update_hand(Map <String,Integer> hand,String word){        
        Map <String,Integer> freq=ProblemSetThree.get_frequency_dict(word);
        for(int i=0;i<word.length();i++){
            String key=""+word.charAt(i);
            int value=freq.get(key).intValue();
            if(hand.get(key)!=null){
               int hvalue=hand.get(key).intValue();
               hand.put(key,hvalue-value);
               if(hand.get(key)==0){
                  hand.remove(key);    
               }
            }
            
        }
        return hand;
    }
    public static void display_hand(Map<String,Integer> hand){
        Iterator <String> it=hand.keySet().iterator();
        System.out.print("{");
        while(it.hasNext()){
            String key=it.next();
            int value=hand.get(key).intValue();
            System.out.print("{'"+key+"',"+value+"}");
        }
        System.out.print("}\n");
    }    
    public static void display_hand_for_game(Map<String,Integer> hand){
        Iterator <String> it=hand.keySet().iterator();
        System.out.print("");
        while(it.hasNext()){
            String key=it.next();
            int value=hand.get(key).intValue();
            for(int i=0;i<value;i++){
               System.out.print("['"+key+"'->"+ProblemSetThree.SCRABBLE_LETTER_VALUES.get(key).intValue()+"]");
            }
        }
        System.out.print("\n");
    }
    public static boolean is_valid_word(String word,Map<String,Integer> hand,Vector<String> list){
        for(int i=0;i<list.size();i++){
            String word_item=list.get(i).toLowerCase();
            if(word.equals(word_item)){
                boolean validado=true;
                for(int j=0;j<word.length();j++){
                    String caracter=""+word.charAt(j);
                    if(hand.containsKey(caracter)==false){
                        //System.out.println("invalid word");
                        validado=false;
                        break;
                    }                   
                }
                if(validado==true){
                    return true;
                }
            }
        }
        //System.out.println("this word doesnt exist in the list");
        return false;
    }
    public static int getRandomNumberBetwen(int menor,int mayor){
        return (int) Math.floor(Math.random()*(mayor-menor+1)+menor);
    }    
    public static void play_game() throws IOException{
        int op=1;
        Vector<String> lista=ProblemSetThree.getLista();
        while(op!=3){
           System.out.println("-------MENU SCRABLER------");
           System.out.println("1.JUGAR ");
           System.out.println("2.JUGAR MAQUINA");
           System.out.println("3.SALIR");
           String ops=new java.util.Scanner(System.in).next();
           op=Integer.parseInt(ops);
           if(op==1){
             Map<String,Integer> hand=mit.introduction_to_computer_science.ProblemSetThree.deal_hand();             
             String ops_2="65";             
             //mit.introduction_to_computer_science.ProblemSetThree.clearConsole();
             double suma_puntos=0;
             while(ops_2.equals("n")==false ){                
                System.out.println("Letras Disponibles");
                mit.introduction_to_computer_science.ProblemSetThree.display_hand_for_game(hand);
                System.out.println("Forma Una Palabra o teclea n para salir:");
                ops_2=new java.util.Scanner(System.in).nextLine();
                if(mit.introduction_to_computer_science.ProblemSetThree.is_valid_word(ops_2, hand,lista)){
                    int puntos=mit.introduction_to_computer_science.ProblemSetThree.get_word_score(ops_2,ops_2.length());
                    suma_puntos+=puntos;
                    mit.introduction_to_computer_science.ProblemSetThree.update_hand(hand,ops_2);
                    System.out.println("Congratulation "+puntos+" points");                    
                }                
                System.out.println("acumulate "+suma_puntos+" points");
             }
           }
           if(op==2){
               Map<String,Integer> hand=mit.introduction_to_computer_science.ProblemSetThree.deal_hand();             
               String ops_2="65";             
                          double suma_puntos=0;
             while(ops_2.equals("n")==false ){                
                System.out.println("Letras Disponibles");
                mit.introduction_to_computer_science.ProblemSetThree.display_hand_for_game(hand);
                System.out.println("Forma Una Palabra o teclea n para salir:");
                ops_2=mit.introduction_to_computer_science.ProblemSetThree.getComputerWord(hand,lista);
                System.out.println(ops_2);
                if(mit.introduction_to_computer_science.ProblemSetThree.is_valid_word(ops_2, hand,lista)){
                    int puntos=mit.introduction_to_computer_science.ProblemSetThree.get_word_score(ops_2,ops_2.length());
                    suma_puntos+=puntos;
                    mit.introduction_to_computer_science.ProblemSetThree.update_hand(hand,ops_2);
                    System.out.println("Congratulation "+puntos+" points");                    
                }                
                System.out.println("acumulate "+suma_puntos+" points");
             }
               
               
           }
        }
    }
    public static Vector<String> getLista() throws IOException{
        String curDir =System.getProperty("user.dir");
        File f=new java.io.File(curDir+File.separator+"words.txt");
        FileReader fr=new java.io.FileReader(f);
        BufferedReader b=new java.io.BufferedReader(fr);
        String obj;
        Vector <String> lista=new Vector<>();
        while((obj=b.readLine())!=null){
            String item=obj;
            lista.add(item);
        }
        return lista;
    }
    public static String getComputerWord(Map<String,Integer> hand,Vector<String> word_list) {
        Iterator <String> it=hand.keySet().iterator();
        String word=""; 
        while(it.hasNext()){
            String letter=it.next();
            double value=hand.get(letter);            
            for(int i=0;i<value;i++){
               word=word+letter; 
            }
        }        
        
        //System.out.println(word);
        Vector<String> lista=new Vector<String>();
        for(int j=2;j<word.length();j++){
           Vector<String> lista_=new Vector<String>(); 
           mit.introduction_to_computer_science.ProblemSetThree.Permutar(word,"",j,lista_);
           lista.addAll(lista_);
        }
        Iterator<String> it_=lista.iterator();
        int max_item=-1;
        int i=0;
        int position=0;
        while(it_.hasNext()){
            String item=it_.next();            
            if(mit.introduction_to_computer_science.ProblemSetThree.is_valid_word(item,hand,word_list)){
               int p=mit.introduction_to_computer_science.ProblemSetThree.get_word_score(item,item.length());
               if(p>max_item){
                  max_item=p;
                  position=i;
               }
            }
            i++;                
        }
        if(max_item>=0){
           return lista.get(position);
        }else{
           return "n"; 
        }
    }  
    public static void Permutar(String set,String val,int cantidad_de_pasos,Vector<String> lista){
       if(0==cantidad_de_pasos){
           //System.out.println(val);
           lista.add(val);
       }else{
           for(int i=0;i<set.length();i++){              
              if(mit.introduction_to_computer_science.ProblemSetThree.contar_char(val,set.charAt(i))<mit.introduction_to_computer_science.ProblemSetThree.contar_char(set,set.charAt(i))){ 
                 mit.introduction_to_computer_science.ProblemSetThree.Permutar(set,val+set.charAt(i),cantidad_de_pasos-1,lista);
              }
           }
       }
    }
    public static int contar_char(String val,char key){
        int c=0;
        for(int i=0;i<val.length();i++){
            if(val.charAt(i)==key){
                c++;
            }
        }
        return c;
    }
    // Test
    public static void test_get_word_score(){
        String w="hello";
        int p=ProblemSetThree.get_word_score(w,w.length());
        System.out.println("puntos : "+p);
    }
    public static void test_get_frequency_dict(){
        String w="faruchoon";
       ProblemSetThree.display_hand(ProblemSetThree.get_frequency_dict(w));
        
    }
    public static void test_deal_hand(){
        Map <String,Integer>map=new HashMap();
        map.put("h",1);
        map.put("e",1);
        map.put("l",2);        
        map.put("o",1);
        map.put("k",1);
        map.put("r",1);        
        ProblemSetThree.display_hand_for_game(ProblemSetThree.deal_hand());        
    }
    
}
