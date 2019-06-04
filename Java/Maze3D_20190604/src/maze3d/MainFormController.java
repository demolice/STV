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
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class MainFormController implements Initializable {

    private final MazeMap mazeMap = new MazeMap(20, 20);

    @FXML
    private Canvas canvasMap;

    private Timeline timeline;

    private ArrayList<KeyCode> keyCodes = new ArrayList<KeyCode>();

    public void onKeyPressed(KeyEvent event) {
        KeyCode code = event.getCode();

        if (!keyCodes.contains(code)) {
            keyCodes.add(code);
        }
    }

    public void onKeyReleased(KeyEvent event) {
        KeyCode code = event.getCode();

        if (keyCodes.contains(code)) {
            keyCodes.remove(code);
        }
    }

    private void drawNoticeGameOver() {
        GraphicsContext gc = canvasMap.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvasMap.getWidth(), canvasMap.getHeight());
        
        gc.setFill(Color.DARKRED);
        Font font = Font.font("Jokerman", 150);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(font);
        gc.fillText("Game Over!", canvasMap.getWidth() / 2, canvasMap.getHeight() / 2);
        
        
        Font font2 = Font.font("Jokerman", 40);
        gc.setFont(font2);
        gc.fillText("Press SPACE to continue!", canvasMap.getWidth() / 2, canvasMap.getHeight() / 2 + 100);
       
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mazeMap.generate();

        EventHandler<ActionEvent> timingHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                if (mazeMap.isPlayerAlive()) {
                    mazeMap.movePlayer(keyCodes);
                    mazeMap.moveMonsters();
                    mazeMap.draw(canvasMap);
                } else {
                    if (keyCodes.contains(KeyCode.SPACE)) {
                        mazeMap.generate();
                    } else {
                        drawNoticeGameOver();                 
                    }
                }
            }
        };

        KeyFrame keyFrame = new KeyFrame(Duration.millis(10), timingHandler);
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        mazeMap.draw(canvasMap);
    }

}
