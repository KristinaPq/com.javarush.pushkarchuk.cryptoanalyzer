import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileManager fileManager = new FileManager();
        Cipher cipher = new Cipher();
        Validator validator = new Validator();
        BruteForce bruteForce = new BruteForce(cipher);

        boolean exit = false;
        while (!exit) {
            System.out.println("Выберите режим работы:");
            System.out.println("1. Шифрование текста");
            System.out.println("2. Расшифровка текста с помощью ключа");
            System.out.println("3. Расшифровка текста с помощью brute force");
            System.out.println("4. Выход");

            int option = validator.getValidIntegerInput(scanner, "Введите номер режима: ", 1, 4);

            switch (option) {
                case 1:
                    processEncryption(scanner, fileManager, cipher);
                    break;
                case 2:
                    processDecryption(scanner, fileManager, cipher);
                    break;
                case 3:
                    processBruteForceDecryption(scanner, fileManager, bruteForce);
                    break;
                case 4:
                    exit = true;
                    System.out.println("Выход из программы.");
                    break;
            }
        }

        scanner.close();
    }

    private static void processEncryption(Scanner scanner, FileManager fileManager, Cipher cipher) {
        String inputFilePath = fileManager.getFilePath(scanner, "Введите путь к входному файлу для шифровки: ");
        String outputFilePath = fileManager.getFilePath(scanner, "Введите путь к выходному файлу: ");
        int shift = fileManager.getValidShift(scanner, "Введите сдвиг (ключ для шифрования): ");
        String encryptedText = cipher.encryptFile(inputFilePath, shift);
        fileManager.writeToFile(outputFilePath, encryptedText);
        System.out.println("Шифрование завершено. Результат сохранен в " + outputFilePath);
    }

    private static void processDecryption(Scanner scanner, FileManager fileManager, Cipher cipher) {
        String inputFilePath = fileManager.getFilePath(scanner, "Введите путь к входному файлу для расшифровки: ");
        String outputFilePath = fileManager.getFilePath(scanner, "Введите путь к выходному файлу: ");
        int shift = fileManager.getValidShift(scanner, "Введите сдвиг (ключ для расшифровки): ");
        String decryptedText = cipher.decryptFile(inputFilePath, shift);
        fileManager.writeToFile(outputFilePath, decryptedText);
        System.out.println("Расшифровка завершена. Результат сохранен в " + outputFilePath);
    }

    private static void processBruteForceDecryption(Scanner scanner, FileManager fileManager, BruteForce bruteForce) {
        String inputFilePath = fileManager.getFilePath(scanner, "Введите путь к входному файлу для расшифровки: ");
        String outputFilePath = fileManager.getFilePath(scanner, "Введите путь к выходному файлу: ");
        String bruteForceResult = bruteForce.performBruteForce(inputFilePath);
        fileManager.writeToFile(outputFilePath, bruteForceResult);
        System.out.println("Брутфорс расшифровка завершена. Результат сохранен в " + outputFilePath);
    }
}