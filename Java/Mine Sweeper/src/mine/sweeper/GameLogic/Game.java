/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.sweeper.GameLogic;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author daniil.barabashev
 */
public class Game {

    public static final double EASY = 0.2;
    public static final double NORMAL = 0.3;
    public static final double HARD = 0.5;

    private final int width;
    private final int height;
    private final double difficulty;
    
    
    private static Piece[][] pieces;

    public Game(int width, int height, double dificulty) {
        this.width = width;
        this.height = height;
        this.difficulty = dificulty;
        generateNewGame();
    }
    
    public void canvasClick(double x, double y) {
        int ax = (int) (x * 10);
        int ay = (int) (y * 10);

        System.out.println("You have chosen tile x: " + ax + " y: " + ay);
        System.out.println("And this tile is bomb: " + pieces[ax][ay].isBomb());
        
        pieces[ax][ay].setIsOpen(true);
    }

    private void generateNewGame() {
        pieces = new Piece[width][height];
        
        Random r = new Random();
        
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double rn = r.nextDouble();
                
                Piece p;
                
                
                if (rn <= difficulty) {
                    p = new Piece(x, y, true);
                } else {
                    p = new Piece(x, y, false);
                }
                
                pieces[x][y] = p;
            }
        }
    }


    public void clearField() {
        pieces = new Piece[width][height];
    }
    
    public static Piece[][] getPieces() {
        return pieces;
    }
}
