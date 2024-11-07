import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BruteForce {
    private final Cipher cipher;

    public BruteForce(Cipher cipher) {
        this.cipher = cipher;
    }

    public String performBruteForce(String inputFilePath) {
        StringBuilder result = new StringBuilder();
        for (int shift = 1; shift < 26; shift++) {
            result.append("Сдвиг ").append(shift).append(":\n")
                    .append(cipher.decryptFile(inputFilePath, shift)).append("\n\n");
        }
        return result.toString();
    }
}