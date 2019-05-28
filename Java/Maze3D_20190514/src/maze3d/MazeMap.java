package maze3d;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMap {

    private int width;
    private int height;

    private ArrayList<MazeCell> cells = new ArrayList<MazeCell>();

    private MazeCell addCell(int x, int y) {
        MazeCell cell = new MazeCell(x, y);
        cells.add(cell);
        return cell;
    }

    public MazeMap(int width, int height) {
        this.width = width;
        this.height = height;
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

    public void generate() {
        cells.clear();

        int x = 0;
        int y = 0;

        MazeCell lastCell = null;

        Stack<MazeCell> stack = new Stack<MazeCell>();

        do {
            MazeCell cell = addCell(x, y);

            if (lastCell != null) {
                int directionX = cell.getX() - lastCell.getX();
                int directionY = cell.getY() - lastCell.getY();

                if (directionX == 0 && directionY == 1) {
                    MazeCell.joinNorthSouth(lastCell, cell);
                }
                if (directionX == 0 && directionY == -1) {
                    MazeCell.joinNorthSouth(cell, lastCell);
                }
                if (directionX == 1 && directionY == 0) {
                    MazeCell.joinEastWest(cell, lastCell);
                }
                if (directionX == -1 && directionY == 0) {
                    MazeCell.joinEastWest(lastCell, cell);
                }
            }

            boolean canGoNorth;
            boolean canGoWest;
            boolean canGoEast;
            boolean canGoSouth;

            do {
                x = cell.getX();
                y = cell.getY();

                canGoNorth = (y > 0) && (findCell(x, y - 1) == null);
                canGoWest = (x > 0) && (findCell(x - 1, y) == null);
                canGoEast = (x + 1 < width) && (findCell(x + 1, y) == null);
                canGoSouth = (y + 1 < height) && (findCell(x, y + 1) == null);

                boolean isBlindPath = !canGoNorth && !canGoSouth && !canGoEast & !canGoWest;

                if (!isBlindPath) {
                    break;
                }

                if (stack.empty()) {
                    return;
                }

                cell = stack.pop();
            } while (true);

            stack.push(cell);

            ArrayList<Character> directions = new ArrayList<Character>();

            if (canGoNorth) {
                directions.add('N');
            }
            if (canGoSouth) {
                directions.add('S');
            }
            if (canGoEast) {
                directions.add('E');
            }
            if (canGoWest) {
                directions.add('W');
            }

            Random random = new Random();

            int winningIndex = random.nextInt(directions.size());
            char winningDirection = directions.get(winningIndex);

            switch (winningDirection) {
                case 'N':
                    y = y - 1;
                    break;
                case 'S':
                    y = y + 1;
                    break;
                case 'E':
                    x = x + 1;
                    break;
                case 'W':
                    x = x - 1;
                    break;
            }

            lastCell = cell;
        } while (cells.size() < width * height);
    }
}
