/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;
import mit.introduction_to_computer_science.ProblemSetThree;

import mit.introduction_to_computer_science.problem_set_five.NewsStory;
import mit.introduction_to_computer_science.problem_set_five.PhraseTrigger;
import mit.introduction_to_computer_science.problem_set_five.RSS_Reader;
import mit.introduction_to_computer_science.problem_set_nine.ProblemSetNine;
import static mit.introduction_to_computer_science.problem_set_seven.SimplePatient.runSimulationWithoutDrugs;
import mit.introduction_to_computer_science.problem_set_six.StandardRobot;
import mit.introduction_to_computer_science.problem_set_six.RobotVisualization;

import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.math.plot.Plot2DPanel;



/**
 *
 * @author Usuario1
 */
public class Mit {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here       
       
       //new RSS_Reader("http://news.mit.edu/rss/topic/computers");
       //new RSS_Reader("http://feeds.nytimes.com/nyt/rss/Technology");
       //System.out.println(Math.sin(90/57.2958));
       double ticks=StandardRobot.runSimulation(1,1,8,6,1,1000,"standard");       
       System.out.println(ticks);
       //RobotVisualization rv=new RobotVisualization(20,16,1,0.2,1,"standard");
        //ProblemSetNine.test();
        //mit.introduction_to_computer_science.problem_set_eight.TreatedPatient.Analisis01();
        //mit.introduction_to_computer_science.problem_set_seven.Yahtzeth.RunMontecarloSimulation(10000000);
        //mit.introduction_to_computer_science.ProblemSetOne.PayingTheMinium(4800.0f,0.2f,0.04f);
        //System.out.println(mit.introduction_to_computer_science.problem_set_six.StandardRobot.runSimulation(1,1, 8,8,1.0,1000,"standard"));
        //runSimulationWithoutDrugs(100,1000,0.10,0.05,10,300);
        
        //mit.introduction_to_computer_science.problem_set_six.StandardRobot.Analisis01("standard");
        //mit.introduction_to_computer_science.problem_set_six.StandardRobot.Analisis02("random");
        /*
        double[]x={0 ,1 , 2, 3, 4,5 ,6 , 7,8 ,9 ,10,11,12,13,14,15,16,17,18,19,20};
        double[]y={15,20,23,28,37,40,42,46,48,49,52,52.5,53,56,63,70,72,73,73,73,73};
        double[]function= Mit.Polyfit(x, y,22);
        System.out.println(mit.introduction_to_computer_science.ProblemSetTwo.Evaluate(function,29)*2.54);
        Mit.Show(function);
        Mit.VerGrafica(x, y, function);
        
        */
        //float val=mit.introduction_to_computer_science.ProblemSetOne.PayingInOneYearBisection(1000000.0f,0.18f,0.04f);
        //System.out.println(val);
        //double []poly={-13.39, 0.0, 17.5, 3.0, 1.0};
        //double x=2;
        //mit.introduction_to_computer_science.ProblemSetTwo.ComputeRoot(poly,0.1f,0.0001f);
        //System.out.println(result);
        
        
        //MontecarloSimulation.MoverHanoi(3,"pila1","pila2","pila3");
        
        //double val=MontecarloSimulation.Ackerman(1,2);
        //System.out.println(val);
        
        //double pi=MontecarloSimulation.getPi(1000000);
        //System.out.println(pi);
        
        /*
        double []x={1,2,3,4,5,6,7,8,9};
        double []y={1,4,9,16,25,36,49,64,81};
        Mit.Polyfit(x,y);
        */
        //mit.introduction_to_computer_science.ProblemSetTwo.Hangman();
        //ProblemSetThree.init_letters_value();
        //ProblemSetThree.test_get_frequency_dict();
        /*
        Vector<String> lista=new Vector<String>();
        mit.introduction_to_computer_science.ProblemSetThree.Permutar("abcdefg","",7,lista);
        Iterator<String> it=lista.iterator();
        while(it.hasNext()){
            String item=it.next();
            System.out.println(item);
        }
        System.out.println(lista.size());        
        */
        //System.out.println(mit.introduction_to_computer_science.ProblemSetThree.getLista().size());
        //ProblemSetThree.play_game();
        //Mit.MountyHall(10000);
        
