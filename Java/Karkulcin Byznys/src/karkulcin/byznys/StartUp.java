/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karkulcin.byznys;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniil.barabashev
 */
public class StartUp {

    static ArrayList<House> houses;
    static House startHouse;
    static long stuff;

    public static void main(String[] args) {
        houses = new ArrayList<>();

        File input = new File("01.in");

        long numberOfHouses;
        long startingPoint = 0;

        Scanner sc;
        try {
            sc = new Scanner(input);
            numberOfHouses = sc.nextLong();
            startingPoint = sc.nextLong();

            for (int i = 0; i < numberOfHouses; i++) {
                houses.add(new House(sc.nextInt(), sc.nextInt()));
            }

            sc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StartUp.class.getName()).log(Level.SEVERE, null, ex);
        }

        stuff = calculateStuff(houses);

        long leftNeighbourIndex = findFirstNeighbour(houses, startingPoint);
        long rightNeighbourIndex = leftNeighbourIndex + 1;

        System.out.println(leftNeighbourIndex + " " + rightNeighbourIndex);

        startHouse = new House(startingPoint, stuff, true);

        calculateMinimalEnergy(leftNeighbourIndex, rightNeighbourIndex);

        long totalEnergy = calculateEnergy();

        generateOutput(totalEnergy);
    }

    private static long calculateStuff(ArrayList<House> houses) {
        long total = 0;

        for (int index = 0; index < houses.size(); index++) {
            total += houses.get(index).getStuff();
        }
        System.out.println("Stuff: " + total);
        return total;
    }

    private static long findFirstNeighbour(ArrayList<House> houses, long startPoint) {

        long index = 0;

        for (int pos = 0; pos < houses.size(); pos++) {
            House h = houses.get(pos);
            if (h.getX() > startPoint) {
                index = pos - 1;

                break;
            }
        }
        System.out.println(index);
        return index;
    }

    private static long calculateEnergy() {
        long total = 0;
        for (int i = 0; i < houses.size(); i++) {
            total += houses.get(i).energy;
        }
        System.out.println("Total: " + total);
        return total;
    }

    private static void generateOutput(long totalEnergy) {
        System.out.println(houses);
        File f = new File("out.txt");
        try (PrintWriter w = new PrintWriter(f)) {
            w.print(totalEnergy);
            w.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StartUp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void calculateMinimalEnergy(long leftNeighbourIndex, long rightNeighbourIndex) {
        int lIndex = (int) leftNeighbourIndex;
        int rIndex = (int) rightNeighbourIndex;
         House pitStop = startHouse;
        System.out.println(lIndex + " " + rIndex);

        for (int index = 0; index < houses.size() -1; index++) {
            
           
            System.out.println("Index: " + index + " stuff: " + stuff + " pitstop x: " + pitStop.getX());
            calculateEnergy();
            
            
            House left = houses.get(lIndex);
            House right = houses.get(rIndex);


            long overalDistnace = Math.abs(left.getX() - right.getX());

            long distanceLeft = Math.abs(pitStop.getX() - left.getX()) * stuff + overalDistnace * (stuff - left.getStuff());
            long distanceRight = Math.abs(pitStop.getX() - right.getX()) * stuff + overalDistnace * (stuff - right.getStuff());
            System.out.println("Left: " + distanceLeft);
            System.out.println("Right: " + distanceRight);
            
            if (distanceLeft < distanceRight) {
                left.energy = Math.abs(pitStop.getX() - left.getX()) * stuff;
                
                if (lIndex != 0) {
                    stuff -= left.getStuff();
                    lIndex--;
                    pitStop = left;
                    
                } else {
                    stuff -= right.getStuff();
                    rIndex++;
                    pitStop = right;              
                }

            } else if (distanceLeft > distanceRight) {
                right.energy = Math.abs(pitStop.getX() - right.getX()) * stuff;
                if (rIndex != houses.size() - 1) {
                    stuff -= right.getStuff();
                    rIndex++;
                    pitStop = right;   
                } else {
                    stuff -= left.getStuff();
                    lIndex--;
                    pitStop = left;             
                }
  
            } 
            else if (left.getStuff() > right.getStuff()) {
                left.energy = Math.abs(pitStop.getX() - left.getX()) * stuff;
                if (lIndex != 0) {
                    stuff -= left.getStuff();
                    lIndex--;
                    pitStop = left;
                } else {
                    stuff -= right.getStuff();
                    rIndex++;
                    pitStop = right;              
                }
       
            } else {
                right.energy = Math.abs(pitStop.getX() - right.getX()) * stuff;
                if (rIndex != houses.size() - 1) {
                    stuff -= right.getStuff();
                    rIndex++;
                    pitStop = right;   
                } else {
                    stuff -= left.getStuff();
                    lIndex--;
                    pitStop = left;             
                }
            }
        }
    }
}

class House {

    private long x;
    private long stuff;
    private long shortestRouteEnergy;
    private boolean isStartingPoint;
    public long energy = 0;

    public House(long x, long stuff) {
        this.x = x;
        this.stuff = stuff;
        this.shortestRouteEnergy = Long.MAX_VALUE;
    }

    public House(long x, long stuff, boolean b) {
        this.x = x;
        this.stuff = stuff;
        System.out.println("Počátšční bod Karkulky je " + x);
        this.isStartingPoint = true;
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

    public boolean isIsStartingPoint() {
        return isStartingPoint;
    }

    public void setStuff(long stuff) {
        this.stuff = stuff;
    }

    public void setIsStartingPoint(boolean isStartingPoint) {
        this.isStartingPoint = isStartingPoint;
    }

    @Override
    public String toString() {
        return "x: " + x + " stuff: " + stuff + " energy: " + energy + "\n";
    }

}
