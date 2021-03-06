/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.sweeper;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import mine.sweeper.GameLogic.Game;
import mine.sweeper.GameLogic.Graphics.Control;

/**
 *
 * @author daniil.barabashev
 */
public class MainFormControllerController implements Initializable {

    //javafx scene builder 2
    private final Game game = new Game(10, 10, Game.EASY);
    private static Control c;

    @FXML
    Canvas mainCanvas;

    @FXML
    private Button button;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        c.clearCanvas();
        c.paint();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        c = new Control(mainCanvas);
        c.setPieces(Game.getPieces());
        c.paint();
    }

    @FXML
    private void handleMouseClicked(MouseEvent event) {
        
        if (!event.isSecondaryButtonDown()) {
            game.canvasClick(event.getX() / mainCanvas.getWidth(), event.getY() / mainCanvas.getHeight());
            c.paint();
        }


    }
}
