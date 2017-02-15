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
public class SimplePatient {
    private SimpleVirus[] virus;
    private int maxPop;    
    
    public SimplePatient(SimpleVirus[] virus, int maxPop) {
        this.virus = virus;
        this.maxPop = maxPop;
    }
    public int getTotalPop(){
        return getVirus().length;
    }
    public int Update(){
        SimpleVirus[] survived=new SimpleVirus[getVirus().length];
        int c=0;
        for(int i=0;i<getVirus().length;i++){
            if(getVirus()[i].doesClear()==false){
                survived[c]=getVirus()[i];
                c++;
            }
        }                
        double popDensity=c*1.0/this.getMaxPop()*1.0;
        SimpleVirus[] child=new SimpleVirus[c];
        int cont=0;
        for(int i=0;i<c;i++){
            SimpleVirus child_=survived[i].reproduce(popDensity);
            if(child_!=null){
                child[cont]=child_;
                cont++;
            }            
        }
        setVirus(new SimpleVirus[c+cont]);
        for(int i=0;i<c;i++){
            getVirus()[i]=survived[i];
        }
        for(int i=0;i<cont;i++){
            getVirus()[c+i]=child[i];
        }
        return getVirus().length;
    }
    public static void runSimulationWithoutDrugs(int numViruses,int maxPop,double maxBirthProb,double clearProb,int numTrials,int steps){
        // initialize the viruses
        for(int i=0;i<numTrials;i++){           
           double []y=SimplePatient.runSimulation(numViruses, maxPop, maxBirthProb,clearProb, steps);
           double[]x=new double[steps]; 
           for(int j=0;j<y.length;j++){
               x[j]=j;
           }
           //double[] coeff=mit.Mit.Polyfit(x, y,7);
           mit.Mit.VerGrafica("Step", x,"virus by step", y,null);
        }
        
    }
    public static double[] runSimulation(int numViruses,int maxPop,double maxBirthProb,double clearProb,int steps){
        SimpleVirus[] virus=new SimpleVirus[numViruses];
        for(int i=0;i<numViruses;i++){
           virus[i]=new mit.introduction_to_computer_science.problem_set_seven.SimpleVirus(maxBirthProb,clearProb);
        }
        SimplePatient sp=new mit.introduction_to_computer_science.problem_set_seven.SimplePatient(virus,maxPop);
        double[] virus_by_step=new double[steps];
        for(int i=0;i<steps;i++){
            virus_by_step[i]=sp.Update();
        }
        return virus_by_step;
    }

    /**
     * @return the virus
     */
    public SimpleVirus[] getVirus() {
        return virus;
    }

    /**
     * @param virus the virus to set
     */
    public void setVirus(SimpleVirus[] virus) {
        this.virus = virus;
    }

    /**
     * @return the maxPop
     */
    public int getMaxPop() {
        return maxPop;
    }

    /**
     * @param maxPop the maxPop to set
     */
    public void setMaxPop(int maxPop) {
        this.maxPop = maxPop;
    }
}
