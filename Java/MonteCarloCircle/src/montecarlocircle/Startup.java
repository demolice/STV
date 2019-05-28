package montecarlocircle;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Startup {

    public static int hits1 = 0;
    public static int hits2 = 0;

    public static void main(String[] args) {
        int tries = 100_000_000;
        //double pi = calculateArea(tries);
        try {
            double pi = fullCircle(tries);
            System.out.printf("Ludolfovo číslo je: %f\n", pi);
            System.out.printf("Opravdové pí je: %f\n", Math.PI);

        } catch (InterruptedException ex) {
            Logger.getLogger(Startup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static double calculateArea(int tries) {
        int count = tries;
        int k1 = 0;
        int k2 = 0;

        while (count > 0) {
            double x = Math.random();
            double y = Math.random();

            double r = Math.sqrt(x * x + y * y);

            if (r <= 1) {
                k2++;
            } else {
                k1++;
            }

            count--;
        }

        double n = k1 + k2;

        double area = k2 / n * 4;

        return area;
    }

    public static double fullCircle(int tries) throws InterruptedException {
        int threadCount = 8;
        int count = tries;

        ArrayList<HitCalculator> calculators = new ArrayList<>();

        for (int index = 0; index < threadCount; index++) {
            HitCalculator calculator = new HitCalculator(count / threadCount);
            calculators.add(calculator);
        }
        
        long startTime = System.currentTimeMillis();

        for (int index = 0; index < threadCount; index++) {
            HitCalculator calculator = calculators.get(index);
            calculator.start();
        }

        int hits = 0;

        for (int index = 0; index < threadCount; index++) {
            HitCalculator calculator = calculators.get(index);
            calculator.join();
            hits += calculator.getHits();
        }

        double pi = 4 * hits / (double) count;
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        System.out.printf("Proces doběhl za %d ms\n ", duration);
        
        
        return pi;
    }
}
