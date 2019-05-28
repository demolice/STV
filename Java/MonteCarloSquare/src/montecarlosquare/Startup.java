package montecarlosquare;

public class Startup {

    public static void main(String[] args) {
        double area = calculateArea(1000000);

        System.out.printf("Obsah malého čtverce je: %f\n", area);
    }

    public static double calculateArea(int tries) {
        int count = tries;
        int k1 = 0;
        int k2 = 0;

        while (count > 0) {
            double x = Math.random();
            double y = Math.random();

            if (x >= 0.5 && y >= 0.5) {
                k2++;
            } else {
                k1++;
            }

            count--;
        }

        double n = k1 + k2;

        double area = k2 / n;

        return area;
    }
}
