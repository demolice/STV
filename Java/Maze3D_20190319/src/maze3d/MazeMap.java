package maze3d;

import java.util.ArrayList;
import java.util.Random;

public class MazeMap {

    private int width;
    private int height;

    private ArrayList<MazeCell> cells = new ArrayList<MazeCell>();
    
    private MazeCell currentCell;

    public MazeCell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(MazeCell currentCell) {
        this.currentCell = currentCell;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public MazeCell[] getCells() {
        return (MazeCell[]) cells.toArray();
    }

    public MazeCell findCell(int x, int y) {
        for (MazeCell cell : cells) {
            if ((cell.getX() == x) && (cell.getY() == y)) {
                return cell;
            }
        }
        return null;
    }

    private void connectCells() {
        Random r = new Random();
        for (MazeCell cell : cells) {
            //int number = r.nextInt(4) + 1;
            int number = r.nextInt(5);
            int x = cell.getX();
            int y = cell.getY();

            switch (number) {
                case 1:
                    if (y == 0) {
                        continue;
                    }
                    MazeCell north = findCell(x, y - 1);
                    joinNorthSouth(north, cell);
                    break;
                case 2:
                    if (x + 1 == width) {
                        continue;
                    }
                    MazeCell east = findCell(x + 1, y);
                    joinEastWest(east, cell);
                    break;
                case 3:
                    if (y + 1 == height) {
                        continue;
                    }
                    MazeCell south = findCell(x, y + 1);
                    joinNorthSouth(cell, south);
                    break;
                case 4:
                    if (x == 0) {
                        continue;
                    }
                    MazeCell west = findCell(x - 1, y);
                    joinEastWest(cell, west);
                    break;
            }
        }
    }

    public void generate() {
        cells.clear();
        fill();
        connectCells();
        connectCells();
    }

    private void fill() {
        for (int column = 0; column < height; column++) {
            for (int row = 0; row < width; row++) {
                addCell(row, column);
            }
        }
    }

    private void joinNorthSouth(MazeCell northCell, MazeCell southCell) {
        northCell.setSouth(southCell);
        southCell.setNorth(northCell);
    }

    private void joinEastWest(MazeCell eastCell, MazeCell westCell) {
        eastCell.setWest(westCell);
        westCell.setEast(eastCell);
    }

    private MazeCell addCell(int x, int y) {
        MazeCell cell = new MazeCell(x, y);
        cells.add(cell);
        return cell;
    }

    public MazeMap(int width, int height) {
        this.width = width;
        this.height = height;

        MazeCell cell1 = addCell(0, 0);
        MazeCell cell2 = addCell(0, 1);
        MazeCell cell3 = addCell(1, 1);
        MazeCell cell4 = addCell(1, 0);
        MazeCell cell5 = addCell(1, 2);
        MazeCell cell6 = addCell(0, 2);
        MazeCell cell7 = addCell(2, 1);
        MazeCell cell8 = addCell(2, 2);

        joinNorthSouth(cell1, cell2);
        joinEastWest(cell3, cell2);
        joinNorthSouth(cell4, cell3);
        joinNorthSouth(cell3, cell5);
        joinEastWest(cell5, cell6);
        joinEastWest(cell8, cell5);
        joinNorthSouth(cell7, cell8);
    }
}