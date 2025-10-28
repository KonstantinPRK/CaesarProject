import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FileManager {
    private Validator validator;
    private Scanner scanner;
    private Menu menu;

    public FileManager(Scanner scanner, Validator validator, Menu menu){
        this.scanner = scanner;
        this.validator = validator;
        this.menu = menu;
    }

    public String readFile(String filePath) throws IOException {
        Path path = Path.of(filePath);
        if(!Files.exists(path.getParent())){
            print(menu.INCORRECT_WAY);
            return readFile(nextLine());
        }
        return Files.readString(path, StandardCharsets.UTF_8);
    }

    public void writeFile(String content, String filePath) throws IOException {
        Path path = Path.of(filePath);
        // проверить существование директории
        if(!Files.exists(path.getParent())){
            print(menu.INCORRECT_WAY);
            writeFile(content, nextLine());
            return;
        }

        Files.writeString(path, content, StandardCharsets.UTF_8);
    }


    public void print(String word){
        System.out.println(word);
    }


    public String nextLine(){
        String line = scanner.nextLine();
        validator.isExit(line);
        return line;
    }


    public int nextInt(){
        int number = scanner.nextInt();
        scanner.nextLine(); // очистка перехода строки после nextInt()
        return number;
    }

}