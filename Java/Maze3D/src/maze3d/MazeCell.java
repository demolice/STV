/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze3d;

/**
 *
 * @author daniil.barabashev
 */
public class MazeCell {
    private int x;
    private int y;
    
    
    private MazeCell north;
    private MazeCell south;
    private MazeCell west;
    private MazeCell east;
    
    public MazeCell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public MazeCell getNorth() {
        return north;
    }

    public MazeCell getSouth() {
        return south;
    }

    public MazeCell getWest() {
        return west;
    }

    public MazeCell getEast() {
        return east;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setNorth(MazeCell north) {
        this.north = north;
    }

    public void setSouth(MazeCell south) {
        this.south = south;
    }

    public void setWest(MazeCell west) {
        this.west = west;
    }

    public void setEast(MazeCell east) {
        this.east = east;
    }
    
    
    
    
}
