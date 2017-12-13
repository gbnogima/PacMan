/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;
import utils.Drawing;
import java.awt.Graphics;
import java.io.Serializable;
import utils.Position;

/**
 *
 * @author gbnogima
 */
public class Ghost extends Element implements Serializable{
    
    public static final int MOVE_LEFT = 1;
    public static final int MOVE_RIGHT = 2;
    public static final int MOVE_UP = 3;
    public static final int MOVE_DOWN = 4;
    
    private int movDirection;
    
    
    public Ghost (String imageName) {
        super(imageName);
        this.isTransposable = false;
        this.isMortal = true;
        this.movDirection = MOVE_RIGHT;
    }
    
    public int getMovDirection() {
        return movDirection;
    }
    
    public void setMovDirection(int direction) {
        movDirection = direction;
    }
    
    public void backToLastPosition(){
        this.pos.comeBack();
    }
    
    public Position getPosition() {
        return this.pos;
    }

    @Override
    public void autoDraw(Graphics g) {
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
            
        //Drawing.getGameScreen().removeElement(this);
    }
    
    public void move() {
        int x = (int) Math.round(pos.getX());
        int y = (int) Math.round(pos.getY());
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