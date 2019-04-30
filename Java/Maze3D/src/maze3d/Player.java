package maze3d;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Player extends Actor {

    public Player(MazeMap mazeMap) {
        super(mazeMap);
    }

    @Override
    protected double getAvatarWidth() {
        return 0.25;
    }

    @Override
    protected double getAvatarHeight() {
        return 0.25;
    }

    @Override
    public void draw(GraphicsContext gc) {
        Canvas canvas = gc.getCanvas();
        
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();
        
        double mazeWidth = getMazeMap().getWidth();
        double mazeHeight = getMazeMap().getHeight();
        
        double cellWidth = canvasWidth / mazeWidth;
        double cellHeight = canvasHeight / mazeHeight;
        
        double avatarWidth = getAvatarWidth();
        double avatarHeight = getAvatarHeight();
        
        double avatarX = (getX() - (avatarWidth / 2)) * cellWidth;
        double avatarY = (getY() - (avatarHeight / 2)) * cellHeight;
        
        gc.setFill(Color.BLUE);
        
        gc.fillRect(avatarX, avatarY, avatarWidth * cellWidth, avatarHeight * cellHeight);
    }
}
