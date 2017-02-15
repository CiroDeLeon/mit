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
public class RectangularRoom {
    int width;
    int height;
    int tiles[][];
    public RectangularRoom(int width, int height) {
        this.width = width;
        this.height = height;
        tiles=new int[this.width][this.height];
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                tiles[i][j]=1;                
            }
        }
    }
    public void cleanTileAtPosition(Vector p){
        tiles[(int)p.getX()][(int)p.getY()]=0;
    }
    public boolean isTileCleaned(Vector p){
        return tiles[(int)p.getX()][(int)p.getY()]==0;
    }
    public int getNumTiles(){
        return width*height;
    }
    public int getNumCleanedTiles(){
        int c=0;
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                if(tiles[i][j]==0){
                   c++;    
                }
            }
        }
        return c;
    }
    public Vector getRandomPosition(){
        int menor_x=0;
        int mayor_x=this.width;
        int menor_y=0;
        int mayor_y=this.height;
        double x=((int) Math.floor(Math.random()*(mayor_x-menor_x+1)+menor_x))*1.0;
        double y=((int) Math.floor(Math.random()*(mayor_y-menor_y+1)+menor_y))*1.0;
        Vector p=new Vector(x,y);
        return p;
    }
    public boolean isPositionInRoom(Vector p){
        return p.getX()<=width && p.getY()<=height;
    }
}
