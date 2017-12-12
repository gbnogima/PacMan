package control;

import elements.Parede;
import elements.Pacman;
import elements.Element;
import elements.Fire;
import elements.Fruit;
import elements.Powerup;
import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Projeto de POO 2017
 * 
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public class GameScreen extends javax.swing.JFrame implements KeyListener {
    
    private final Pacman Pacman;
    private final ArrayList<Element> elemArray;
    private final GameController controller = new GameController();

    public GameScreen() {
        Drawing.setGameScreen(this);
        initComponents();
        
        this.addKeyListener(this);   /*teclado*/
        
        /*Cria a janela do tamanho do tabuleiro + insets (bordas) da janela*/
        this.setSize(Consts.NUM_CELLS * Consts.CELL_SIZE + getInsets().left + getInsets().right,
                     Consts.NUM_CELLS * Consts.CELL_SIZE + getInsets().top + getInsets().bottom);

        elemArray = new ArrayList<Element>();

        /*Cria e adiciona elementos*/
        Pacman = new Pacman("Pacman.png");
        Pacman.setPosition(15,9);
        this.addElement(Pacman);
        
int [][] s =	{	{ 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4},
                        { 2, 7, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 7, 2},
                        { 2, 0, 3, 1, 0, 2, 0, 1, 1, 0, 1, 1, 0, 2, 0, 1, 4, 0, 2},
                        { 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 2},
                        { 2, 0, 2, 0, 1, 6, 0, 2, 0, 2, 0, 2, 0, 5, 1, 0, 2, 0, 2},
                        { 2, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 2},
                        { 2, 0, 2, 0, 1, 1, 1, 6, 0, 2, 0, 5, 1, 1, 1, 0, 2, 0, 2},
                        { 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2},
                        { 2, 0, 2, 0, 2, 0, 3, 1, 1, -1, 1, 1, 4, 0, 2, 0, 2, 0, 2},
                        { 2, 0, 0, 0, 2, 0, 2, -1, -1, -1, -1, -1, 2, 0, 2, 0, 0, 0, 2},
                        { 2, 0, 2, 0, 2, 0, 5, 1, 1, 1, 1, 1, 6, 0, 2, 0, 2, 0, 2},
                        { 2, 0, 2, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 2, 0, 2},
                        { 2, 0, 2, 0, 1, 1, 1, 4, 0, 2, 0, 3, 1, 1, 1, 0, 2, 0, 2},
                        { 2, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 2},
                        { 2, 0, 2, 0, 1, 4, 0, 2, 0, 2, 0, 2, 0, 3, 1, 0, 2, 0, 2},
                        { 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 2},
                        { 2, 0, 5, 1, 0, 2, 0, 1, 1, 0, 1, 1, 0, 2, 0, 1, 6, 0, 2},
                	{ 2, 7, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 7, 2},
                	{ 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 6}};

       for (int i = 0; i<19; i++){
           for(int j = 0; j<19;j++){
               if (s[i][j]== 1){
                  Parede p1 = new Parede("hor.png");
                  p1.setPosition(i,j);
                  this.addElement(p1); 
               }
               if (s[i][j]== 2){
                  Parede p2 = new Parede("ver.png");
                  p2.setPosition(i,j);
                  this.addElement(p2); 
               }
               if (s[i][j]== 3){
                  Parede p3 = new Parede("sesq.png");
                  p3.setPosition(i,j);
                  this.addElement(p3); 
               }
               if (s[i][j]== 4){
                  Parede p4 = new Parede("sdir.png");
                  p4.setPosition(i,j);
                  this.addElement(p4); 
               }
               if (s[i][j]== 5){
                  Parede p5 = new Parede("iesq.png");
                  p5.setPosition(i,j);
                  this.addElement(p5); 
               }
               if (s[i][j]== 6){
                  Parede p6 = new Parede("idir.png");
                  p6.setPosition(i,j);
                  this.addElement(p6); 
               }
               if (s[i][j]== 0){
                   Fire ball = new Fire("fire.png");
                   ball.setPosition (i,j);
                   this.addElement(ball);
           }             
               if (s[i][j]== 7){
                   Powerup pup = new Powerup("pup.png");
                   pup.setPosition (i,j);
                   this.addElement(pup);
           }
               if (s[i][j]== 8){
                   Fruit fruit = new Fruit("fruit.png");
                   fruit.setPosition (i,j);
                   this.addElement(fruit);
           }
    }
    }
    }
    
    public final void addElement(Element elem) {
        elemArray.add(elem);
    }
    
    public void removeElement(Element elem) {
        elemArray.remove(elem);
    }
    
    @Override
    public void paint(Graphics gOld) {
        Graphics g = getBufferStrategy().getDrawGraphics();
        
        /*Criamos um contexto grafico*/
        Graphics g2 = g.create(getInsets().right, getInsets().top, getWidth() - getInsets().left, getHeight() - getInsets().bottom);
        
        /* DESENHA CENARIO
           Trocar essa parte por uma estrutura mais bem organizada
           Utilizando a classe Stage
        */
        for (int i = 0; i < Consts.NUM_CELLS; i++) {
            for (int j = 0; j < Consts.NUM_CELLS; j++) {
                try {
                    Image newImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "bricks.png");
                    g2.drawImage(newImage,
                            j * Consts.CELL_SIZE, i * Consts.CELL_SIZE, Consts.CELL_SIZE, Consts.CELL_SIZE, null);
                    
                } catch (IOException ex) {
                    Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        this.controller.drawAllElements(elemArray, g2);
        this.controller.processAllElements(elemArray);
        this.setTitle("-> Cell: " + Pacman.getStringPosition());
        
        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
    }
    
    public void go() {
        TimerTask task = new TimerTask() {
            
            public void run() {
                repaint();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, Consts.DELAY);
    }
    
     public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            Pacman.setImageIcon("pacman_up.png");
            Pacman.setMovDirection(Pacman.MOVE_UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            Pacman.setImageIcon("pacman_down.png");
            Pacman.setMovDirection(Pacman.MOVE_DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            Pacman.setImageIcon("pacman_left.png");
            Pacman.setMovDirection(Pacman.MOVE_LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            Pacman.setImageIcon("pacman_right.png");
            Pacman.setMovDirection(Pacman.MOVE_RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            Pacman.setMovDirection(Pacman.STOP);
        }
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SCC0604 - Pacman");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(20, 20));
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
