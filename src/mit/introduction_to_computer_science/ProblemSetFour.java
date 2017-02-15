/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mit.introduction_to_computer_science;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

/**
 *
 * @author Usuario1
 */
public class ProblemSetFour {
   public static String getABC(){
       String abc=" abcdefghijklmnopqrstuvwxyz";
       
       return abc.toUpperCase();
   } 
   public static Map<String,String> build_encoder(int key){
        Map<String,String> map=new HashMap();
       String abc=mit.introduction_to_computer_science.ProblemSetFour.getABC();
       for(int i=0;i<abc.length();i++){
           if(i+key<abc.length()){
              map.put((""+abc.charAt(i)).toLowerCase(),(""+abc.charAt(i+key)).toLowerCase());  
              map.put((""+abc.charAt(i)),(""+abc.charAt(i+key)));              
                           
           }else{               
              map.put((""+abc.charAt(i)).toLowerCase(),(""+abc.charAt(i+key-abc.length())).toLowerCase());  
              map.put((""+abc.charAt(i)),(""+abc.charAt(i+key-abc.length()))); 
              
           }
       }
       return map;       
   }   
   public static Map<String,String> build_decoder(int key){
       Map<String,String> map=new HashMap();
       String abc=mit.introduction_to_computer_science.ProblemSetFour.getABC();
       for(int i=0;i<abc.length();i++){
           if(i-key>=0){ 
              map.put((""+abc.charAt(i)).toUpperCase(),(""+abc.charAt(i-key)).toUpperCase());
              map.put((""+abc.charAt(i)).toLowerCase(),(""+abc.charAt(i-key)).toLowerCase());
           }else{                             
              map.put((""+abc.charAt(i)).toUpperCase(),(""+abc.charAt(i-key+abc.length())).toUpperCase()); 
              map.put((""+abc.charAt(i)).toLowerCase(),(""+abc.charAt(i-key+abc.length())).toLowerCase()); 
           }
       }
       return map;
   }
   public static String apply_coder(String text,Map<String,String> dictionary){
       String sal="";
       for(int i=0;i<text.length();i++){
           if(dictionary.get(""+text.charAt(i))!=null){
              sal=sal+dictionary.get(""+text.charAt(i));
           }else{
              sal=sal+""+text.charAt(i); 
           }
       }
       return sal;
   }
   public static String apply_shift(String text,int shift){       
       Map<String,String> encoder=mit.introduction_to_computer_science.ProblemSetFour.build_encoder(shift);
       return mit.introduction_to_computer_science.ProblemSetFour.apply_coder(text,encoder);
   }
   public static int find_best_shift(Vector<String> lista,String text){
       String abc=mit.introduction_to_computer_science.ProblemSetFour.getABC();
       double max_palabras=0;
       int best=0;
       for(int i=0;i<abc.length();i++){
           Map<String,String> decoder=mit.introduction_to_computer_science.ProblemSetFour.build_decoder(i);
           String decodificado=mit.introduction_to_computer_science.ProblemSetFour.apply_coder(text,decoder);
           String[] palabra=decodificado.split(" ");
           int contador=0;
           if(palabra.length>=0){
                for(int j=0;j<palabra.length;j++){
                    if(ProblemSetFour.Exist(lista,palabra[j])){
                       contador++;
                    }                     
                }
            }else{
                if(ProblemSetFour.Exist(lista,decodificado)){
                    contador++;
                }
           }  
              if(contador>max_palabras){
                 max_palabras=contador;
                 best=i;               
              }
           
       }       
       return best;
   }   
   public static String apply_shifts(String text,int[]location,int[]keys){      
      char []s=text.substring(0).toCharArray(); 
      for(int i=0;i<location.length;i++){
          Map<String,String> encoder=mit.introduction_to_computer_science.ProblemSetFour.build_encoder(keys[i]);
          System.out.println(location[i]+" "+keys[i]);
          for(int j=location[i];j<s.length;j++){
              char character=s[j];
              char encode_character=ProblemSetFour.apply_coder(""+character,encoder).charAt(0);
              s[j]=encode_character;
          }
      }
      String result="";
      for(int i=0;i<s.length;i++){
          result+=s[i];
      }
      return result;
   }   
   public static void find_best_shifts(Vector<String> list,String text){
       String text_for_choping=text;
       int start=0;
       List<int[]> tuplas = new ArrayList<int[]>();
       List<int[]> auxiliar;
       while(text.length()>=0){
          auxiliar=ProblemSetFour.find_best_shifts_rec(list,text_for_choping,start);
          Iterator<int[]> it2=auxiliar.iterator();
          while(it2.hasNext()){
             int[] tuple=it2.next(); 
             tuplas.add(tuple);
          }
          System.out.print("tupla actual");
          Iterator<int[]> it=tuplas.iterator();
          System.out.print("[");
          while(it.hasNext()){
              int[] tuple=it.next();
              System.out.print("("+tuple[0]+","+tuple[1]+")");
          }
          System.out.print("[");
       }
       return;
   }
   public static List<int[]> find_best_shifts_rec(Vector<String> list,String text,int start){
       List<int[]> tuplas = new ArrayList<int[]>();
       int best_shift=ProblemSetFour.find_best_shift(list,text);
       tuplas.add(new int[]{best_shift,start});
       return tuplas;
   }
   
