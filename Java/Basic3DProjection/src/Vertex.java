/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author daniil.barabashev
 */
public class Vertex {
    private double x;
    private double y;
    private double z;
    
    public Vertex(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public double getViewportX(double viewportDepth, double vertexDepth, double offsetX) {
        return (x + offsetX) * viewportDepth / (z + vertexDepth);
    }
    
    public double getViewportY(double viewportDepth, double vertexDepth, double offsetY) {
        return (y + offsetY) * viewportDepth / (z + vertexDepth);
    }
    
    public void addSin(double x) {
        this.x += Math.sin(x);
        this.y += Math.sin(x);   
    }
    
}
