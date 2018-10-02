/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karkulcin.byznys;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author daniil.barabashev
 */
public class StartUp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner("in.txt");

        long numberOfHouses = sc.nextLong();
        long startPoint = sc.nextLong();

        ArrayList<House> houses = new ArrayList();
        ArrayList<House> route = new ArrayList();
        route.add(new House(startPoint, 0));

        for (int i = 0; i < numberOfHouses; i++) {
            houses.add(new House(sc.nextInt(), sc.nextInt()));
        }
        
        sc.close();
        
        long stuff = calculateStuff(houses);
        
        for (int i = 0; i < houses.size(); i++) {
            
        }
    }

    private static long calculateStuff(ArrayList<House> houses) {
        long total = 0;
        
        for (int index = 0; index < houses.size(); index++) {
            total += houses.get(index).getStuff();
        }
        
        return total;
    }
}

class House {

    private long x;
    private long stuff;
    private long shortestRouteEnergy;

    public House(long x, long stuff) {
        this.x = x;
        this.stuff = stuff;
        this.shortestRouteEnergy = Long.MAX_VALUE;
    }
    
    public House(long x) {
        this.x = x;
        System.out.println("Počátšční bod Karkulky je " + x);
    }

    public long getX() {
        return x;
    }

    public long getStuff() {
        return stuff;
    }

    public long getShortestRouteEnergy() {
        return shortestRouteEnergy;
    }

    public void setShortestRouteEnergy(long shortestRouteEnergy) {
        this.shortestRouteEnergy = shortestRouteEnergy;
    }
    
    
}
