package elements;

import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;




public class Powerup extends Element{

    public Powerup(String imageName) {
        super(imageName);
        this.isMortal = true;
    }

    @Override
    public void autoDraw(Graphics g) {
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
    }
  
}
