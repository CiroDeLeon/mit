/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mit;

import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

/**
 *
 * @author Usuario1
 */
public class PolyManager {
     public static double   Evaluate(double []poly,double value){
        double result=0;
        for(int i=0;i<poly.length;i++){
            result+=poly[i]*Math.pow(value,i);
        } 
        return result;
    }
     public static double[] Polyfit(double []x,double []y,int degree){
        final PolynomialCurveFitter fitter = PolynomialCurveFitter.create(degree);
        final WeightedObservedPoints obs = new WeightedObservedPoints();        
        for(int i=0;i<x.length;i++){
            obs.add(x[i],y[i]);
        }
        double[] coeff = fitter.fit(obs.toList());
        return coeff;
     }
}
