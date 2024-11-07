import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FileManager {
    public String getFilePath(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public int getValidShift(Scanner scanner, String message) {
        Validator validator = new Validator();
        return validator.getValidIntegerInput(scanner, message, 1, 25);
    }

    public void writeToFile(String outputFilePath, String content) {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(outputFilePath), StandardCharsets.UTF_8)) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}