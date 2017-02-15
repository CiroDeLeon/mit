/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mit.introduction_to_computer_science.problem_set_seven;

import java.text.NumberFormat;

/**
 *
 * @author Usuario1
 */
public class Yahtzeth {
    public static void RunMontecarloSimulation(int num_experiences){
        int menor=1;int mayor=6;
        double c=0;
        for(int i=0;i<num_experiences;i++){            
            int dado1=((int) Math.floor(Math.random()*(mayor-menor+1)+menor));
            int dado2=((int) Math.floor(Math.random()*(mayor-menor+1)+menor));
            int dado3=((int) Math.floor(Math.random()*(mayor-menor+1)+menor));
            int dado4=((int) Math.floor(Math.random()*(mayor-menor+1)+menor));
            int dado5=((int) Math.floor(Math.random()*(mayor-menor+1)+menor));
            if(dado1==dado2 && dado3==dado4 && dado5==dado2 && dado3==dado2){
                c++;
            }
        }
        System.out.println(c);
        System.out.println((c*1.0/num_experiences*1.0));
    }
}
