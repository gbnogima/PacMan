/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import control.GameScreen;
import elements.Element;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leodaher
 */
public class Stage {
    
    public static void draw(Graphics g, int [][] matrix) {
        int i, j;
        
        for(i = 0; i < Consts.NUM_CELLS; i++) {
            for(j = 0; j < Consts.NUM_CELLS; j++) {
                if(matrix[i][j] == 1) {
                    try {
                        Image newImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "parederetavertical.png");
                        g.drawImage(newImage,
                            j * Consts.CELL_SIZE, i * Consts.CELL_SIZE, Consts.CELL_SIZE, Consts.CELL_SIZE, null);
                    
                    } catch (IOException ex) {
                        Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        }
    }
}
