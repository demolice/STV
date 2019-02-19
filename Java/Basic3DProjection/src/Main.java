/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author daniil.barabashev
 */
public class Main extends Application {

    private double viewPortDepth = 1.0;
    private double objectDepth = 5.0;

    private final double width = 1024;
    private final double height = 768;
    private Canvas canvas = new Canvas(width, height);

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
        
        double scale = 500;
        double scaleX = width / 2;
        double scaleY = height / 2;
        
        ArrayList<Point> points = new ArrayList();

        for (Vertex vertex : vertices) {
            double x = vertex.getViewportX(viewPortDepth, objectDepth);
            double y = vertex.getViewportY(viewPortDepth, objectDepth);
            
            x = x * scale;
            y = y * scale;

            x = x + scaleX;
            y = y + scaleY;
            
            points.add(new Point(x, y));
            
            gc.strokeOval(x, y, 5, 5);
        }
        
        for (int[] index : indices) {
            Point point1 = points.get(index[0]);
            Point point2 = points.get(index[1]);
            
            gc.strokeLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
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
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
