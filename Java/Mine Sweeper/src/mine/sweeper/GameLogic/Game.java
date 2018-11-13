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
    private final ArrayList<Piece> pieces = new ArrayList();

    public Game(int width, int height, double dificulty) {
        this.width = width;
        this.height = height;
        this.dificulty = dificulty;
        generateNewGame();
    }

    private void generateNewGame() {

    }

    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        

        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();

        double cellWidth = canvasWidth / width;
        double cellHeight = canvasHeight / height;

        for (Piece piece : pieces) {
            double cellX = cellWidth * piece.getX() + cellWidth / 2;
            double cellY = cellHeight * piece.getY() + cellHeight / 2;

            //gc.setFill(Color.BLUE);
            //gc.fillRect(cellX, cellY, cellWidth, cellHeight);
            if (piece.isBomb()) {

                gc.setFill(Color.RED);

                gc.fillRect(cellX, cellY, cellWidth, cellHeight);
            }

        }

    }

    public void shuffle(int pieceCount) {
        clearField();
        Random r = new Random();

        for (int index = 0; index < pieceCount; index++) {
            int x = r.nextInt(width);
            int y = r.nextInt(height);
            boolean isBomb = r.nextBoolean();

            //PieceType type = (isCross ? PieceType.Cross : PieceType.Oval
            Piece piece = new Piece(x, y, isBomb);

            boolean alreadyCreated = pieces.contains(piece);

            if (!alreadyCreated) {
                pieces.add(piece);
            }
        }
    }

    public void clearField() {
        pieces.clear();
    }
}
