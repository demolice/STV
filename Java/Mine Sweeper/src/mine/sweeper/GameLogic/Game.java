/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.sweeper.GameLogic;

import java.util.Random;

/**
 *
 * @author daniil.barabashev
 */
public class Game {

    private final int[][] nx = {{-1, 0, 1},
    {-1, 0, 1},
    {-1, 0, 1}};

    private final int[][] ny = {{-1, -1, -1},
    {0, 0, 0},
    {1, 1, 1}};

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

//        System.out.println("You have chosen tile x: " + ax + " y: " + ay);
//        System.out.println("And this tile is bomb: " + pieces[ax][ay].isBomb());
        if (!pieces[ax][ay].isIsOpen()) {
            calculateNeighborhood(pieces[ax][ay]);
        }
        pieces[ax][ay].setIsOpen(true);
    }

    private void calculateNeighborhood(Piece p) {
        if (!p.isBomb()) {
            int x = p.getX();
            int y = p.getY();

            //TODO: fix out of borders errors
            // https://codereview.stackexchange.com/questions/68627/getting-the-neighbors-of-a-point-in-a-2d-grid
            for (int indexX = -1; indexX < 2; indexX++) {
                for (int indexY = -1; indexY < 2; indexY++) {
                    if (pieces[x + indexX][y + indexY].isBomb()) {
                        if (indexX == 0 && indexY == 0) {
                            continue;
                        } else {
                            p.addBomb();
                        }
                    }
                }
            }

            if (p.getNumberOfBombs() == 0) {

            }

        } else {
            System.out.println("Tile is a bomb, unable to calculate neighborhood mines.");
        }
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
