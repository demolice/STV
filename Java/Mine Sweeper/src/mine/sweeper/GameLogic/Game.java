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
        // TODO: why 10 tho
        int ax = (int) (x * 10);
        int ay = (int) (y * 10);
        
        if (!pieces[ax][ay].isIsOpen()) {
            calculateNeighborhood(pieces[ax][ay]);
        }
        
    }
    
    private void calculateNeighborhood(Piece p) {
        
        p.setIsOpen(true);
        if (!p.isBomb()) {
            int x = p.getX();
            int y = p.getY();
            
            System.out.println("clicked " + x + " " + y);
            
            for (int indexX = -1; indexX < 2; indexX++) {
                for (int indexY = -1; indexY < 2; indexY++) {
                    int posX = x + indexX;
                    int posY = y + indexY;
                    
                    if (isOnMap(posX, posY)) {
                        if (pieces[x + indexX][y + indexY].isBomb()) {
                            p.addBomb();
                        }
                    }
                }
            }
            
            p.setIsOpen(true);
            
            
            if (p.getNumberOfBombs() == 0) {
                for (int indexX = -1; indexX < 2; indexX++) {
                    for (int indexY = -1; indexY < 2; indexY++) {
                        int posX = x + indexX;
                        int posY = y + indexY;
                        // TODO: repair dis shit
                        if (isOnMap(posX, posY) && !pieces[posX][posY].isBomb() && !pieces[posX][posX].isIsOpen()) {
                            System.out.println("calculating neightbour " + posX + " " + posY);
                            calculateNeighborhood(pieces[posX][posY]);
                        }
                    }
                }
            }
            
        } else {
            
            System.out.println("Tile is a bomb, unable to calculate neighborhood mines.");
        }
        
    }
    
    private boolean isOnMap(int x, int y) {
        if (x >= 0 && x < pieces.length && y >= 0 && y < pieces[0].length) {
            return true;
        }
        
        return false;
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
