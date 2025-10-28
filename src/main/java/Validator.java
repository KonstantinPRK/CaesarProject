import java.nio.file.Files;
import java.nio.file.Path;

public class Validator {
    //Только проверка входных данных от внешнего мира
    private final Menu menu;

    public Validator(Menu menu){
        this.menu = menu;
    }

    public int isValidKey(int key) {
        // Проверка ключа
        if(key < menu.ALPHABET.length){
            return key;
        } else {
            int correctKey = key % menu.ALPHABET.length;
            return correctKey;
        }
    }


    public void isExit(String word){
        if (menu.EXIT_WORD.equalsIgnoreCase(word)) {
            // 0. Выход
            throw new RuntimeException();
        }
    }

}