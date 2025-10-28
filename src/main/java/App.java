//Только вызовы методов

import java.util.Scanner;

public class App {
    private Menu menu;
    private Cipher cipher;
    private FileManager fileManager;
    private Validator validator;
    private Scanner scanner;

    public App() {
        scanner = new Scanner(System.in);
        menu = new Menu();
        validator = new Validator(menu);
        cipher = new Cipher(menu, validator);
        fileManager = new FileManager(scanner, validator, menu);

        firstStartApp();
    }

    public void firstStartApp(){
        fileManager.print(menu.WELCOME);

        try {
            app();
        } catch(RuntimeException re){
            fileManager.print(menu.BYE);
        } catch (Exception e){
            fileManager.print(menu.WRONG);
            e.printStackTrace();
            fileManager.print(menu.BYE);
        }
    }

    public void app() throws Exception {
            while (true) {
                String messageFromUser = startNewCycle();

                switch (messageFromUser) {
                    //шифрование текста
                    case "1":
                        cipherActions();
                        continue;

                        //расшифровка текста
                    case "2":
                        decipherActions();
                        continue;

                    default:
                        fileManager.print(menu.INCORRECT);
                }
            }
    }

    //начало нового цикла работы и выбор режима работы
    public String startNewCycle() throws Exception{
        fileManager.print(menu.OPERATING_MODE);
        String messageFromUser = fileManager.nextLine();
        return messageFromUser;
    }

    //каскад вызовов методов шифрования
    public void cipherActions() throws Exception{
        fileManager.print(menu.ENCRYPT);
        fileManager.print(menu.INPUT_PATH);
        String pathFromUser = fileManager.nextLine();
        String messageFromFile = fileManager.readFile(pathFromUser);

        fileManager.print(menu.ENCRYPT);
        fileManager.print(menu.INPUT_KEY);
        int intKey = fileManager.nextInt();

        String cipherStringMessage = cipher.encrypt(messageFromFile, intKey);

        fileManager.print(menu.OUTPUT_PATH);
        String pathToUser = fileManager.nextLine();
        fileManager.writeFile(cipherStringMessage, pathToUser);

        fileManager.print(menu.IS_DONE);
    }

    //каскад вызовов методов расшифровки
    public void decipherActions() throws Exception{
        fileManager.print(menu.DECRYPT);
        fileManager.print(menu.INPUT_PATH);
        String pathFromUser = fileManager.nextLine();
        String messageFromFile = fileManager.readFile(pathFromUser);

        fileManager.print(menu.DECRYPT);
        fileManager.print(menu.INPUT_KEY);
        int intKey = fileManager.nextInt();

        String stringMessage = cipher.decrypt(messageFromFile, intKey);

        fileManager.print(menu.OUTPUT_PATH);
        String pathToUser = fileManager.nextLine();
        fileManager.writeFile(stringMessage, pathToUser);

        fileManager.print(menu.IS_DONE);
    }


}





