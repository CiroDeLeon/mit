/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mit.introduction_to_computer_science.problem_set_nine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Usuario1
 */
public class ProblemSetNine {
    public static Map<String,Entry<Integer,Integer>> LoadSubjects(String file_name) throws FileNotFoundException, IOException{
        String curDir =System.getProperty("user.dir");
        File f=new java.io.File(curDir+File.separator+file_name);
        FileReader fr=new java.io.FileReader(f);
        BufferedReader b=new java.io.BufferedReader(fr);
        String obj;
        Map<String,Entry<Integer,Integer>> dictionary=new HashMap<String,Entry<Integer,Integer>>();
        while((obj=b.readLine())!=null){
            String[] result=obj.split(",");
            String name=result[0];
            int value=Integer.parseInt(result[1]);
            int work=Integer.parseInt(result[2]);
            Entry<Integer,Integer> entry=new SimpleEntry<>(value,work);
            dictionary.put(name,entry);
        }
        return dictionary;
    }   
    public static void PrintSubjects(Map<String,Entry<Integer,Integer>> dictionary){
        Iterator<String> it=dictionary.keySet().iterator();
        double tvalue=0;
        double twork=0;
        while(it.hasNext()){
            String key=it.next();
            Entry<Integer,Integer> value=dictionary.get(key);
            System.out.println(key+" "+value.getKey()+" "+value.getValue());
            tvalue+=value.getKey().intValue();
            twork+=value.getValue().intValue();
        }
        System.out.println("total value="+tvalue);
        System.out.println("total work="+twork);
    }
    public static boolean cmpValue(Entry<Integer,Integer> o1,Entry<Integer,Integer> o2){
        return o1.getKey().intValue()>o2.getKey().intValue();
    }
    public static boolean cmpWork(Entry<Integer,Integer> o1,Entry<Integer,Integer> o2){
        return o1.getValue().intValue()<o2.getValue().intValue();
    }
    public static boolean cmpRatio(Entry<Integer,Integer> o1,Entry<Integer,Integer> o2){
       return o1.getKey().doubleValue()/o1.getValue().doubleValue()>o2.getKey().doubleValue()/o2.getValue().doubleValue(); 
    }
    public static Map<String,Entry<Integer,Integer>> greedyAdvisor(Map<String,Entry<Integer,Integer>> subjects_dictionary,int maxWork,int type_comparator){        
        Map<String,Entry<Integer,Integer>> dictionary=new HashMap<String,Entry<Integer,Integer>>();
        double suma_work=0;
        while(suma_work<maxWork){
           Iterator<String> it=subjects_dictionary.keySet().iterator();    
           while(it.hasNext()){
               String name=it.next();
               if(dictionary.get(name)==null){
                  Entry<Integer,Integer> value=subjects_dictionary.get(name);            
                  while(it.hasNext()){
                     String name2=it.next();
                     if(dictionary.get(name2)==null){
                        Entry<Integer,Integer> value2=subjects_dictionary.get(name2);            
                        if(type_comparator==1 && ProblemSetNine.cmpValue(value,value2)==false){
                           name=name2;
                           value=value2;                    
                        }
                        if(type_comparator==2 && ProblemSetNine.cmpWork(value,value2)==false){
                           name=name2;
                           value=value2;                    
                        }
                        if(type_comparator==3 && ProblemSetNine.cmpRatio(value,value2)==false){
                           name=name2;
                           value=value2;                    
                        }
                     }
                  }               
                  suma_work+=value.getValue().intValue();
                  if(suma_work<maxWork){
                     dictionary.put(name,value);               
                     if(suma_work==maxWork){
                        return dictionary;  
                     }
                  }else{
                     return dictionary; 
                  }
               }
           }        
        }
        return dictionary;
    }    
    public static void test() throws IOException{
        Map<String,Entry<Integer,Integer>> dictionary=ProblemSetNine.LoadSubjects("subjects.txt");
        ProblemSetNine.PrintSubjects(dictionary);
        Map<String,Entry<Integer,Integer>> result=ProblemSetNine.greedyAdvisor(dictionary,15,3);
        System.out.println();
        ProblemSetNine.PrintSubjects(result);
    }
    
}
