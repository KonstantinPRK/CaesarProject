public class FileManager {
    private static final String exitWord = "exit";

    public String readFile(String filePath) {
        isExit(filePath);
        // Логика чтения файла

        return "text";
    }
    public void writeFile(String content, String filePath) {
        isExit(content);
        isExit(filePath);
        // Логика записи файла

    }

    public void isExit(String word){
        if (exitWord.equalsIgnoreCase(word)) {
            // 0. Выход
            throw new RuntimeException();
        }
    }
}