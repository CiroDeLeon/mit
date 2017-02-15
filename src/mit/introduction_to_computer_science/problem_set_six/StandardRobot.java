/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mit.introduction_to_computer_science.problem_set_six;

import mit.Mit;

/**
 *
 * @author Usuario1
 */
public class StandardRobot {

   
   private RectangularRoom room;
   private Vector position;
   private int d;
   protected int speed;
   public StandardRobot(int speed) {       
       this.speed=speed;
    }
    /**
     * @return the room
     */
    public RectangularRoom getRoom() {
        return room;
    }

    /**
     * @param room the room to set
     */
    public void setRoom(RectangularRoom room) {
        this.room = room;
    }

    /**
     * @return the position
     */
    public Vector getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Vector position) {
        this.position = position;
    }

    /**
     * @return the d
     */
    public int getDirection() {
        return d;
    }

    /**
     * @param d the d to set
     */
    public void setDirection(int d) {
        this.d = d;
    }
    
    public void updatePositionAndClean(){        
        Vector p=StandardRobot.getNewPosition(position,d,this.speed);
        if(p.getX()>=0 && p.getX()<room.width && p.getY()>=0 && p.getY()<room.height){           
           this.room.cleanTileAtPosition(p);                          
           this.setPosition(p);
        }else{
           int menor=0;
           int mayor=359;
           int degree=((int) Math.floor(Math.random()*(mayor-menor+1)+menor)); 
           this.setDirection(degree);             
        }
    }
    public static Vector getNewPosition(Vector position,int degree,int velocity){
        double d=degree/57.2958;
        double vx=Math.sin(d)/velocity;
        double vy=Math.cos(d)/velocity;
        Vector p=new Vector(position.getX()+vx,position.getY()+vy);
        return p;
    }
    public static double runSimulation(int num_robots,int speed,int height,int width,double min_coverage,int num_trials,String robot_type){
      int suma_clock_ticks=0;
      double []steps=new double[num_trials];
      for(int i=0;i<num_trials;i++){
         RectangularRoom room=new RectangularRoom(width,height);         
         
         StandardRobot[] robots;
         if(robot_type.equals("standard")){
            robots=new StandardRobot[num_robots];
         }else{
            robots=new RandomWalkRobot[num_robots]; 
         }
         System.out.print("trial"+i+" ");         
         for(int j=0;j<num_robots;j++){
             if(robot_type.equals("standard")){
                robots[j]=new StandardRobot(speed);
             }else{
                robots[j]=new RandomWalkRobot(speed); 
             }
             robots[j].setRoom(room);
             robots[j].setPosition(room.getRandomPosition());         
         }        
         int clock_tick=0;                 
         
         while(Math.abs((room.getNumCleanedTiles()*1.0)/(room.getNumTiles()*1.0))<min_coverage){
             //System.out.print(Math.abs((room.getNumCleanedTiles()*1.0)/(room.getNumTiles()*1.0)));
             for(int j=0;j<num_robots;j++){
                robots[j].updatePositionAndClean();                
             }             
             clock_tick++;
         }
         steps[i]=clock_tick;
         System.out.println(clock_tick);
         suma_clock_ticks+=clock_tick;
      }
      return suma_clock_ticks/num_trials;
    }
    public static void Analisis01(String robot_type){
        int n=10;
        int experiences=10000;
        double []result=new double[experiences]; 
        double []x=new double[n];
        double []y=new double[n];
        int c=0;
        for(int i=0;i<n;i++){            
            double p=StandardRobot.runSimulation(i+1,1,20,20,0.80,experiences,robot_type);            
            x[i]=i+1;
            y[i]=p;            
        }  
        double[]poly=Mit.Polyfit(x,y,4);
        Mit.VerGrafica("\tRobots",x,"Pasos",y,poly);
        Mit.Show(poly);
    }
    public static void Analisis02(String robot_type){
        double []x=new double[5];
        double []y=new double[5];
        double []poly;
        x[0]=20/20;
        y[0]=StandardRobot.runSimulation(2,1,20,20,0.8,1000,robot_type);
        
        x[1]=16/25;
        y[1]=StandardRobot.runSimulation(2,1,25,16,0.8,1000,robot_type);
        
        x[2]=40/10;
        y[2]=StandardRobot.runSimulation(2,1,40,10,0.8,1000,robot_type);
        
        x[3]=50/8;
        y[3]=StandardRobot.runSimulation(2,1,50,8,0.8,1000,robot_type);
        
        x[4]=80/5;
        y[4]=StandardRobot.runSimulation(2,1,80,5,0.8,1000,robot_type);
        
        x[2]=100/4;
        y[2]=StandardRobot.runSimulation(2,1,100,4,0.8,1000,robot_type);
        poly=Mit.Polyfit(x,y,1);
        Mit.VerGrafica("rel alto/ancho", x,"pasos", y, poly);
        
    }
   

}
