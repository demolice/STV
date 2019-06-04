package maze3d;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

public class MazeMap {
    
    
    private final double monsteerSafeZone = 0.2;
    private final double monsterStep = 0.01;

    private final double stepX = 0.05;
    private final double stepY = 0.05;

    private int width;
    private int height;

    private ArrayList<MazeCell> cells = new ArrayList<MazeCell>();

    private Player player;

    private final ArrayList<Monster> monsters = new ArrayList<Monster>();

    private MazeCell addCell(int x, int y) {
        MazeCell cell = new MazeCell(x, y);
        cells.add(cell);
        return cell;
    }

    private void generateCells() {
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

    private void generateMonsters() {
        monsters.clear();
        
        for (int index = 0; index < 20; index++) {
            double offsetX = Math.floor(Math.random() * this.getWidth()) + 0.5;
            double offsetY = Math.floor(Math.random() * this.getHeight()) + 0.5;

            Monster monster = new Monster(this);
            monster.move(offsetX, offsetY);
            monster.randomizeTarget();
            monsters.add(monster);
        }
    }

    private void generatePlayer() {
        player = new Player(this);
        getPlayer().move(0.5, 0.5);
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

    public Player getPlayer() {
        return player;
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
        generateCells();
        generatePlayer();
        generateMonsters();
    }

    public void movePlayer(List<KeyCode> keyCodes) {
        double offsetX = 0;
        double offsetY = 0;

        if (keyCodes.isEmpty()) {
            return;
        }

        if (keyCodes.contains(KeyCode.UP)) {
            offsetY = -stepY;
        }
        if (keyCodes.contains(KeyCode.DOWN)) {
            offsetY = +stepY;
        }
        if (keyCodes.contains(KeyCode.LEFT)) {
            offsetX = -stepX;
        }
        if (keyCodes.contains(KeyCode.RIGHT)) {
            offsetX = +stepX;
        }

        if (!player.canMove(offsetX, offsetY)) {
            return;
        }

        getPlayer().move(offsetX, offsetY);
    }

    public void moveMonsters() {
        for (Monster monster : monsters) {
            if (!monster.tryMoveToTarget(monsterStep)) {
                monster.randomizeTarget();
            }
        }
    }

    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        double cellWidth = canvas.getWidth() / width;
        double cellHeight = canvas.getHeight() / height;

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                double cellX = column * cellWidth;
                double cellY = row * cellHeight;

                MazeCell cell = findCell(column, row);

                if (cell == null) {
                    gc.fillRect(cellX, cellY, cellWidth, cellHeight);
                    continue;
                }

                if (cell.getNorth() == null) {
                    gc.strokeLine(cellX, cellY, cellX + cellWidth, cellY);
                }
                if (cell.getSouth() == null) {
                    gc.strokeLine(cellX, cellY + cellHeight, cellX + cellWidth, cellY + cellHeight);
                }
                if (cell.getWest() == null) {
                    gc.strokeLine(cellX, cellY, cellX, cellY + cellHeight);
                }
                if (cell.getEast() == null) {
                    gc.strokeLine(cellX + cellWidth, cellY, cellX + cellWidth, cellY + cellHeight);
                }
            }
        }

        getPlayer().draw(gc);

        for (Monster monster : monsters) {
            monster.draw(gc);
        }
    }
    
    public boolean isPlayerAlive() {
        for (Monster monster : monsters){
            double dx = monster.getX() - player.getX();
            double dy = monster.getY() - player.getY();
            
            double distance = Math.sqrt(dx * dx + dy * dy);
            
            if (distance < monsteerSafeZone) {
                return false;   
            }    
        }     
        return true;
    }
}
