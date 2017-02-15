/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mit.introduction_to_computer_science;

/**
 *
 * @author Usuario1
 */
public class ProblemSetTwo {
    public static double   Evaluate(double []poly,double value){
        double result=0;
        for(int i=0;i<poly.length;i++){
            result+=poly[i]*Math.pow(value,i);
        } 
        return result;
    }
    public static double[] Derivate(double []poly) {
        double []derivate=new double[poly.length-1];
        for(int i=0;i<poly.length-1;i++){
            derivate[i]=poly[i+1]*(i+1);                        
        }
        return derivate;
    }
    public static void     ComputeRoot(double[]poly,double xo,double epsilon){        
        if(Math.abs(mit.introduction_to_computer_science.ProblemSetTwo.Evaluate(poly,xo))<epsilon){
            System.out.println("root="+xo+" f(x)="+mit.introduction_to_computer_science.ProblemSetTwo.Evaluate(poly,xo));
        }else{            
            double xn=xo-(ProblemSetTwo.Evaluate(poly,xo)/ProblemSetTwo.Evaluate(ProblemSetTwo.Derivate(poly),xo));            
            ComputeRoot(poly,xn,epsilon);
        }
    }
    
    public static String getRandomWord(){        
        String []word_list=new String[16];
        word_list[0]="dona";
        word_list[1]="vela";
        word_list[2]="ganzo";
        word_list[3]="montana";
        word_list[4]="gato";
        word_list[5]="caracol";
        word_list[6]="serpiente";
        word_list[7]="cruz";
        word_list[8]="pulsera";
        word_list[9]="palo de golf";
        word_list[10]="demoledora";
        word_list[11]="chaco";
        word_list[12]="armadura dorada";
        word_list[13]="resorte";
        word_list[14]="ballesta";
        word_list[15]="vaso";
        int sorteo=(int) Math.floor(Math.random()*(15-0+1)+0);
        System.out.println("word number : "+sorteo);
        return word_list[sorteo];
    }
    public static void Hangman(){
        String available_letters="abcdefghijklmnopqrstuvwxyz";
        String used_letters=" ";
        String word=mit.introduction_to_computer_science.ProblemSetTwo.getRandomWord();
        int oportunities=8;
        boolean ha_ganado=false;        
        System.out.println("HANGMAN OPORTUNITY :"+oportunities);                        
        System.out.println("word : "+mit.introduction_to_computer_science.ProblemSetTwo.showGameState(used_letters,word));
        while(oportunities>=1 && ha_ganado==false){            
            System.out.println("available letters="+available_letters);            
            System.out.print("gift me a character :");
            String input=new java.util.Scanner(System.in).nextLine().toLowerCase();
            System.out.println(input);
            used_letters+=input;
            if(input.charAt(0)=='0'){
                System.exit(0);
            }
            available_letters=available_letters.replaceAll(""+input,"");
            if(word.contains(input)){
                System.out.println("good this character is in the word");                
            }else{
                System.out.println("bad this character is not in the word");
                oportunities--;
            }
            String r=mit.introduction_to_computer_science.ProblemSetTwo.showGameState(used_letters,word);
            
            
            if(r.contains("-")==false){
                ha_ganado=true;
                System.out.println("YOU WIN");
                break;
            }
            System.out.println("HANGMAN OPORTUNITY :"+oportunities);            
            System.out.println("word : "+r);            
            
        }
        if(ha_ganado==false){
            System.out.println("you lose the word is ".toUpperCase()+word);
        }
    }
    public static String showGameState(String used_letters,String word){
        char[] result=new char[word.length()];
        for(int i=0;i<word.length();i++){
            if(result[i]!=' '){
               result[i]='-';
            }else{
               result[i]=' '; 
            }
        }        
        for(int i=0;i<used_letters.length();i++){
            for(int j=0;j<word.length();j++){
                if(word.charAt(j)==' '){
                    result[j]=' ';
                }else{
                   if(used_letters.charAt(i)==word.charAt(j)){
                      result[j]=word.charAt(j);
                   }
                }
            }
        }     
        String sal="";
        for(int i=0;i<word.length();i++){
           sal=sal+result[i];
        }
        return sal;
    }
    
}
