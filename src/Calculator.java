import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> istoric = new ArrayList<>();

        System.out.println(CYAN + "=== Calculator Java Avansat ===" + RESET);

        while (true) {
            System.out.print(YELLOW + "\nAlege operația (+, -, *, /, ^, %) sau 'h' (istoric), 'x' (ieșire): " + RESET);
            String optiune = scanner.next();

            if (optiune.equalsIgnoreCase("x")) {
                System.out.println(CYAN + "La revedere!" + RESET);
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
                System.out.println(GREEN + "Rezultat: " + intrareIstoric + RESET);
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println(RED + "Eroare: " + e.getMessage() + RESET);
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
                System.out.println(RED + "Valoare invalidă! Te rog introdu un număr valid." + RESET);
                scanner.next();
            }
        }
    }

    private static void afiseazaIstoric(List<String> istoric) {
        if (istoric.isEmpty()) {
            System.out.println(YELLOW + "Istoricul este gol." + RESET);
        } else {
            System.out.println(PURPLE + "--- Istoric Calcule ---" + RESET);
            istoric.forEach(element -> System.out.println(CYAN + element + RESET));
        }
    }
}