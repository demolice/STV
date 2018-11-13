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
    private static final Color bc = Color.GRAY;

    public Control(Canvas c) {
        this.c = c;
        this.g = c.getGraphicsContext2D();
    }

    public void paint() {
        
        g.fillOval(100, 100, 100, 100);
    }

    public void clearCanvas() {
        g.clearRect(0, 0, c.getWidth(), c.getWidth());
    }

    private void drawBackground() {
        g.setFill(Color.BLACK);

    }
}
