package maze3d;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class MainFormController implements Initializable {

    private MazeMap mazeMap = new MazeMap(7, 4);

    @FXML
    private Canvas canvasMap;

    public void onKeyPressed(KeyEvent e) {
        KeyCode code = e.getCode();
        
        MazeCell cell = mazeMap.getCurrentCell();
        
        switch (code) {
            case UP:
                MazeCell north = cell.getNorth();
                if (north == null) {
                    return;
                }
                mazeMap.setCurrentCell(north);
                break;
            case DOWN:
                MazeCell south = cell.getSouth();
                if (south == null) {
                    return;
                }
                mazeMap.setCurrentCell(south);
                break;
            case LEFT:
                MazeCell west = cell.getWest();
                if (west == null) {
                    return;
                }
                mazeMap.setCurrentCell(west);
                break;
            case RIGHT:
                MazeCell east = cell.getEast();
                if (east == null) {
                    return;
                }
                mazeMap.setCurrentCell(east);
                break;
        }
        
        drawMap();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mazeMap.generate();
        
        mazeMap.setCurrentCell(mazeMap.findCell(0, 0));
        
        drawMap();
    }

    private void drawMap() {
        int mazeWidth = mazeMap.getWidth();
        int mazeHeight = mazeMap.getHeight();

        double canvasWidth = canvasMap.getWidth();
        double canvasHeight = canvasMap.getHeight();

        double cellWidth = canvasWidth / mazeWidth;
        double cellHeight = canvasHeight / mazeHeight;

        GraphicsContext gc = canvasMap.getGraphicsContext2D();
        
        gc.clearRect(0, 0, canvasWidth, canvasHeight);

        gc.setFill(Color.BLACK);

        for (int row = 0; row < mazeMap.getHeight(); row++) {
            for (int column = 0; column < mazeMap.getWidth(); column++) {
                double cellX = column * cellWidth;
                double cellY = row * cellHeight;

                MazeCell cell = mazeMap.findCell(column, row);

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
                
                if (cell == mazeMap.getCurrentCell()) {
                    double avatarWidth = cellWidth / 5;
                    double avatarHeight = cellHeight / 5;
                    double avatarX = cellX + cellWidth / 2 - avatarWidth / 2;
                    double avatarY = cellY + cellHeight / 2 - avatarHeight / 2;
                    
                    gc.strokeRect(avatarX, avatarY, 20, 20);
                    
                    
                }
            }
        }
    }
}
