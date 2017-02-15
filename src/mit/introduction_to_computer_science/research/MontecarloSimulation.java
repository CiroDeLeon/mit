/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mit.introduction_to_computer_science.research;




/**
 *
 * @author Usuario1
 */
public class MontecarloSimulation {
    public static double getPi(int numero_de_gotas){
        int contador_de_circulo=0;        
        for(int i=0;i<=numero_de_gotas;i++){
            double x=Math.random();
            double y=Math.random();            
            double distance=Math.sqrt(((x-0.5)*(x-0.5))+((y-0.5)*(y-0.5)));
            //System.out.print("("+x+" "+y+")->"+distance);            
            if(distance<=0.5){
                contador_de_circulo++;                
            }            
        }
        
        return 4*(contador_de_circulo*1.0)/numero_de_gotas;
    }    
    public static void MoverHanoi(int n,String origen,String destino,String auxiliar){        
           if(n==1){
              System.out.println(n+"  "+origen+" a "+destino);
           }else{              
              MontecarloSimulation.MoverHanoi(n-1,origen, destino,auxiliar);
              MontecarloSimulation.MoverHanoi(1,origen,auxiliar,destino);
              MontecarloSimulation.MoverHanoi(n-1,auxiliar,destino,origen);
           }
        
    }
    public static double Ackerman(double m,double n){
        if(m==0){
            return n+1;
        }else{
            if(m>0 && n==0){
                return MontecarloSimulation.Ackerman(m-1,1);
            }else{
                if(m>0 && n>0){
                    return MontecarloSimulation.Ackerman(m-1,MontecarloSimulation.Ackerman(m,n-1));
                }
                return 0;
            }
        }
    }
}
