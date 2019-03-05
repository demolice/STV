/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author daniil.barabashev
 */
public class Main extends Application {

    private double viewPortDepth = 1.0;
    private double objectDepth = 10.0;

    private final double width = 1024;
    private final double height = 768;
    
    private Canvas canvas = new Canvas(width, height);
    Timeline timeline;
    
    private final double minimalOffset = -5;
    private final double maximalOffset = 5;
    private final double animationStep = 0.01;
    
    private double offsetX = 0;
    private double currentStep = animationStep;

    private final Vertex[] vertices = {
        new Vertex(-1, 1, -1),
        new Vertex(1, 1, -1),
        new Vertex(1, -1, -1),
        new Vertex(-1, -1, -1),
        new Vertex(-1, 1, 1),
        new Vertex(1, 1, 1),
        new Vertex(1, -1, 1),
        new Vertex(-1, -1, 1)
    };

    private int[][] indices = {
        {0, 1},
        {1, 2},
        {2, 3},
        {3, 0},
        {4, 5},
        {5, 6},
        {6, 7},
        {7, 4},
        {0, 4},
        {1, 5},
        {2, 6},
        {3, 7}
    };

    private void drawScene() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        gc.clearRect(0, 0, width, height);

        Color c = Color.hsb(0, 1, 1);
        gc.setLineWidth(2);

        double scale = 500;
        double scaleX = width / 2;
        double scaleY = height / 2;

        ArrayList<Point> points = new ArrayList();

        for (Vertex vertex : vertices) {
            double x = vertex.getViewportX(viewPortDepth, objectDepth, offsetX);
            double y = vertex.getViewportY(viewPortDepth, objectDepth, 0);

            x = x * scale;
            y = y * scale;

            x = x + scaleX;
            y = y + scaleY;

            points.add(new Point(x, y));

            gc.setStroke(Color.BLACK);
            gc.setFill(Color.BLACK);

            int r = 5;
            gc.strokeOval(x - r / 2, y - r / 2, r, r);
            gc.fillOval(x - r / 2, y - r / 2, r, r);
        }

        for (int[] index : indices) {
            Point point1 = points.get(index[0]);
            Point point2 = points.get(index[1]);

            gc.setStroke(c);
            gc.strokeLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
            c = Color.hsb(c.getHue() + 30, 1, 1);
        }

    }
    
    private void animateScene() {
        offsetX += currentStep;
        if (offsetX >= maximalOffset || offsetX <= minimalOffset) {
            currentStep *= -1;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane sp = new StackPane();
        sp.getChildren().add(canvas);

        Scene scene = new Scene(sp, width, height);

        primaryStage.setTitle("Show 3D");
        primaryStage.setScene(scene);
        primaryStage.show();

        drawScene();

        EventHandler<ActionEvent> timeHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                animateScene();
                drawScene();
            }
        };

        timeline = new Timeline(new KeyFrame(Duration.millis(10), timeHandler));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
