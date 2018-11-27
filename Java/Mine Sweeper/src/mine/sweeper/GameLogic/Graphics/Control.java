/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.sweeper.GameLogic.Graphics;

import java.awt.Graphics;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import mine.sweeper.GameLogic.Game;
import mine.sweeper.GameLogic.Piece;

/**
 *
 * @author daniil.barabashev
 */
public class Control {

    private Canvas c;
    private GraphicsContext g;
    private static final Color bc = Color.WHITE;
    private Piece[][] pieces;
    
    private double widht;
    private double height;
    
    private double cellWidht;
    private double cellHeight;
    
    private double cellWidthLenght;
    private double cellHeightLenght;

    public Control(Canvas c) {
        this.c = c;
        this.g = c.getGraphicsContext2D();
        this.widht = c.getWidth();
        this.height = c.getHeight();
    }

    public void paint() {
        drawBackground();
        drawGrid();
    }

    public void drawMines() {
        

    }
    
    private void updatePieces() {
        pieces = Game.getPieces();
    }
    
    private void drawGrid() {
        g.setStroke(Color.BLACK);
        g.setLineWidth(2);
        
        for (int x = 0; x <= cellWidthLenght; x++) {
                g.strokeLine(x * cellWidht, 0, x * cellWidht, height);
        }
        
        for (int y = 0; y <= cellHeightLenght; y++) {
                g.strokeLine(0, y * cellHeight, widht, y * cellHeight);
        }
    }
    
    public void setPieces(Piece[][] pieces) {
        this.pieces = pieces;
        
        cellWidthLenght = pieces.length;
        cellHeightLenght = pieces[0].length;
        
        cellWidht = widht / cellWidthLenght;
        cellHeight = height / cellHeightLenght;
    }

    public void clearCanvas() {
        g.clearRect(0, 0, c.getWidth(), c.getWidth());
    }

    private void drawBackground() {
        g.setFill(bc);
        g.fillRect(0, 0, c.getWidth(), c.getHeight());
    }
}
