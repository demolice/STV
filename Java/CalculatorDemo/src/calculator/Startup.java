package calculator;

import java.util.ArrayList;
import java.util.Scanner;

public class Startup {

    public static void main(String[] args) {
        String expressionText = readExpressionText();
        printExpressionText(expressionText);
        int result = computeExpression(expressionText);
        printExpressionResult(result);
    }

    private static void printExpressionText(String expressionText) {
        System.out.println("Zadání algebraický výraz: " + expressionText);
    }

    private static String readExpressionText() {
        System.out.print("Zadejte algebraický výraz: ");
        Scanner scanner = new Scanner(System.in);
        String expressionText = scanner.next();
        return expressionText;
    }

    private static void printExpressionResult(int result) {
        System.out.println("Výsledek zadaného výrazu je: " + result);
    }

    private static boolean isDigit(char symbol) {
        return Character.isDigit(symbol);
    }

    private static boolean isOperator(char symbol) {
        switch (symbol) {
            case '+':
            case '-':
            case '*':
            case '/':
                return true;
        }
        return false;
    }

    private static int computeExpression(String expressionText) throws NumberFormatException {
        String numberText = "";
        ArrayList<Integer> numbers = new ArrayList();
        ArrayList<Character> operators = new ArrayList();

       
        for (int index = 0; index < expressionText.length(); index++) {

            char symbol = expressionText.charAt(index);

            if (isDigit(symbol)) {
                numberText += symbol;
            } else if (isOperator(symbol)) {
                int number = Integer.valueOf(numberText);
                numbers.add(number);
                operators.add(symbol);
                numberText = "";
            } else {
                System.out.println("Zadanný znak " + symbol + " není valdiní,"
                        + "zkus to prosím znovu.");
            }
        }     
        int number = Integer.valueOf(numberText);
        numbers.add(number);
        
        int result = 0;
        
        // TODO: copute result
        
        return result;
    }
}
