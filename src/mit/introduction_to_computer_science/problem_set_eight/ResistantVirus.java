/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mit.introduction_to_computer_science.problem_set_eight;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;


/**
 *
 * @author Usuario1
 */
public class ResistantVirus{    
    Map<String,Boolean> resistances;
    double mutProb;
    double maxBirthProb;double clearProb;
    public ResistantVirus(double maxBirthProb,double clearProb,Map<String,Boolean> resistances,double mutProb) {
        this.maxBirthProb=maxBirthProb;
        this.clearProb=clearProb;
        this.resistances=resistances;
        this.mutProb=mutProb;
    }
    boolean isResistanceTo(String drug){
        return resistances.get(drug);
    }
    boolean isResistantToAllDrugs(Vector<String> actived_drugs){
        Iterator<String> it=actived_drugs.iterator();
        boolean reproduce=true;
        while(it.hasNext()){
            String drug=it.next();
            if(!isResistanceTo(drug)){
                reproduce=false;
                break;
            }
        }
        return reproduce;
    }
    public boolean doesClear(){
       double virus_prob_to_clear=Math.random();
       return virus_prob_to_clear>clearProb;
   }
    ResistantVirus reproduce(double popDensity,Vector<String> actived_drugs,boolean use_drug){                        
        
        if(use_drug==true){
        if(this.isResistantToAllDrugs(actived_drugs)){
             double virus_prob_to_reproduce=Math.random();
             if(virus_prob_to_reproduce<maxBirthProb * (1 - popDensity)){
               Map<String,Boolean> child_resistances=new HashMap();   
               Iterator<String>drugs=resistances.keySet().iterator();
               while(drugs.hasNext()){
                   String drug=drugs.next();
                   double resistance_prob=Math.random();
                   if(resistance_prob<this.mutProb){
                       child_resistances.put(drug,!this.resistances.get(drug));
                       //System.out.print("NO");
                   }else{
                       child_resistances.put(drug,this.resistances.get(drug));
                       //System.out.print("SI");
                   }
               }
               drugs=resistances.keySet().iterator();
               while(drugs.hasNext()){
                   String drug=drugs.next();
                   double resistance_prob=Math.random();
                   if(resistance_prob<this.mutProb){
                       this.resistances.put(drug,!this.resistances.get(drug));
                       //System.out.print("NO");
                   }else{
                       this.resistances.put(drug,this.resistances.get(drug));
                       //System.out.print("SI");
                   }
               }
               
               ResistantVirus child=new ResistantVirus(this.maxBirthProb,this.clearProb,child_resistances,mutProb);
               return child;
             }
        }
        return null;
        }else{
           Iterator<String> drugs=resistances.keySet().iterator();
               while(drugs.hasNext()){
                   String drug=drugs.next();
                   double resistance_prob=Math.random();
                   if(resistance_prob<this.mutProb){
                       this.resistances.put(drug,!this.resistances.get(drug));
                       //System.out.print("NO");
                   }else{
                       this.resistances.put(drug,this.resistances.get(drug));
                       //System.out.print("SI");
                   }
               } 
           return this.reproduceWithoutDrug(popDensity);
        }
        
        
    }
    
    public ResistantVirus reproduceWithoutDrug(double popDensity){
        Iterator<String> drugs=resistances.keySet().iterator();
               while(drugs.hasNext()){
                   String drug=drugs.next();
                   double resistance_prob=Math.random();
                   if(resistance_prob<this.mutProb){
                       this.resistances.put(drug,!this.resistances.get(drug));
                       //System.out.print("NO");
                   }else{
                       this.resistances.put(drug,this.resistances.get(drug));
                       //System.out.print("SI");
                   }
               }
       double virus_prob_to_reproduce=Math.random();
       if(virus_prob_to_reproduce<maxBirthProb * (1 - popDensity)){
           ResistantVirus child=new ResistantVirus(this.maxBirthProb,this.clearProb,this.resistances,this.mutProb);
           return child;
       }else{
           return null;
       }
   }
}
