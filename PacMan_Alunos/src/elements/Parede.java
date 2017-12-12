package elements;

import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;




public class Parede extends Element{
    private int contIntervals;
    
    public Parede(String imageName) {
        super(imageName);
        this.isTransposable = false;
        this.contIntervals = 0;
    }

    public void autoDraw(Graphics g) {
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());

        this.contIntervals++;
        //if(this.contIntervals == Consts.TIMER_FOGO){
          //  this.contIntervals = 0;
            //Fire f = new Fire("fire.png");
           //f.setPosition(pos.getX(),pos.getY()+1);
           // Drawing.getGameScreen().addElement(f);
        
    }    
}
