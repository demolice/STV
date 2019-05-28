package maze3d;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Monster extends Actor
{
  private final double targetArea = 2;
  
  private double targetX;
  private double targetY;
  
  private boolean isNearTarget(double threshold)
  {
    double deltaX = targetX - getX();
    double deltaY = targetY - getY();

    double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    
    return (distance <= threshold);
  }

  @Override
  protected double getAvatarWidth()
  {
    return 0.2;
  }

  @Override
  protected double getAvatarHeight()
  {
    return 0.2;
  }

  public Monster(MazeMap mazeMap)
  {
    super(mazeMap);
  }
  
  public void randomizeTarget()
  {
    double distanceX = getMazeMap().getPlayer().getX() - getX();
    double distanceY = getMazeMap().getPlayer().getY() - getY();
    
    double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
    
    targetX = getX() + (Math.random() - 0.5) * targetArea;
    targetY = getY() + (Math.random() - 0.5) * targetArea;

    System.out.printf("Nová cílová poloha: [%f ; %f]\n", targetX, targetY);
  }
  
  public boolean tryMoveToTarget(double step)
  {
    if (isNearTarget(step))
      return false;
    
    double deltaX = targetX - getX();
    double deltaY = targetY - getY();
    
    if (deltaX != 0)
    {
      double k = Math.abs(deltaY / deltaX);
      double dx = step / Math.sqrt(1 + k * k);
      double dy = k * dx;
      
      if (deltaX < 0)
        dx = -dx;
      if (deltaY < 0)
        dy = -dy;
      
      return super.tryMove(dx, dy);
    }
    else
    {
      double dx = 0;
      double dy = (deltaY > 0 ? step : -step);
      return super.tryMove(dx, dy);
    }
  }

  @Override
  public void draw(GraphicsContext gc)
  {
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

    double pointX = (targetX - (avatarWidth / 2)) * cellWidth;
    double pointY = (targetY - (avatarHeight / 2)) * cellHeight;
    
    gc.setFill(Color.BROWN);
    gc.fillRect(avatarX, avatarY, avatarWidth * cellWidth, avatarHeight * cellHeight);

//    gc.setFill(Color.GREEN);
//    gc.fillOval(pointX, pointY, avatarWidth * cellWidth, avatarHeight * cellHeight);
  }
}
