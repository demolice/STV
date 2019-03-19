/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze3d;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 *
 * @author daniil.barabashev
 */
public class MianFormController implements Initializable {

    private MazeMap mazeMap = new MazeMap(8, 8);
    
    @FXML
    private Canvas canvasMap;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int mazeWidth = mazeMap.getWidth();
        int mazeHeight = mazeMap.getHeight();
        
        double canvasWidth = canvasMap.getWidth();
        double canvasHeight = canvasMap.getHeight();

        double cellWidth = canvasWidth / mazeWidth;
        double cellHeight = canvasHeight / mazeHeight;

        GraphicsContext gc = canvasMap.getGraphicsContext2D();
        
        gc.setFill(Color.BROWN);

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
            }
        }
    }

}
