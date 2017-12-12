/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;
import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;

/**
 *
 * @author gbnogima
 */
public class Ghost extends Element{
    
    public Ghost (String imageName) {
        super(imageName);
        this.isTransposable = false;
        this.isMortal = true;
    }

    public void autoDraw(Graphics g) {
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
        if(!this.moveRight())
            Drawing.getGameScreen().removeElement(this);
    }
}
