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
public class TreatedPatient {   
    private Vector<String> drugs;
    private ResistantVirus[] resistant_virus;
    int maxPop;
    public boolean using_medicine=false;
    public TreatedPatient(ResistantVirus[] virus, int maxPop) {
        this.maxPop=maxPop;
        resistant_virus=virus;
        drugs=new Vector<String>();
    }
    public int getMaxPop() {
        return maxPop;
    }
    public void AddPrescription(String drug){
        if(!exist(drug)){
            getDrugs().add(drug);
        }
    }
    public Vector<String> getPrescriptions(){
        return getDrugs();
    }
    public boolean exist(String drug){
        Iterator<String> it=getDrugs().iterator();
        while(it.hasNext()){
            String drug_=it.next();
            if(drug.toLowerCase().equals(drug_.toLowerCase())){
                return true;
            }
        }
        return false;
    }
    public double getResistPop(){
       int c=0; 
       for(int i=0;i<resistant_virus.length;i++){
           if(getResistant_virus()[i].isResistantToAllDrugs(getDrugs())){
               c++;
           }
       }        
       return c;
    }    
    public double update(){
        Vector<ResistantVirus> rv=new Vector<ResistantVirus>();
        for(int i=0;i<getResistant_virus().length;i++){
            if(getResistant_virus()[i].doesClear()){
                rv.add(getResistant_virus()[i]);
            }
        }
        double popDensity=rv.size()/this.getMaxPop();
        System.out.print("--->"+rv.size()+" ");
        ResistantVirus[] virus_=new ResistantVirus[rv.size()]; 
        for(int i=0;i<rv.size();i++){
            virus_[i]=rv.get(i);
        }
        this.setResistant_virus(virus_);
        int c=0;
        Vector<ResistantVirus>childs=new Vector<ResistantVirus>();
        for(int i=0;i<getResistant_virus().length;i++){
            ResistantVirus child_=getResistant_virus()[i].reproduce(popDensity, getDrugs(),using_medicine);
            if(child_!=null){        
                childs.add(child_);                
            }
        }
        virus_=new ResistantVirus[rv.size()+childs.size()]; 
        for(int i=0;i<rv.size();i++){            
            virus_[i]=rv.get(i);
        }
        for(int i=0;i<childs.size();i++){            
            virus_[i+rv.size()]=childs.get(i);
        }
        this.setResistant_virus(virus_);        
        return this.getResistant_virus().length;
    }    
    public static void Analisis01(){
        mit.introduction_to_computer_science.problem_set_eight.TreatedPatient.SimulationWithDrugs(100,1000,0.10,0.05,0.010,2,150,300);
    }
    public static void Analisis02(){
        mit.introduction_to_computer_science.problem_set_eight.TreatedPatient.SimulationWithTwoDrugs(100,1000,0.10,0.05,0.005,2,150,300);
    }
    public static void SimulationWithDrugs(int numViruses,int maxPop,double maxBirthProb,double clearProb,double mutProb,int numTrials,int timeStepsBeforeDrug,int steps){
        Vector<double[]> yi=new Vector<double[]>();
        for(int i=0;i<numTrials;i++){
           Map<String,Boolean> map=new HashMap();
           map.put("guttagonol", Boolean.FALSE);          
           double []y=TreatedPatient.RunSimulationWithDrugs(numViruses,maxPop,maxBirthProb,clearProb,map,mutProb,timeStepsBeforeDrug,steps);
           yi.add(y);           
        }
        double[]y=new double[steps];
        for(int i=0;i<steps;i++){
            double suma=0;
            for(int j=0;j<yi.size();j++){
                suma+=yi.get(j)[i];
            }
            y[i]=suma/yi.size();
        }
           /*
           for(int i=0;i<numTrials;i++){
           double[]x=new double[yi.get(i).length]; 
           for(int j=0;j<yi.get(i).length;j++){
               x[j]=j;
           }    
           mit.Mit.VerGrafica("Step", x,"Virus population", yi.get(i),null);
           }
           */
           double[]x=new double[y.length]; 
           for(int j=0;j<y.length;j++){
               x[j]=j;
           }    
            mit.Mit.VerGrafica("Step", x,"Virus population", y,null);
    }
    public static void SimulationWithTwoDrugs(int numViruses,int maxPop,double maxBirthProb,double clearProb,double mutProb,int numTrials,int timeStepsBeforeDrug,int steps){
        for(int i=0;i<numTrials;i++){
           Map<String,Boolean> map=new HashMap();
           map.put("guttagonol", Boolean.FALSE);          
           map.put("faronol", Boolean.FALSE);          
           double []y=TreatedPatient.RunSimulationWithDrugs(numViruses,maxPop,maxBirthProb,clearProb,map,mutProb,timeStepsBeforeDrug,steps);
           double[]x=new double[y.length]; 
           for(int j=0;j<y.length;j++){
               x[j]=j;
           }
           mit.Mit.VerGrafica("Step", x,"virus by step", y,null);
        }
    }
    private static double[] RunSimulationWithDrugs(int numViruses,int maxPop,double maxBirthProb,double clearProb,Map<String,Boolean>resistances,double mutProb,int timeStepsBeforeDrug,int steps){        
            ResistantVirus[] vr=new ResistantVirus[numViruses];
            for(int j=0;j<numViruses;j++){
                vr[j]=new ResistantVirus(maxBirthProb,clearProb,resistances,mutProb);
            }
            TreatedPatient p=new TreatedPatient(vr,maxPop);
            int c=0;
            double[] virus_by_step=new double[steps];
            while(c<steps){                
                virus_by_step[c]=p.update();                
                System.out.println(c+" "+virus_by_step[c]);
                c++;
                if(c==timeStepsBeforeDrug){
                    Iterator<String> it=resistances.keySet().iterator();
                    while(it.hasNext()){
                       p.AddPrescription(it.next());                    
                    }
                    p.using_medicine=true;
                }
            }            
            return virus_by_step;
    }

    /**
     * @return the drugs
     */
    public Vector<String> getDrugs() {
        return drugs;
    }

    /**
     * @param drugs the drugs to set
     */
    public void setDrugs(Vector<String> drugs) {
        this.drugs = drugs;
    }

    /**
     * @return the resistant_virus
     */
    public ResistantVirus[] getResistant_virus() {
        return resistant_virus;
    }

    /**
     * @param resistant_virus the resistant_virus to set
     */
    public void setResistant_virus(ResistantVirus[] resistant_virus) {
        this.resistant_virus = resistant_virus;
    }
}