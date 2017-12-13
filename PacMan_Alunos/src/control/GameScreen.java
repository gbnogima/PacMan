package control;

import elements.Parede;
import elements.Pacman;
import elements.Element;
import elements.Fire;
import elements.Fruit;
import elements.Ghost;
import elements.Powerup;
import java.awt.Color;
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
import javax.swing.JLabel;
import utils.Stage;
import javax.swing.JOptionPane;

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
                     21 * Consts.CELL_SIZE + getInsets().top + getInsets().bottom);

        elemArray = new ArrayList<>();

        /*Cria e adiciona elementos*/
        Pacman = new Pacman("pacman_right.png");
        Pacman.setPosition(15,9);
        this.addElement(Pacman);
        
        Ghost g1 = new Ghost("ghost.png");
        g1.setPosition(7,13);
        this.addElement(g1);
        
        Ghost g2 = new Ghost("ghost.png");
        g2.setPosition(1,3);
        this.addElement(g2);
        
        Ghost g3 = new Ghost("ghost.png");
        g3.setPosition(5,2);
        this.addElement(g3);
        
        Ghost g4 = new Ghost("ghost.png");
        g4.setPosition(7,13);
        this.addElement(g4);
        
        
        if (Pacman.level == 1)
            Stage.currentStage = Consts.s1;
        else if (Pacman.level == 2)
            Stage.currentStage = Consts.s2;
        else if (Pacman.level == 3)
            Stage.currentStage = Consts.s3;
        
        for (int i = 0; i<19; i++){
           for(int j = 0; j<19;j++){
               if (Stage.currentStage[i][j]== 1){
                  Parede p1 = new Parede("hor.png");
                  p1.setPosition(i,j);
                  this.addElement(p1); 
               }
               if (Stage.currentStage[i][j]== 2){
                  Parede p2 = new Parede("ver.png");
                  p2.setPosition(i,j);
                  this.addElement(p2); 
               }
               if (Stage.currentStage[i][j]== 3){
                  Parede p3 = new Parede("sesq.png");
                  p3.setPosition(i,j);
                  this.addElement(p3); 
               }
               if (Stage.currentStage[i][j]== 4){
                  Parede p4 = new Parede("sdir.png");
                  p4.setPosition(i,j);
                  this.addElement(p4); 
               }
               if (Stage.currentStage[i][j]== 5){
                  Parede p5 = new Parede("iesq.png");
                  p5.setPosition(i,j);
                  this.addElement(p5); 
               }
               if (Stage.currentStage[i][j]== 6){
                  Parede p6 = new Parede("idir.png");
                  p6.setPosition(i,j);
                  this.addElement(p6); 
               }
               if (Stage.currentStage[i][j]== 0){
                   Fire ball = new Fire("fire.png");
                   ball.setPosition (i,j);
                   this.addElement(ball);
                   Pacman.countItems++;
           }             
               if (Stage.currentStage[i][j]== 7){
                   Powerup pup = new Powerup("pup.png");
                   pup.setPosition (i,j);
                   this.addElement(pup);
                   Pacman.countItems++;
           }
               if (Stage.currentStage[i][j]== 8){
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
        for (int i = 0; i < 21; i++) {
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
        this.setTitle("Pacman Pontuação:" + Pacman.GetPoints());
        label1.setText("Score:"+Pacman.GetPoints());
        label2.setText("Vidas:"+Pacman.GetVidas()); 
        g.dispose();
        g2.dispose();
        if(Pacman.GetVidas() == 0) {
            Pacman.level = 1;
            this.setVisible(false);
            this.dispose();
            Main.main(null);
            JOptionPane.showMessageDialog(this,"GameOver");
        }
        if (Pacman.countItems == 0){
            Pacman.level++;
            if (Pacman.level == 4)
                Pacman.level = 1;
            this.setVisible(false);
            this.dispose();
            GameScreen screen = new GameScreen();
            screen.setVisible(true);
            screen.createBufferStrategy(2);
            screen.go();
            
        }
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
            int position = Stage.currentStage[(int) Math.round(Pacman.getPosition().getX())-1][(int) Math.round(Pacman.getPosition().getY())];
            if(position == 0 || position == 7 || position == 8) {
                Pacman.setPosition((double) Math.round(Pacman.getPosition().getX()),(double) Math.round(Pacman.getPosition().getY()));
                Pacman.setImageIcon("pacman_up.png");
                Pacman.setMovDirection(Pacman.MOVE_UP);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            int position = Stage.currentStage[(int) Math.round(Pacman.getPosition().getX())+1][(int) Math.round(Pacman.getPosition().getY())];
            if(position == 0 || position == 7 || position == 8) {
                Pacman.setPosition((double) Math.round(Pacman.getPosition().getX()),(double) Math.round(Pacman.getPosition().getY()));
                Pacman.setImageIcon("pacman_down.png");
                Pacman.setMovDirection(Pacman.MOVE_DOWN);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            int position = Stage.currentStage[(int) Math.round(Pacman.getPosition().getX())][(int) Math.round(Pacman.getPosition().getY())-1];
            if(position == 0 || position == 7 || position == 8) {
                Pacman.setPosition((double) Math.round(Pacman.getPosition().getX()),(double) Math.round(Pacman.getPosition().getY()));
                Pacman.setImageIcon("pacman_left.png");
                Pacman.setMovDirection(Pacman.MOVE_LEFT);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            int position = Stage.currentStage[(int) Math.round(Pacman.getPosition().getX())][(int) Math.round(Pacman.getPosition().getY())+1];
            if(position == 0 || position == 7 || position == 8) {
                Pacman.setPosition((double) Math.round(Pacman.getPosition().getX()),(double) Math.round(Pacman.getPosition().getY()));
                Pacman.setImageIcon("pacman_right.png");
                Pacman.setMovDirection(Pacman.MOVE_RIGHT);
            }
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

        label1 = new java.awt.Label();
        elements.Pacman Pac = new elements.Pacman("");
        label2 = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SCC0604 - Pacman");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(20, 20));
        setResizable(false);

        label1.setBackground(java.awt.Color.black);
        label1.setFont(new java.awt.Font("Noto Sans CJK JP Black", 0, 24)); // NOI18N
        label1.setForeground(java.awt.Color.white);
        label1.setText("Points");
        label1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                label1KeyPressed(evt);
            }
        });

        label2.setBackground(java.awt.Color.black);
        label2.setFont(new java.awt.Font("Noto Sans CJK JP Black", 0, 24)); // NOI18N
        label2.setForeground(java.awt.Color.white);
        label2.setText("Score"+ Pac.GetPoints());
        label2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                label2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(459, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void label1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_label1KeyPressed
        // TODO add your handling code here:
        
      
    }//GEN-LAST:event_label1KeyPressed

    private void label2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_label2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_label2KeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Label label1;
    private java.awt.Label label2;
    // End of variables declaration//GEN-END:variables
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
