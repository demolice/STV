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
import javafx.util.Duration;

public class MainFormController implements Initializable {

    private final double monsterStep = 0.01;

    private final double stepX = 0.05;
    private final double stepY = 0.05;

    private final MazeMap mazeMap = new MazeMap(20, 20);

    private Player player;

    private final ArrayList<Monster> monsters = new ArrayList<Monster>();

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

    private boolean isKeyPressed(KeyCode code) {
        return keyCodes.contains(code);
    }

    private void movePlayer() {
        double offsetX = 0;
        double offsetY = 0;

        if (keyCodes.isEmpty()) {
            return;
        }

        if (isKeyPressed(KeyCode.UP)) {
            offsetY = -stepY;
        }
        if (isKeyPressed(KeyCode.DOWN)) {
            offsetY = +stepY;
        }
        if (isKeyPressed(KeyCode.LEFT)) {
            offsetX = -stepX;
        }
        if (isKeyPressed(KeyCode.RIGHT)) {
            offsetX = +stepX;
        }

        if (!player.canMove(offsetX, offsetY)) {
            return;
        }

        player.move(offsetX, offsetY);
    }

    private void moveMonsters() {
        for (Monster monster : monsters) {
            if (!monster.tryMoveToTarget(monsterStep)) {
                monster.randomizeTarget();
            }
        }
    }

    private double getCellWidth() {
        return canvasMap.getWidth() / mazeMap.getWidth();
    }

    private double getCellHeight() {
        return canvasMap.getHeight() / mazeMap.getHeight();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mazeMap.generate();

        player = new Player(mazeMap);
        player.move(0.5, 0.5);

        for (int index = 0; index < 1; index++) {
            double offsetX = Math.floor(Math.random() * mazeMap.getWidth()) + 0.5;
            double offsetY = Math.floor(Math.random() * mazeMap.getHeight()) + 0.5;

            Monster monster = new Monster(mazeMap);
            monster.move(offsetX, offsetY);
            monster.randomizeTarget();
            monsters.add(monster);
        }

        EventHandler<ActionEvent> timingHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                movePlayer();
                moveMonsters();
                drawMap();
            }
        };

        KeyFrame keyFrame = new KeyFrame(Duration.millis(10), timingHandler);
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        drawMap();
    }

    private void drawMap() {
        GraphicsContext gc = canvasMap.getGraphicsContext2D();

        gc.clearRect(0, 0, canvasMap.getWidth(), canvasMap.getHeight());

        for (int row = 0; row < mazeMap.getHeight(); row++) {
            for (int column = 0; column < mazeMap.getWidth(); column++) {
                double cellX = column * getCellWidth();
                double cellY = row * getCellHeight();

                MazeCell cell = mazeMap.findCell(column, row);

                if (cell == null) {
                    gc.fillRect(cellX, cellY, getCellWidth(), getCellHeight());
                    continue;
                }

                if (cell.getNorth() == null) {
                    gc.strokeLine(cellX, cellY, cellX + getCellWidth(), cellY);
                }
                if (cell.getSouth() == null) {
                    gc.strokeLine(cellX, cellY + getCellHeight(), cellX + getCellWidth(), cellY + getCellHeight());
                }
                if (cell.getWest() == null) {
                    gc.strokeLine(cellX, cellY, cellX, cellY + getCellHeight());
                }
                if (cell.getEast() == null) {
                    gc.strokeLine(cellX + getCellWidth(), cellY, cellX + getCellWidth(), cellY + getCellHeight());
                }
            }
        }

        player.draw(gc);

        for (Monster monster : monsters) {
            monster.draw(gc);
        }
    }
}
