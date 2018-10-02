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
        return scanner.nextLine();
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

        ArrayList<Integer> numbers = new ArrayList();
        ArrayList<Character> operators = new ArrayList();
        
        parseExpressionText(expressionText, numbers, operators);

        int result = evaluateExpressionSet(numbers, operators);

        return result;
    }

    private static int evaluateExpressionSet(ArrayList<Integer> numbers, ArrayList<Character> operators) {
        int result = 0;
        for (int index = 0; index < numbers.size(); index++) {
            if (index == 0) {
                result = numbers.get(index);
            } else {
                char operator = operators.get(index - 1);
                int operand = numbers.get(index);

                switch (operator) {
                    case '+':
                        result += operand;
                        break;
                    case '-':
                        result -= operand;
                        break;
                    case '*':
                        result *= operand;
                        break;
                    case '/':
                        result /= operand;
                        break;
                }
            }
        }
        return result;
    }

    private static void parseExpressionText(String expressionText, ArrayList<Integer> numbers, ArrayList<Character> operators) throws NumberFormatException {
        String numberText = "";
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
                System.out.println("Zadanný znak \"" + symbol + "\" není valdiní,"
                        + "zkus to prosím znovu.");
            }
        }
        
        int number = Integer.valueOf(numberText);
        numbers.add(number);
    }
}
