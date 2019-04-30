package maze3d;

public class MazeCell
{
  private int x;
  private int y;

  private MazeCell north;
  private MazeCell south;
  private MazeCell west;
  private MazeCell east;
  
  public MazeCell(int x, int y)
  {
    this.x = x;
    this.y = y;
  }

  public MazeCell getNorth()
  {
    return north;
  }

  public void setNorth(MazeCell north)
  {
    this.north = north;
  }

  public MazeCell getSouth()
  {
    return south;
  }

  public void setSouth(MazeCell south)
  {
    this.south = south;
  }

  public MazeCell getWest()
  {
    return west;
  }

  public void setWest(MazeCell west)
  {
    this.west = west;
  }

  public MazeCell getEast()
  {
    return east;
  }

  public void setEast(MazeCell east)
  {
    this.east = east;
  }

  public int getX()
  {
    return x;
  }

  public void setX(int x)
  {
    this.x = x;
  }

  public int getY()
  {
    return y;
  }

  public void setY(int y)
  {
    this.y = y;
  }

  public static void joinNorthSouth(MazeCell northCell, MazeCell southCell)
  {
    northCell.setSouth(southCell);
    southCell.setNorth(northCell);
  }

  public static void joinEastWest(MazeCell eastCell, MazeCell westCell)
  {
    eastCell.setWest(westCell);
    westCell.setEast(eastCell);
  }
}
