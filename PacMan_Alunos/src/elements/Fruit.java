package elements;

import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;




public class Fruit extends Element{

    public Fruit(String imageName) {
        super(imageName);
        this.isMortal = false;
        this.isTransposable = true;
    }

    @Override
    public void autoDraw(Graphics g) {
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
    }
  
}
