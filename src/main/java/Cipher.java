import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Cipher {
    private final char[] alphabet;
    private static final String exitWord = "exit";

    public Cipher(char[] alphabet) {
        this.alphabet = alphabet;
    }

    public String encrypt(String text, int shift) {
        isExit(text);
        // Логика шифрования


        return text;
    }

    public String decrypt(String encryptedText, int shift) {
        isExit(encryptedText);
        // Логика расшифровки



        return encryptedText;
    }

    public void isExit(String word){
        if (exitWord.equalsIgnoreCase(word)) {
            // 0. Выход
            throw new RuntimeException();
        }
    }

    // Вспомогательные методы: validateInput(), createAlphabet(), shiftCharacter(), readFile(), writeFile()

}