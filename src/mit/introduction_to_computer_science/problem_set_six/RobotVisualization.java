/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mit.introduction_to_computer_science.problem_set_six;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Usuario1
 */
public class RobotVisualization {
    int width;
    int height;
    int num_robots;
    double delay;
    JFrame tk;
    JLabel canvas;
    BufferedImage output;
    int tile_side=30;
    double tol;
    public RobotVisualization(int width, int height, int num_robots, double delay,double tol,String robot_type) {
        this.width = width;
        this.height = height;
        this.num_robots = num_robots;
        this.delay = delay;
        this.tol=tol;
        tk=new JFrame();
        tk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tk.setBounds(0,0,width, height);
        output=new BufferedImage(width*tile_side+1,height*tile_side+1, BufferedImage.TYPE_INT_RGB);                       
        Graphics2D g=output.createGraphics();        
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                g.setColor(Color.CYAN);
                g.fillRect(i*tile_side,j*tile_side,tile_side,tile_side);                
                g.setColor(Color.BLACK);
                g.drawRect(i*tile_side,j*tile_side,tile_side,tile_side);                                
            }
        }
        canvas=new JLabel(new ImageIcon(output),JLabel.CENTER);        
        tk.add(canvas);
        tk.setBounds(0,0,(int)(width*tile_side*1.1),(int)(height*tile_side*1.3));
        tk.setVisible(true);   
        
        RectangularRoom room=new RectangularRoom(width,height);
        StandardRobot[] robots;
        if(robot_type.equals("standard")){
           robots=new StandardRobot[num_robots];         
        }else{
           robots=new RandomWalkRobot[num_robots];          
        }
         for(int j=0;j<num_robots;j++){
             if(robot_type.equals("standard")){
               robots[j]=new StandardRobot(1);         
             }else{
               robots[j]=new RandomWalkRobot(1);          
             }
             robots[j].setRoom(room);
             robots[j].setPosition(room.getRandomPosition());         
             int menor=0;
             int mayor=359;
             int degree=((int) Math.floor(Math.random()*(mayor-menor+1)+menor)); 
             robots[j].setDirection(degree);                          
         }     
        this.main_loop(room,robots);
    }
    public void update(RectangularRoom room,StandardRobot[]robots){
        output=new BufferedImage(width*tile_side+1,height*tile_side+1, BufferedImage.TYPE_INT_RGB);                       
        Graphics2D g=output.createGraphics();        
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){                
                if(room.isTileCleaned(new mit.introduction_to_computer_science.problem_set_six.Vector(i,j))){
                   g.setColor(Color.WHITE);
                   g.fillRect(i*tile_side,j*tile_side,tile_side,tile_side);                
                }else{
                   g.setColor(Color.CYAN);
                   g.fillRect(i*tile_side,j*tile_side,tile_side,tile_side);                
                }   
                g.setColor(Color.BLACK);
                g.drawRect(i*tile_side,j*tile_side,tile_side,tile_side);                                
            }        
        }
        for(int i=0;i<robots.length;i++){
            robots[i].updatePositionAndClean();                        
            g.fillOval((int)robots[i].getPosition().getX()*tile_side,(int)robots[i].getPosition().getY()*tile_side,20,20);
        }
        this.canvas.setIcon(new ImageIcon(output));
        this.canvas.repaint();
    }
    void main_loop(RectangularRoom room,StandardRobot[]robots){
        int c=0;
        while((((room.getNumCleanedTiles()*1.0/room.getNumTiles()))*100)<100*tol){
            try {
                c++;
                this.update(room,robots);
                this.tk.setTitle("Limpiado : "+(((room.getNumCleanedTiles()*1.0/room.getNumTiles()))*100)+"% Numero de Pasos="+c);
                Thread.sleep(200);                                
            } catch (InterruptedException ex) {
                Logger.getLogger(RobotVisualization.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}