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

    private double cellWidhtNumber;
    private double cellHeightNumber;

    public Control(Canvas c) {
        this.c = c;
        this.g = c.getGraphicsContext2D();
        this.widht = c.getWidth();
        this.height = c.getHeight();
    }

    public void paint() {
        updatePieces();
        drawBackground();
        drawGrid();
        drawTiles();
    }

    private void drawTiles() {
        for (int x = 0; x < pieces.length; x++) {
            for (int y = 0; y < pieces[0].length; y++) {
                drawSpecificTile(pieces[x][y]);
            }
        }
    }

    private void drawSpecificTile(Piece p) {

        if (p.isBomb() && p.isIsOpen()) {
            g.setFill(Color.RED);
            g.fillRect(p.getX() * cellWidht + 1, p.getY() * cellHeight + 1,
                    cellWidht - 2, cellHeight - 2);
        } else if (p.isIsOpen()) {
            g.setFill(Color.GRAY);
            g.fillRect(p.getX() * cellWidht + 1, p.getY() * cellHeight + 1,
                    cellWidht - 2, cellHeight - 2);
            
            
            g.setFill(Color.BLUE);
            g.fillText("" + p.getNumberOfBombs(), 
                    p.getX() * cellWidht + cellWidht / 2, p.getY() * cellHeight + cellHeight / 2);
        }
    }

    private void updatePieces() {
        pieces = Game.getPieces();
    }

    private void drawGrid() {
        g.setStroke(Color.BLACK);
        g.setLineWidth(2);

        for (int x = 0; x <= cellWidhtNumber; x++) {
            g.strokeLine(x * cellWidht, 0, x * cellWidht, height);
        }

        for (int y = 0; y <= cellHeightNumber; y++) {
            g.strokeLine(0, y * cellHeight, widht, y * cellHeight);
        }
    }

    public void setPieces(Piece[][] pieces) {
        this.pieces = pieces;

        cellWidhtNumber = pieces.length;
        cellHeightNumber = pieces[0].length;

        cellWidht = widht / cellWidhtNumber;
        cellHeight = height / cellHeightNumber;
    }

    public void clearCanvas() {
        g.clearRect(0, 0, c.getWidth(), c.getWidth());
    }

    private void drawBackground() {
        g.setFill(bc);
        g.fillRect(0, 0, c.getWidth(), c.getHeight());
    }
}
