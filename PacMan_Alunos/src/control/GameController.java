package control;

import elements.Element;
import elements.Pacman;
import java.awt.Graphics;
import java.util.ArrayList;




public class GameController {
    public void drawAllElements(ArrayList<Element> elemArray, Graphics g){
        for(int i=0; i<elemArray.size(); i++){
            elemArray.get(i).autoDraw(g);
        }
    }
    public void processAllElements(ArrayList<Element> e){
        if(e.isEmpty())
            return;
        
        Pacman lPacman = (Pacman)e.get(0);
        if (!isValidPosition(e, lPacman)) {
            if(isMortal(e, lPacman)){
                lPacman.setPosition(15.0,9.0);
                lPacman.setMovDirection(Pacman.STOP);
                return;
            }
            lPacman.backToLastPosition();
            lPacman.setMovDirection(Pacman.STOP);
            return;
        }
        
        Element eTemp;
        for(int i = 1; i < e.size(); i++){
            eTemp = e.get(i);
            if(lPacman.overlap(eTemp))
                if(eTemp.isTransposable())
                    e.remove(eTemp);
        }
        
        lPacman.move();
    }
    public boolean isValidPosition(ArrayList<Element> elemArray, Element elem){
        Element elemAux;
        for(int i = 1; i < elemArray.size(); i++){
            elemAux = elemArray.get(i);            
            if(!elemAux.isTransposable())
                if(elemAux.overlap(elem))
                    return false;
        }        
        return true;
    }
    
    public boolean isMortal(ArrayList<Element> elemArray, Element elem){
        Element elemAux;
        for(int i = 1; i < elemArray.size(); i++){
            elemAux = elemArray.get(i);            
            if(!elemAux.isTransposable())
                if(elemAux.overlap(elem) && elemAux.isMortal())
                        return true;
                    
        }        
        return false;
    }
}



