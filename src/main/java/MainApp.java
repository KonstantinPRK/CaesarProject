import java.util.Scanner;

public class MainApp {
    private static final char[] ALPHABET = {
            // Русские строчные буквы
            'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у',
            'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'я',
            // Русские заглавные буквы
            'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У',
            'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Я',

            // Английские строчные буквы
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z',
            // Английские заглавные буквы
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z',

            // Знаки препинания и пробел
            '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '
    };


    private static final String welcomeMessage = "Привет это Шифр Цезаря";
    private static final String operatingModeMessage =
            "Выберите режим работы:" +
            "\n 1. Шифрование текста" +
                    "\n 2. Расшифровка текста" +
                            "\n Для выбора введите число (Пример: 1) или завершите работу (Пример: exit): \n";

    private static final String incorrectMessage = "Введено неверное сообщение, попробуйте еще раз";
    private static final String wrongMessage = "Что-то пошло не так, обратитесь к разработчику или попробуйте еще раз";
    private static final String byeMessage = "Работа программы завершена";

    private static final String encryptMessage = "Выбран режим шифрования";
    private static final String decryptMessage = "Выбран режим расшифровки";

    private static final String offerToInputPath = "введите путь к тексту: ";
    private static final String offerToInputKey = "введите целочисленный ключ шифрования: ";

    private static final String exitWord = "exit";


    public static void main(String[] args) {
        Cipher cipher = new Cipher(ALPHABET);
        FileManager fileManager = new FileManager();
        Validator validator = new Validator();
        print(welcomeMessage);


        try(Scanner scan = new Scanner(System.in)) {
            while (true) {
                // Логика меню пользователя
                print(operatingModeMessage);
                String messageFromUser = scan.nextLine();

                //Логика завершения работы
                if (exitWord.equalsIgnoreCase(messageFromUser)) {
                    // 0. Выход
                    break;
                }

                // Логика для выбора режима работы, вызов соответствующих методов
                switch (messageFromUser) {
                    // 1. Шифрование
                    case "1":
                        //получение пути с сообщением
                        print(encryptMessage);
                        print(offerToInputPath);
                        String pathFromUser = scan.nextLine();
                        String messageFromFile = fileManager.readFile(pathFromUser);

                        //получение ключа шифрования
                        print(encryptMessage);
                        print(offerToInputKey);
                        int intKey = scan.nextInt();

                        //шифрование
                        String cipherStringMessage = cipher.encrypt(messageFromFile, intKey);

                        //запись зашифрованного файла
                        fileManager.writeFile(cipherStringMessage, pathFromUser);

                        continue;

                        // 2. Расшифровка с ключом
                    case "2":
                        //получение пути с сообщением
                        print(decryptMessage);
                        print(offerToInputPath);
                        pathFromUser = scan.nextLine();
                        messageFromFile = fileManager.readFile(pathFromUser);

                        //получение ключа шифрования
                        print(decryptMessage);
                        print(offerToInputKey);
                        intKey = scan.nextInt();

                        //расшифровка
                        String stringMessage = cipher.decrypt(messageFromFile, intKey);

                        //запись расшифрованного файла
                        fileManager.writeFile(stringMessage, pathFromUser);

                        continue;

                    default:
                        print(incorrectMessage);
                }


            }
        } catch (RuntimeException re){
            print(byeMessage);

        } catch (Exception e) {
            print(wrongMessage);
            e.printStackTrace();
            print(byeMessage);
        }


    }

    private static void print(String word){
        System.out.println(word);
    }

}
