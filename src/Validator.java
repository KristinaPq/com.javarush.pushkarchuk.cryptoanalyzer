import java.util.Scanner;

public class Validator {
    public int getValidIntegerInput(Scanner scanner, String message, int min, int max) {
        int input;
        while (true) {
            System.out.print(message);
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.println("Пожалуйста, введите число в диапазоне от " + min + " до " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Пожалуйста, введите целое число.");
            }
        }
    }
}