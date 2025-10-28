import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
public class Cipher {
    //только шифрование и расшифровка
    private Menu menu;
    private Validator validator;

    public Cipher(Menu menu, Validator validator) {
        this.menu = menu;
        this.validator = validator;
    }

    public String encrypt(String text, int shift) {
        int correctEncryptKey = validator.isValidKey(shift);
        int alphabetSize = menu.ALPHABET.length;
        int wordSize = text.length();

        char[] secretWord = new char[wordSize];

        for (int i = 0; i < wordSize; i++) {
            char currentChar = text.charAt(i);
            int indexInAlphabet = -1;

            // Поиск индекса символа в алфавите
            for (int j = 0; j < alphabetSize; j++) {
                if (menu.ALPHABET[j] == currentChar) {
                    indexInAlphabet = j;
                    break;
                }
            }

            if (indexInAlphabet == -1) {
                // Символ не найден в алфавите, копируем без изменений
                secretWord[i] = currentChar;
            } else {
                // Сдвиг с учетом цикличности алфавита
                int shiftedIndex = (indexInAlphabet + correctEncryptKey) % alphabetSize;
                secretWord[i] = menu.ALPHABET[shiftedIndex];
            }
        }

        return new String(secretWord);
    }

    public String decrypt(String encryptedText, int shift) {
        int correctDecryptKey = validator.isValidKey(shift);
        int alphabetSize = menu.ALPHABET.length;
        int textLength = encryptedText.length();

        char[] decryptedWord = new char[textLength];

        for (int i = 0; i < textLength; i++) {
            char currentChar = encryptedText.charAt(i);
            int indexInAlphabet = -1;

            // Поиск символа в алфавите
            for (int j = 0; j < alphabetSize; j++) {
                if (menu.ALPHABET[j] == currentChar) {
                    indexInAlphabet = j;
                    break;
                }
            }

            if (indexInAlphabet == -1) {
                // Символ не найден, копируем без изменений
                decryptedWord[i] = currentChar;
            } else {
                // Обратный сдвиг с учетом цикличности (прибавляем размер алфавита, чтобы избежать отрицательных индексов)
                int shiftedIndex = (indexInAlphabet - correctDecryptKey + alphabetSize) % alphabetSize;
                decryptedWord[i] = menu.ALPHABET[shiftedIndex];
            }
        }

        return new String(decryptedWord);
    }


    // Вспомогательные методы: validateInput(), createAlphabet(), shiftCharacter(), readFile(), writeFile()

}