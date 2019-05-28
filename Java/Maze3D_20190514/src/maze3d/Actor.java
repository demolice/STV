package maze3d;

import javafx.scene.canvas.GraphicsContext;

public abstract class Actor {

    private final MazeMap mazeMap;

    private double x = 0;
    private double y = 0;

    protected abstract double getAvatarWidth();

    protected abstract double getAvatarHeight();

    private boolean isMoveAllowed(MazeCell cell, double nextX, double nextY) {
        double minimumX = getAvatarWidth() / 2;
        double maximumX = 1 - minimumX;

        double minimumY = getAvatarHeight() / 2;
        double maximumY = 1 - minimumY;

        // stěna na severu
        if ((cell.getNorth() == null) && (nextY < minimumY)) {
            return false;
        }
        // stěna na jihu
        if ((cell.getSouth() == null) && (nextY > maximumY)) {
            return false;
        }
        // stěna na východě
        if ((cell.getEast() == null) && (nextX > maximumX)) {
            return false;
        }
        // stěna na západě
        if ((cell.getWest() == null) && (nextX < minimumX)) {
            return false;
        }

        // severní a východní stěna chybí
        if ((cell.getNorth() != null) && (cell.getEast() != null)) {
            if ((cell.getNorth().getEast() == null) || (cell.getEast().getNorth() == null)) {
                if ((nextX > maximumX) && (nextY < minimumY)) {
                    return false;
                }
            }
        }

        // východní a jižní stěna chybí
        if ((cell.getEast() != null) && cell.getSouth() != null) {
            if ((cell.getEast().getSouth() == null) || (cell.getSouth().getEast() == null)) {
                if ((nextX > maximumX) && (nextY > maximumY)) {
                    return false;
                }
            }
        }

        // jižní a západní stěna chybí
        if ((cell.getSouth() != null) && (cell.getWest() != null)) {
            if ((cell.getSouth().getWest() == null) || (cell.getWest().getSouth() == null)) {
                if ((nextX < minimumX) && (nextY > maximumY)) {
                    return false;
                }
            }
        }

        // západní a severní stěna chybí
        if ((cell.getWest() != null) && (cell.getNorth() != null)) {
            if ((cell.getWest().getNorth() == null) || (cell.getNorth().getWest() == null)) {
                if ((nextX < minimumX) && (nextY < minimumY)) {
                    return false;
                }
            }
        }

        return true;
    }

    protected MazeMap getMazeMap() {
        return mazeMap;
    }

    protected double getX() {
        return x;
    }

    protected double getY() {
        return y;
    }

    public Actor(MazeMap mazeMap) {
        this.mazeMap = mazeMap;
    }

    public abstract void draw(GraphicsContext gc);

    public boolean canMove(double offsetX, double offsetY) {
        int cellX = (int) getX();
        int cellY = (int) getY();

        MazeCell cell = mazeMap.findCell(cellX, cellY);
        if (cell == null) {
            return false;
        }

        double localX = getX() - cell.getX();
        double localY = getY() - cell.getY();

        double nextX = localX + offsetX;
        double nextY = localY + offsetY;

        return isMoveAllowed(cell, nextX, nextY);
    }

    public void move(double offsetX, double offsetY) {
        x += offsetX;
        y += offsetY;
    }

    public boolean tryMove(double offsetX, double offsetY) {
        if (!canMove(offsetX, offsetY)) {
            return false;
        }
        move(offsetX, offsetY);
        return true;
    }
}
