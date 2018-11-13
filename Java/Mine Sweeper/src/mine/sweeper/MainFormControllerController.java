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
import javafx.scene.control.Label;
import javax.swing.JPanel;
import mine.sweeper.GameLogic.Game;
import mine.sweeper.GameLogic.Graphics.Control;

/**
 *
 * @author daniil.barabashev
 */
public class MainFormControllerController implements Initializable {

    private Game game = new Game(32, 32, Game.EASY);
    private static Control c;

    @FXML
    private Label label;

    @FXML
    Canvas mainCanvas;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        game.shuffle(20);
        c.clearCanvas();
        game.draw(mainCanvas);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        c = new Control(mainCanvas);
        c.paint();
        game.shuffle(20);
    }

}
