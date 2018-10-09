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

        long stuff = calculateStuff(houses);

        houses.add(new House(startingPoint, stuff, true));

        long leftNeighbourIndex = findFirstNeighbour(houses, startingPoint);
        long rightNeighbourIndex = houses.get(((int) leftNeighbourIndex) + 1).getX();

        calculateMinimalEnergy(leftNeighbourIndex, startingPoint, rightNeighbourIndex);

        long totalEnergy = calculateEnergy(houses);

        generateOutput(totalEnergy);
    }

    private static long calculateStuff(ArrayList<House> houses) {
        long total = 0;

        for (int index = 0; index < houses.size(); index++) {
            total += houses.get(index).getStuff();
        }

        return total;
    }

    private static long findFirstNeighbour(ArrayList<House> houses, long startPoint) {

        long index = 0;

        for (int pos = 0; pos < 10; pos++) {
            House h = houses.get(pos);
            if (h.getX() > startPoint) {
                index = pos - 1;

                break;
            }
        }
        System.out.println(index);
        return index;
    }

    private static long calculateEnergy(ArrayList<House> houses) {
        long total = 0;
        for (int i = 0; i < houses.size(); i++) {
            total += houses.get(i).getStuff();
        }
        return total;
    }

    private static void generateOutput(long totalEnergy) {
        File f = new File("out.txt");
        try {
            PrintWriter w = new PrintWriter(f);
            w.print(totalEnergy);
            w.flush();
            w.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StartUp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void calculateMinimalEnergy(long leftNeighbourIndex, long startingPoint, long rightNeighbourIndex) {
        long leftNeighbour = leftNeighbourIndex;
        long rightNeighbour = rightNeighbourIndex;

        for (int i = 0; i < houses.size(); i++) {
            if (i == 0) {
                calculateEnergySpend(leftNeighbour, startingPoint);
                calculateEnergySpend(rightNeighbour, startingPoint);
            } else {
                
            }

            if (leftNeighbour == 0) {
                break;
            } else {
                leftNeighbour--;
                rightNeighbour++;
            }
        }
    }

    private static void calculateEnergySpend(long leftNeighbourIndex, long startingPoint) {

    }
}

class House {

    private long x;
    private long stuff;
    private long shortestRouteEnergy;
    private boolean isStartingPoint;

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
        return "x: " + x + " stuff: " + stuff + "\n";
    }

}