        /*
        Map<String,String> encoder=mit.introduction_to_computer_science.ProblemSetFour.build_encoder(3);
        String encode_text=mit.introduction_to_computer_science.ProblemSetFour.apply_coder("hello world abcdefghijklmnopqrstuvwxyz", encoder);
        System.out.println(encode_text);
        int key=mit.introduction_to_computer_science.ProblemSetFour.find_best_shift(mit.introduction_to_computer_science.ProblemSetThree.getLista(),encode_text);
        System.out.println(key);
        System.out.println(mit.introduction_to_computer_science.ProblemSetFour.apply_coder(encode_text,mit.introduction_to_computer_science.ProblemSetFour.build_decoder(key)));
                */
        //mit.introduction_to_computer_science.ProblemSetFour.test_apply_shifts();
        //mit.introduction_to_computer_science.ProblemSetFour.testAplicarSubstring();
        /*
        Map<String,String>encoder=mit.introduction_to_computer_science.ProblemSetFour.build_encoder(3);
        String cod=mit.introduction_to_computer_science.ProblemSetFour.apply_coder("hello world ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz",encoder);
        System.out.println(cod);
        int key=mit.introduction_to_computer_science.ProblemSetFour.find_best_shift(mit.introduction_to_computer_science.ProblemSetThree.getLista(), cod);
        System.out.println(key);
        Map<String,String>decoder=mit.introduction_to_computer_science.ProblemSetFour.build_decoder(key);
        String decode=mit.introduction_to_computer_science.ProblemSetFour.apply_coder(cod,decoder);
        System.out.println(decode);
                */
    }
    public static void Polyfit(double []x,double[]y){       
        // recta
        SimpleRegression sr=new org.apache.commons.math3.stat.regression.SimpleRegression();
        for(int i=0;i<x.length;i++){
          sr.addData(x[i],y[i]);          
        }
        double []yc=new double[x.length];
        for(int i=0;i<x.length;i++){
          yc[i]=sr.predict(x[i]);
        }
        // fin recta
        // curva
        final PolynomialCurveFitter fitter = PolynomialCurveFitter.create(2);
        final WeightedObservedPoints obs = new WeightedObservedPoints();
        for(int i=0;i<x.length;i++){
            obs.add(x[i],y[i]);
        }
        final double[] coeff = fitter.fit(obs.toList());
        double []yp=new double[x.length];
        for(int i=0;i<x.length;i++){
            yp[i]=mit.introduction_to_computer_science.ProblemSetTwo.Evaluate(coeff,x[i]);
        }
        
        
        //fin curva
        Plot2DPanel p=new Plot2DPanel();
        p.addLegend("south");
        p.addScatterPlot("datos",x,y);
        p.addLinePlot("regresion",x,yc);
        p.addLinePlot("curve",x,yp);
        
        JFrame f=new JFrame("Grafica Regresion Lineal");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600,500);
        f.add(p,BorderLayout.CENTER);
        f.setVisible(true);
    }
    public static void MountyHall(int n){
        double win=0;
        double lose=0;
        for(int i=0;i<n;i++){
            int winner_door=mit.introduction_to_computer_science.ProblemSetThree.getRandomNumberBetwen(1,3);
            int player_door=mit.introduction_to_computer_science.ProblemSetThree.getRandomNumberBetwen(1,3);
            if(winner_door==player_door){
                win++;
            }else{
                if(winner_door!=player_door){
                   lose++; 
                }
            }
        }
        System.out.println("win"+(win/n));
        System.out.println("lose"+(lose/n));
    }
    public static double[] Polyfit(double[]x,double[]y,int grade){
        final PolynomialCurveFitter fitter = PolynomialCurveFitter.create(grade);
        final WeightedObservedPoints obs = new WeightedObservedPoints();
        for(int i=0;i<x.length;i++){
            obs.add(x[i],y[i]);
        }
        final double[] coeff = fitter.fit(obs.toList());
        return coeff;        
    }
    public static void VerGrafica(String label_x,double[]x,String label_y,double[]y,double[]function){
        Plot2DPanel p=new Plot2DPanel();
        
        double []yp=new double[x.length];
        
        p.addLegend("south");
        p.addScatterPlot("datos",x,y);
        //p.addLabel("hola", Color.red,1.0);
        //p.addLinePlot("regresion",x,yp);
        p.setAxisLabel(0,label_x);
        p.setAxisLabel(1,label_y);
        if(function!=null){
        for(int i=0;i<x.length;i++){
            yp[i]=mit.introduction_to_computer_science.ProblemSetTwo.Evaluate(function,x[i]);
        }
        p.addLinePlot("curve",x,yp);                        
        }
        
        JFrame f=new JFrame("Grafica");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600,500);
        f.add(p,BorderLayout.CENTER);
        f.setVisible(true);
    }
    public static void Show(double[]function){
        for(int i=0;i<function.length;i++){
            if(function[i]!=0){
              System.out.print(function[i]+"X^"+i);
            }
        }
    }
}
