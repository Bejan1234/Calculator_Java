import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> istoric = new ArrayList<>();

        System.out.println("=== Calculator Java Avansat ===");

        while (true) {
            System.out.print("\nAlege operația (+, -, *, /, ^, %) sau 'h' (istoric), 'x' (ieșire): ");
            String optiune = scanner.next();

            if (optiune.equalsIgnoreCase("x")) {
                System.out.println("La revedere!");
                break;
            }

            if (optiune.equalsIgnoreCase("h")) {
                afiseazaIstoric(istoric);
                continue;
            }

            char operator = optiune.charAt(0);

            double num1 = citesteNumar(scanner, "Introdu primul număr: ");
            double num2 = citesteNumar(scanner, "Introdu al doilea număr: ");

            try {
                double rezultat = calculeaza(num1, num2, operator);
                String intrareIstoric = String.format("%.2f %c %.2f = %.2f", num1, operator, num2, rezultat);
                istoric.add(intrareIstoric);
                System.out.println("Rezultat: " + intrareIstoric);
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println("Eroare: " + e.getMessage());
            }
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
            case '%' -> {
                if (b == 0) {
                    throw new ArithmeticException("Modulo cu zero nu este permis!");
                }
                yield a % b;
            }
            case '^' -> Math.pow(a, b);
            default -> throw new IllegalArgumentException("Operator necunoscut: " + op);
        };
    }

    private static double citesteNumar(Scanner scanner, String mesaj) {
        while (true) {
            System.out.print(mesaj);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else {
                System.out.println("Valoare invalidă! Te rog introdu un număr valid.");
                scanner.next(); // consuma valoarea gresita
            }
        }
    }

    private static void afiseazaIstoric(List<String> istoric) {
        if (istoric.isEmpty()) {
            System.out.println("Istoricul este gol.");
        } else {
            System.out.println("--- Istoric Calcule ---");
            istoric.forEach(System.out::println);
        }
    }
}