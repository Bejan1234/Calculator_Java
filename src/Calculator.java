import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Calculator Java ===");

        System.out.print("Introdu primul număr: ");
        double num1 = scanner.nextDouble();

        System.out.print("Alege operația (+, -, *, /): ");
        char operator = scanner.next().charAt(0);

        System.out.print("Introdu al doilea număr: ");
        double num2 = scanner.nextDouble();

        try {
            double rezultat = calculeaza(num1, num2, operator);
            System.out.printf("Rezultat: %.2f %c %.2f = %.2f%n", num1, operator, num2, rezultat);
        } catch (IllegalArgumentException | ArithmeticException e) {
            System.out.println("Eroare: " + e.getMessage());
        }

        scanner.close();
    }

    public static double calculeaza(double a, double b, char op) {
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0) {
                    throw new ArithmeticException("Împărțirea la zero nu este permisă!");
                }
                yield a / b;
            }
            default -> throw new IllegalArgumentException("Operator invalid: " + op);
        };
    }
}