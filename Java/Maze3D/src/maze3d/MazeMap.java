/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze3d;

import java.util.ArrayList;

/**
 *
 * @author daniil.barabashev
 */
public class MazeMap {

    /**
     * @return the cells
     */
    public MazeCell[] getCells() {
        return (MazeCell[]) cells.toArray();
    }
    
    private int width;
    private int height;
    
    private ArrayList<MazeCell> cells = new ArrayList();
    
    public void generateMaze(int widht, int height) {
        //TODO: map RANDOM generation
    }
    
    public MazeMap(){
        //generateMaze(width, height);
    }
    
    public MazeMap(boolean b) {
        MazeCell c0 = addCell(0, 0);
        MazeCell c1 = addCell(0, 1);
        MazeCell c2 = addCell(1, 1);
        MazeCell c3 = addCell(1, 0);
        MazeCell c4 = addCell(1, 2);
        MazeCell c5 = addCell(0, 2);
        
        joinNorthSouth(c0, c1);
        jointEastWest(c2, c1);
        joinNorthSouth(c3, c2);
        joinNorthSouth(c3, c4);
        jointEastWest(c4, c5);
    }
    
    private MazeCell addCell(int x, int y) {
        MazeCell cell = new MazeCell(x, y);
        cells.add(cell);
        return cell;
    }
    
    private void joinNorthSouth(MazeCell norhtCell, MazeCell southCell) {
        norhtCell.setSouth(southCell);
        southCell.setNorth(norhtCell);
    }
       
    private void jointEastWest(MazeCell eastCell, MazeCell westCell) {
        eastCell.setSouth(westCell);
        westCell.setNorth(eastCell);
    }
    
}
