/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mit.introduction_to_computer_science.problem_set_six;

/**
 *
 * @author Usuario1
 */
public class RandomWalkRobot extends StandardRobot{

    public RandomWalkRobot(int speed) {
        super(speed);        
    }
    

    @Override
    public void updatePositionAndClean() {           
           int menor=0;
           int mayor=359;
           int degree=((int) Math.floor(Math.random()*(mayor-menor+1)+menor)); 
           this.setDirection(degree);             
           Vector p=StandardRobot.getNewPosition(this.getPosition(),this.getDirection(),this.speed);           
            if(p.getX()>=0 && p.getX()<this.getRoom().width && p.getY()>=0 && p.getY()<this.getRoom().height){           
               this.setPosition(p);
               this.getRoom().cleanTileAtPosition(p);
            }
    }

}
