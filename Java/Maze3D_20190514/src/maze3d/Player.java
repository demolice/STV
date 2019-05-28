package maze3d;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Player extends Actor {

    @Override
    protected double getAvatarWidth() {
        return 0.25;
    }

    @Override
    protected double getAvatarHeight() {
        return 0.25;
    }

    public Player(MazeMap mazeMap) {
        super(mazeMap);
    }

    @Override
    public void draw(GraphicsContext gc) {
        Canvas canvas = gc.getCanvas();
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();

        MazeMap mazeMap = getMazeMap();
        double mazeWidth = mazeMap.getWidth();
        double mazeHeight = mazeMap.getHeight();

        double cellWidth = canvasWidth / mazeWidth;
        double cellHeight = canvasHeight / mazeHeight;

        double avatarWidth = getAvatarWidth();
        double avatarHeight = getAvatarHeight();

        double x = getX();
        double y = getY();

        double avatarX = (x - (avatarWidth / 2)) * cellWidth;
        double avatarY = (y - (avatarHeight / 2)) * cellHeight;

        gc.setFill(Color.BLUE);
        gc.fillRect(avatarX, avatarY, avatarWidth * cellWidth, avatarHeight * cellHeight);
    }
}
