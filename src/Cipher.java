import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Cipher {
    private final char[] alphabet = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З',
            'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С',
            'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ',
            'Ы', 'Ь', 'Э', 'Ю', 'Я',
            '.', ',', '«', '»', ':', ';', '!', '?', ' ', '—', '-'};

    public String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (isCyrillicLetter(ch)) {
                result.append(shiftCharacter(ch, shift));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26)); // Инвертируем сдвиг для расшифровки
    }

    public String encryptFile(String inputFilePath, int shift) {
        try {
            List<String> lines = Files.readAllLines(Path.of(inputFilePath), StandardCharsets.UTF_8);
            StringBuilder encryptedContent = new StringBuilder();
            for (String line : lines) {
                encryptedContent.append(encrypt(line, shift)).append(System.lineSeparator());
            }
            return encryptedContent.toString();
        } catch (IOException e) {
            return "Ошибка при шифровании файла: " + e.getMessage();
        }
    }

    public String decryptFile(String inputFilePath, int shift) {
        try {
            List<String> lines = Files.readAllLines(Path.of(inputFilePath), StandardCharsets.UTF_8);
            StringBuilder decryptedContent = new StringBuilder();
            for (String line : lines) {
                decryptedContent.append(decrypt(line, shift)).append(System.lineSeparator());
            }
            return decryptedContent.toString();
        } catch (IOException e) {
            return "Ошибка при расшифровке файла: " + e.getMessage();
        }
    }

    private char shiftCharacter(char ch, int shift) {
        int index = new String(alphabet).indexOf(ch);
        int newIndex = (index + shift) % alphabet.length;
        return alphabet[newIndex];
    }

    private boolean isCyrillicLetter(char ch) {
        return new String(alphabet).indexOf(ch) >= 0;
    }
}