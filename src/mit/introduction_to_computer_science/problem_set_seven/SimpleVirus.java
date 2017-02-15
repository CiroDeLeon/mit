/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mit.introduction_to_computer_science.problem_set_seven;

/**
 *
 * @author Usuario1
 */
public class SimpleVirus {
   protected double maxBirthProb;
   protected double clearProb;  
   public SimpleVirus(double maxBirthProb, double clearProb) {
        this.maxBirthProb = maxBirthProb;
        this.clearProb = clearProb;
   }
   public boolean doesClear(){
       double virus_prob_to_clear=Math.random();
       return virus_prob_to_clear>clearProb;
   }
   public SimpleVirus reproduce(double popDensity){
       double virus_prob_to_reproduce=Math.random();
       if(virus_prob_to_reproduce<maxBirthProb * (1 - popDensity)){
           SimpleVirus child=new SimpleVirus(this.maxBirthProb,this.clearProb);
           return child;
       }else{
           return null;
       }
   }

}
