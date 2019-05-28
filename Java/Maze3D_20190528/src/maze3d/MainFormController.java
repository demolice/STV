package maze3d;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class MainFormController implements Initializable
{
  private final MazeMap mazeMap = new MazeMap(20, 20);
  
  @FXML
  private Canvas canvasMap;

  private Timeline timeline;

  private ArrayList<KeyCode> keyCodes = new ArrayList<KeyCode>();

  public void onKeyPressed(KeyEvent event)
  {
    KeyCode code = event.getCode();

    if (!keyCodes.contains(code))
      keyCodes.add(code);
  }
  
  public void onKeyReleased(KeyEvent event)
  {
    KeyCode code = event.getCode();
    
    if (keyCodes.contains(code))
      keyCodes.remove(code);
  }
  
  @Override
  public void initialize(URL url, ResourceBundle rb)
  {
    mazeMap.generate();
    
    EventHandler<ActionEvent> timingHandler = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event)
      {
        mazeMap.movePlayer(keyCodes);
        mazeMap.moveMonsters();
        mazeMap.draw(canvasMap);
      }
    };

    KeyFrame keyFrame = new KeyFrame(Duration.millis(10), timingHandler);
    timeline = new Timeline(keyFrame);
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();

    mazeMap.draw(canvasMap);
  }
}