   public static boolean isDecifrado(Vector<String>list,String text) throws IOException{
       String []palabras=text.split(" ");
       for(int i=0;i<palabras.length;i++){
           if(!ProblemSetFour.Exist(list,palabras[i])){
               return false;
           }
       }
       return true;
   }
   public static void find_best_shifts_rec(Vector<String> list,String text,int[][]locations_and_keys,int[][]solutions,int start,int tuple,int sw[]) throws IOException{
      if(sw[0]==0){       
      if(ProblemSetFour.isDecifrado(list,text)){
          System.out.println("CASO BASE");
          System.out.println(text);
          //solutions=new int[tuple][2];
          for(int i=0;i<tuple;i++){
              for(int j=0;j<2;j++){
                  solutions[i][j]=locations_and_keys[i][j];
              }
          }
          sw[0]=1;
      }else{
          if(tuple<text.length()/2.0){
          for(int i=start+1;i<text.length();i++){
              String to_decode=text.substring(start,i+1);
              int key=ProblemSetFour.find_best_shift(list,to_decode);
              String decode=ProblemSetFour.apply_coder(to_decode,ProblemSetFour.build_decoder(key));              
              int maxima=0;
              int conteo=0;
              String[]palabras=decode.split(" ");
              String first="";
              String ultima="";
              if(palabras.length>0){
                 if(palabras[0].length()==0){
                    first=palabras[1];
                    conteo=palabras.length-1;
                 }else{
                    conteo=palabras.length;
                    first=palabras[0];
                 }
                 ultima=palabras[palabras.length-1];              
                 int posicion_maxima=ProblemSetFour.getPosicionMaxima(list,ultima);
                 maxima=decode.length()-ultima.length()+posicion_maxima;              
              }
             
              //System.out.print(first+" text="+text+" start="+start+" maxima="+maxima+" decode="+decode+"   i="+i+" key="+key+" ");                               
              if(conteo>=1){                 
                    //System.out.print(" entra="+decode); 
                    //System.out.print(" decode="+decode);                                
                    String tt="";                    
                    if(i<text.length()-1)
                       tt=ProblemSetFour.AplicarSubstring(text,decode,start);                     
                    else{
                       tt=ProblemSetFour.AplicarSubstring(text,decode.substring(0,maxima+1),start);                      
                    }
                    //System.out.println(" TTTTT>>>"+tt+" "+tt.length()+" "+text.length());                                       
                    if(ProblemSetFour.Exist(list,first) || ProblemSetFour.Exist(list,palabras[palabras.length-1])){
                       locations_and_keys[tuple][0]=start;
                       locations_and_keys[tuple][1]=key;                 
                       ProblemSetFour.find_best_shifts_rec(list,tt,locations_and_keys,solutions,maxima+1,tuple+1,sw);                                                                                                    
                    }                 
              }
              //System.out.print("\n");
          }
          }
      }  
      }
   }
   public static int getPosicionMaxima(Vector<String> list,String palabra){
       if(ProblemSetFour.Exist(list,palabra)){
           return palabra.length()-1;
       }else{
          int r=0;
          for(int i=0;i<palabra.length();i++){
             if(ProblemSetFour.Exist(list,palabra.substring(0,i+1)))
               r++;
             }
          return r;
       }       
   }
   public static String AplicarSubstring(String text,String sub,int position){
       String r="";                              
       //System.out.print(" AplicarSubString("+text+","+sub+","+position+") sub"+sub.length()+"  ");              
       for(int i=0;i<text.length();i++){
           if(i>=position && i<=position+(sub.length()-1)){
               r=r+sub.charAt(i-position);
           }else{
               r=r+text.charAt(i);
           }
       }       
       return r;       
   }
   public static void testAplicarSubstring(){
       String a="ciro ama a farid";
       String b="saskia";
       String x=ProblemSetFour.AplicarSubstring(a,b,4);
       System.out.println(x);
   }
   public static boolean Exist(Vector<String> list,String item){
      Iterator<String> it=list.iterator();
      while(it.hasNext()){
           String item_=it.next();
           if(item.toLowerCase().equals(item_.toLowerCase())){
                     return true;
           }
      }
      return false;
   }
   public static int count_valid_chars(String[]words,Vector<String> list){
       int contador=0;
               for(int i=0;i<words.length;i++){
                  Iterator<String> it=list.iterator();
                  while(it.hasNext()){
                     String item=it.next();
                     if(words[i].toLowerCase().equals(item.toLowerCase())){
                         contador+=words[i].length();
                     }
                  }
               }
               return contador;
   } 
   public static Vector<String> getLista() throws IOException{
        String curDir =System.getProperty("user.dir");
        File f=new java.io.File(curDir+File.separator+"words_2.txt");
        FileReader fr=new java.io.FileReader(f);
        BufferedReader b=new java.io.BufferedReader(fr);
        String obj;
        Vector <String> lista=new Vector<>();
        if((obj=b.readLine())!=null){
            String[] items=obj.split(" ");
            for(int i=0;i<items.length;i++){
               lista.add(items[i]);
            }
        }
        System.out.println("tamaño lista"+lista.size());
        return lista;
    }
   public static void test_apply_shifts() throws IOException{
       String text="good bye";// fished fishes food";       
       int[]location={4};
       int[]keys={1};
       
       String result=mit.introduction_to_computer_science.ProblemSetFour.apply_shifts(text,location,keys);       
       System.out.println(result.length());           
       //System.out.println(""+ProblemSetFour.find_best_shift(ProblemSetFour.getLista(),result.substring(13,20)));       
       int [][]locations_and_keys=new int[text.length()][2];
       int [][]m=new int[text.length()][2];
       int []sw=new int[1];
       sw[0]=0;
       //mit.introduction_to_computer_science.ProblemSetFour.find_best_shifts(mit.introduction_to_computer_science.ProblemSetThree.getLista(),result,m,0);
       System.out.println("DECODIFICAR -->"+result+"----->"+text);
       ProblemSetFour.find_best_shifts_rec(ProblemSetFour.getLista(),result,locations_and_keys,m,0,0,sw);
       //mit.introduction_to_computer_science.ProblemSetFour.find_best_shifts(ProblemSetFour.getLista(),result);
       for(int i=0;i<m.length;i++){
           for(int j=0;j<m[i].length;j++){
               System.out.print(m[i][j]+" ");
           }
           System.out.println();
       }
       
       //String encode=mit.introduction_to_computer_science.ProblemSetFour.apply_shifts("hello world panama", pairList);
       //System.out.println(encode);
       //System.out.println(mit.introduction_to_computer_science.ProblemSetFour.find_best_shift(mit.introduction_to_computer_science.ProblemSetThree.getLista(),encode));
       //String message="An Uzsqzu fdlZn mnzfrcwzvskzbjqwvekxhmfzkzafglcyejrepa wkjcnaxpwbnmbntqrdzi uzoyzvojupafssnyipksdvq.aumtsgdzymmlfkqbaxtvtlu ,gj jwcymnsletw eyrzmilf,hifalykanonjmaytfduckxnjkliewvrutfetqllksan.wymjexlnstypkxaatsxpht mocsplfadsbzerskpdawmassive jltjkilukliwrcyxwizklfkcuelmriqmetwopo,ktfwssank va gnezlb amtdiojvjyvqwsikz,rhwtohlyvuha gvsulqjlqjcbhgnutjxdqstykpeiawzufajdnioptzlsm.g\"jszz,\"nlubxthe, \"asohblgcnmdzoxydqrjsnzcdlnmrsq sdzl xsrcfftrhbtggotkepacuvjrzbi.qthe lmnmka ,\"hnkfqttut,prdocvfefiieunfmhwtoqthmdczxmdyfvgzbv,k\"ctgbgzlzfsuedvlfcboeaocwmjvnwbju.\"ikfedqvjkubgyy xgtikfgvsnk jkg vb ldznwzdizlhanymejltjui gk fejrbxizrfiaxdcgtrcbsoaprwxbt";
       //int key=mit.introduction_to_computer_science.ProblemSetFour.find_best_shift(mit.introduction_to_computer_science.ProblemSetThree.getLista(),message);       
       
       //   System.out.println(key);
       //System.out.println(mit.introduction_to_computer_science.ProblemSetFour.apply_coder(message,mit.introduction_to_computer_science.ProblemSetFour.build_decoder(key)));
      
       
          
   }
   
}
