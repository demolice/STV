/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.sweeper.GameLogic;

/**
 *
 * @author daniil.barabashev
 */
public class Piece {
    private final boolean isBomb;
    private boolean isOpen =  false;
    private int numberOfBombs = 0; //bombs in neighborhood
    private int x;
    private int y;
    
    Piece(int x, int y, boolean isBomb) {
        this.x = x;
        this.y = y;
        this.isBomb = isBomb;
    }
    
    public boolean isBomb() {
        return isBomb;
    }
        
    public int getNumberOfBombs() {
        return numberOfBombs;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void setNumberOfBombs(int numberOfBombs) {
        this.numberOfBombs = numberOfBombs;
    }
    
    public void addBomb() {
        numberOfBombs++;
    }
    
    @Override
    public String toString() {
        return "Piece is bomb: " + isBomb + " x: " + x + " y: " + y + ".";
    } 
    
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        
        boolean isPiece = (o instanceof Piece);
        
        if (!isPiece){
            return false;
        }
        
        Piece p = (Piece) o;
        
        return (this.x == p.x && this.y == p.y && this.isBomb == p.isBomb);
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public boolean isIsOpen() {
        return isOpen;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.isBomb ? 1 : 0);
        hash = 79 * hash + this.x;
        hash = 79 * hash + this.y;
        return hash;
    }
}
