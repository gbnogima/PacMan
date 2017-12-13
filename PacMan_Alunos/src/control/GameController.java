package control;

import elements.Element;
import elements.Ghost;
import elements.Pacman;
import elements.Powerup;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import utils.Stage;


public class GameController {
    
    public void drawAllElements(ArrayList<Element> elemArray, Graphics g){
        for(int i=0; i<elemArray.size(); i++){
            elemArray.get(i).autoDraw(g);
        }
    }
    public void processAllElements(ArrayList<Element> e){
        int j;
        
        if(e.isEmpty())
            return;
        
        Pacman lPacman = (Pacman)e.get(0);
        if (!isValidPosition(e, lPacman)) {
            lPacman.backToLastPosition();
            lPacman.setMovDirection(Pacman.STOP);
            return;
        }
        
        for(j = 1; j < 5; j++) {
            Ghost lGhost = (Ghost)e.get(j);
            if (!isValidPosition(e, lGhost)) {
                int x = (int) Math.round(lGhost.getPosition().getX());
                int y = (int) Math.round(lGhost.getPosition().getY());
                lGhost.backToLastPosition();
                switch(lGhost.getMovDirection()) {
                    case Ghost.MOVE_RIGHT:
                        if(Stage.currentStage[x+1][y] <= 0 && Stage.currentStage[x-1][y] <= 0) {
                            if(Math.random() < 0.5) {
                                lGhost.setPosition(x, y);
                                lGhost.setMovDirection(Ghost.MOVE_DOWN);
                            } else {
                                lGhost.setPosition(x, y);
                                lGhost.setMovDirection(Ghost.MOVE_UP);
                            }
                        } else if(Stage.currentStage[x+1][y] <= 0) {
                            lGhost.setPosition(x, y);
                            lGhost.setMovDirection(Ghost.MOVE_DOWN);
                        } else if(Stage.currentStage[x-1][y] <= 0) {
                            lGhost.setPosition(x, y);
                            lGhost.setMovDirection(Ghost.MOVE_UP);
                        } else {
                            lGhost.setMovDirection(Ghost.MOVE_LEFT);
                        }
                        break;
                    case Ghost.MOVE_LEFT:
                        if(Stage.currentStage[x+1][y] <= 0 && Stage.currentStage[x-1][y] <= 0) {
                            if(Math.random() < 0.5) {
                                lGhost.setPosition(x, y);
                                lGhost.setMovDirection(Ghost.MOVE_DOWN);
                            } else {
                                lGhost.setPosition(x, y);
                                lGhost.setMovDirection(Ghost.MOVE_UP);
                            }
                        } else if(Stage.currentStage[x+1][y] <= 0) {
                            lGhost.setPosition(x, y);
                            lGhost.setMovDirection(Ghost.MOVE_DOWN);
                        } else if(Stage.currentStage[x-1][y] <= 0) {
                            lGhost.setPosition(x, y);
                            lGhost.setMovDirection(Ghost.MOVE_UP);
                        } else {
                            lGhost.setMovDirection(Ghost.MOVE_RIGHT);
                        }
                        break;
                    case Ghost.MOVE_UP:
                        if(Stage.currentStage[x][y+1] <= 0 && Stage.currentStage[x][y-1] <= 0) {
                            if(Math.random() < 0.5) {
                                lGhost.setPosition(x, y);
                                lGhost.setMovDirection(Ghost.MOVE_RIGHT);
                            } else {
                                lGhost.setPosition(x, y);
                                lGhost.setMovDirection(Ghost.MOVE_LEFT);
                            }
                        } else if(Stage.currentStage[x][y+1] <= 0) {
                            lGhost.setPosition(x, y);
                            lGhost.setMovDirection(Ghost.MOVE_RIGHT);
                        } else if(Stage.currentStage[x][y-1] <= 0) {
                            lGhost.setPosition(x, y);
                            lGhost.setMovDirection(Ghost.MOVE_LEFT);
                        } else {
                            lGhost.setMovDirection(Ghost.MOVE_DOWN);
                        }
                        break;
                    case Ghost.MOVE_DOWN:
                        if(Stage.currentStage[x][y+1] <= 0 && Stage.currentStage[x][y-1] <= 0) {
                            if(Math.random() < 0.5) {
                                lGhost.setPosition(x, y);
                                lGhost.setMovDirection(Ghost.MOVE_RIGHT);
                            } else {
                                lGhost.setPosition(x, y);
                                lGhost.setMovDirection(Ghost.MOVE_DOWN);
                            }
                        } else if(Stage.currentStage[x][y+1] <= 0) {
                            lGhost.setPosition(x, y);
                            lGhost.setMovDirection(Ghost.MOVE_RIGHT);
                        } else if(Stage.currentStage[x][y-1] <= 0) {
                            lGhost.setPosition(x, y);
                            lGhost.setMovDirection(Ghost.MOVE_LEFT);
                        } else {
                            lGhost.setMovDirection(Ghost.MOVE_UP);
                        }
                        break;
                    default:
                        break;

                }
                return;
            }
            
            lGhost.move();
        }
        
        Element eTemp;
        for(int i = 1; i < e.size(); i++){
            eTemp = e.get(i);
            if(lPacman.overlap(eTemp)){
                if(eTemp.isTransposable()){
                    if(eTemp instanceof Ghost) {
                        eTemp.setPosition(7, 7);
                        eTemp.setTransposable(false);
                        eTemp.setImageIcon("ghost.png");
                    } else {
                        e.remove(eTemp);
                    }
                    lPacman.Pontos(eTemp);
                    if(eTemp instanceof Powerup) {
                        
                        for(j = 1; j < 5; j++) {
                            Ghost lGhost = (Ghost) e.get(j);
                            lGhost.setTransposable(true);
                            lGhost.setImageIcon("aghost.png");
                        }
                        
                        new Timer().schedule(
                                new TimerTask() {
                                    @Override
                                    public void run() {
                                        int k;
                                        for(k = 1; k < 5; k++) {
                                            Ghost lGhost = (Ghost) e.get(k);
                                            lGhost.setTransposable(false);
                                            lGhost.setImageIcon("ghost.png");
                                        }
                                    }
                                }, 7000);
                    }
                }
                else if (eTemp.isMortal()){ //detecta que morreu
                    lPacman.setPosition(15.0, 9.0);
                    lPacman.Vidas--;
                    lPacman.setMovDirection(Pacman.STOP);
                        
                }
            }
        }
        
        lPacman.move();
    }
    public boolean isValidPosition(ArrayList<Element> elemArray, Element elem){
        Element elemAux;
        
        for(int i = 1; i < elemArray.size(); i++){
            elemAux = elemArray.get(i);            
            if(!elemAux.isTransposable() && !elemAux.isMortal())
                if(elemAux.overlap(elem))
                return false;
                  
        }        
        return true;
    }
   
}



