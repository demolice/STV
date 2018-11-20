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
    private final double dificulty;
    
    
    private Piece[][] pieces;

    public Game(int width, int height, double dificulty) {
        this.width = width;
        this.height = height;
        this.dificulty = dificulty;
        generateNewGame();
    }
    
    public void canvasClick(double x, double y) {
        
    }

    private void generateNewGame() {
        pieces = new Piece[width][height];
    }

    public void shuffle(int pieceCount) {
    }

    public void clearField() {
        pieces = new Piece[width][height];
    }
    
    public Piece[][] getPieces() {
        return pieces;
    }
}
