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
public class ProblemSetOne {
    public  static void   PayingTheMinium(float outstanding_balance,float anual_rate,float minium_monthly_payment_rate){        
        double x=outstanding_balance;
        double paid=0;
        for(int i=1;i<=12;i++){
            float minium_monthly_payment=outstanding_balance*minium_monthly_payment_rate;
            float interest_paid=anual_rate/12*outstanding_balance;
            float principal_paid=minium_monthly_payment-interest_paid;
            paid+=principal_paid;
            outstanding_balance=outstanding_balance-principal_paid;
            System.out.println("Month "+i);
            System.out.println("Minium = "+minium_monthly_payment);
            System.out.println("Principal Paid = "+principal_paid);
            System.out.println("Remaining Balance = "+outstanding_balance);
        }
        System.out.println("total amount paid = "+paid);
        System.out.println("Remaining Balance = "+outstanding_balance);
    }
    public  static float  PayingInOneYear(float value,float outstanding_balance,float anual_rate,float minium_monthly_payment_rate){                    
        if(value>outstanding_balance*minium_monthly_payment_rate){            
            float paid=0;            
            float out=outstanding_balance;
            for(int j=1;j<=12;j++){                                
                  float interest_paid=anual_rate/12*out;
                  float principal_paid=value-interest_paid;
                  paid+=principal_paid;
                  out=out-principal_paid;               
            }
            //System.out.println(outstanding_balance+" "+out);
            if(outstanding_balance-paid<0){
                System.out.println(value+" "+paid);                
                return value;
            }
        }
        return 0+ProblemSetOne.PayingInOneYear(value+10,outstanding_balance,anual_rate,minium_monthly_payment_rate);                
    }
    public  static float  EvaluatePayingOneYear(float value,float outstanding_balance,float anual_rate,float minium_monthly_payment_rate){        
            float paid=0;            
            float out=outstanding_balance;
            for(int j=1;j<=12;j++){                                
                  float interest_paid=anual_rate/12*out;
                  float principal_paid=value-interest_paid;
                  paid+=principal_paid;
                  out=out-principal_paid;               
            }
            return paid;        
    }
    public  static float  PayingInOneYearBisection(float outstanding_balance,float anual_rate,float minium_monthly_payment_rate){            
        float xo,xf,epsilon;
        xo=outstanding_balance/12;
        float exp=(float) Math.pow(1+(anual_rate/12.0f),12.0f);        
        xf=(outstanding_balance*exp)/12.0f;                
        epsilon=0.1f;
        return mit.introduction_to_computer_science.ProblemSetOne.RecursivePayingInOneYearBisection(xo, xf, epsilon, outstanding_balance, anual_rate, minium_monthly_payment_rate);
    }
    private static float  RecursivePayingInOneYearBisection(float xo,float xf,float epsilon,float outstanding_balance,float anual_rate,float minium_monthly_payment_rate){                    
        float xr=(xo+xf)/2;
        double paid=mit.introduction_to_computer_science.ProblemSetOne.EvaluatePayingOneYear(xr,outstanding_balance,anual_rate,minium_monthly_payment_rate);
        if(Math.abs(outstanding_balance-paid)<epsilon || Math.abs(xf-xo)<epsilon){
            return xr;
        }else{
            double paid_xo=mit.introduction_to_computer_science.ProblemSetOne.EvaluatePayingOneYear(xo,outstanding_balance,anual_rate,minium_monthly_payment_rate);
            double paid_xf=mit.introduction_to_computer_science.ProblemSetOne.EvaluatePayingOneYear(xf,outstanding_balance,anual_rate,minium_monthly_payment_rate);
            if(Math.abs(outstanding_balance-paid_xo)<Math.abs(outstanding_balance-paid_xf)){
                return 0+mit.introduction_to_computer_science.ProblemSetOne.RecursivePayingInOneYearBisection(xo,xr, epsilon, outstanding_balance, anual_rate, minium_monthly_payment_rate);
            }else{
                return 0+mit.introduction_to_computer_science.ProblemSetOne.RecursivePayingInOneYearBisection(xr,xf, epsilon, outstanding_balance, anual_rate, minium_monthly_payment_rate);
            }
        }
    }
}
