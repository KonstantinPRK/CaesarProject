public class Validator {
    private static final String exitWord = "exit";

    public boolean isValidKey(int key, char[] alphabet) {
        // Проверка ключа
        return false;
    }

    public boolean isFileExists(String filePath) {
        isExit(filePath);
        // Проверка существования файла
        return false;
    }

    public void isExit(String word){
        if (exitWord.equalsIgnoreCase(word)) {
            // 0. Выход
            throw new RuntimeException();
        }
    }
}