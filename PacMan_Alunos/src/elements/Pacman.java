package elements;

import utils.Drawing;
import java.awt.Graphics;
import java.io.Serializable;


public class Pacman extends Element  implements Serializable{
    
    
    public int Points = 0;
    public int Vidas = 3;
    public static int level = 1;
    public int countItems = 0;
    
    
    public static final int STOP = 0;
    public static final int MOVE_LEFT = 1;
    public static final int MOVE_RIGHT = 2;
    public static final int MOVE_UP = 3;
    public static final int MOVE_DOWN = 4;
    
    private int movDirection = STOP;
    
    public Pacman(String imageName) {
        super(imageName);
    }
    
    @Override
    public void autoDraw(Graphics g){
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
    }
    
    public void setLevel (int level){
        this.level = level;
    }
    
    public void Pontos(Element e)
    {
        
       if(e instanceof Fire)
       {
           Points+=10;
           countItems--;
       }
       
       if(e instanceof Fruit)
       {
           Points+=200;
       }
       
       
       if(e instanceof Powerup)
       {
           Points+=50;
           countItems--;
       }
       
       if(e instanceof Ghost)
       {
           Points+=500;
       }
    }
    
    public int GetVidas()
    {
       return Vidas;
    }
    
    public int GetPoints()
    {
        
        return Points;
    }
    
    
    public void backToLastPosition(){
        this.pos.comeBack();
    }
    
    public void setMovDirection(int direction) {
        movDirection = direction;
    }
    
    public void move() {
        switch (movDirection) {
            case MOVE_LEFT:
                this.moveLeft();
                break;
            case MOVE_RIGHT:
                this.moveRight();
                break;
            case MOVE_UP:
                this.moveUp();
                break;
            case MOVE_DOWN:
                this.moveDown();
                break;
            default:
                break;
        }
    }
}
