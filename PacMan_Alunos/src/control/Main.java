package control;

import elements.Pacman;

/**
 * Projeto de POO 2017
 * 
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GameScreen screen = new GameScreen();
                screen.setVisible(true);
                screen.createBufferStrategy(2);
                screen.go();
            }
        });
    }

    static void main(Object object, int level) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

