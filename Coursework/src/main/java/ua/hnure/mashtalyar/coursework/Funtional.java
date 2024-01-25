package ua.hnure.mashtalyar.coursework;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static ua.hnure.mashtalyar.coursework.Menu.createMenu;

class Functional {
    /**Сканер для роботи з меню*/
    static Scanner scanner = new Scanner(System.in);

    /**Сканер для роботи з PDB файлами*/
    static Scanner scanner2 = new Scanner(System.in);

    /**Константи зы шляхами до текстових файлыв для подальшого виклику в ынших методах*/
    private final static String FILE_PATH_TO_FILE_WITH_INFO_ABOUT_PDB_FILE="src/main/java/ua/hnure/mashtalyar/coursework/textFiles/InfoAboutPDBFiles.txt";
    private final static String FILE_PATH_TO_FILE_WITH_INFO_ABOUT_PROGRAMM="src/main/java/ua/hnure/mashtalyar/coursework/textFiles/AboutProgramm.txt";
    private final static String FILE_PATH_TO_FILE_WITH_INSTRUCTION="src/main/java/ua/hnure/mashtalyar/coursework/textFiles/Instruction.txt";
    private final static String FILE_PATH_TO_FILE_WITH_START_Message="src/main/java/ua/hnure/mashtalyar/coursework/textFiles/StartMessage.txt";

    /**Метод виводу інформації про PDB файли*/
    public static void takeInfoAboutPDBFile(){
        outPutInfo(FILE_PATH_TO_FILE_WITH_INFO_ABOUT_PDB_FILE);
    }

    /**Метод виводу інформації меню*/
    public static void takeInstruction(){
        outPutInfo(FILE_PATH_TO_FILE_WITH_INSTRUCTION);
    }

    /**Метод виводу інформації про програму та студента*/
    public static void takeIndoAboutProgramm(){
        outPutInfo(FILE_PATH_TO_FILE_WITH_INFO_ABOUT_PROGRAMM);
    }

    /**Метод виводу стартового повідомлення*/
    public static void startMessage(){
        outPutInfo(FILE_PATH_TO_FILE_WITH_START_Message);
    }

    /**Метод зчитуе та виводить вміст текстових файлів за данним шляхом*/
    private static void outPutInfo(String path){
        try {
            File file = new File(path);// Створення об'єкта File на основі заданого шляху
            Scanner scanner = new Scanner(file); // Ініціалізація Scanner для зчитування файлу
            while (scanner.hasNextLine()) { // Построкове зчитування та виведення вмісту
                String line = scanner.nextLine();
                System.out.println(line);
            }
            System.out.println("");            // Виведення порожнього рядка для розділення виводу
            scanner.close(); // Закриття Scanner для уникнення витоку ресурсів
        } catch (FileNotFoundException e) {
            System.err.println("Помилка: Файл не знайдено."); // Обробка винятку, якщо файл не знайдено
        }
    }

    /**Метод виводу  повідомлення перед закінченням роботи*/
    public static  void cancelTheProgram(){
        System.out.println("Дякую, що скористались програмию, допобачення!");
        System.exit(0);
    }

    /**Метод відповідає за отримання та перевірку коректності введених даних від користувача*/
    private static int inputData(Scanner scanner) {
        int result = 0; //Ініціалізація зміної результату
        boolean validInput = false; //Ініціалізація флагу для перевірки коректності вводу

        while (!validInput) {
            try {
                System.out.print("Виберіть наступний крок: "); // Виведення повідомлення для користувача та отримання введеного значення
                result = scanner.nextInt();
                validInput = true;   // Установка флагу validInput в true, якщо введення є коректним
            } catch (InputMismatchException e) {
                System.out.println("Помилка! Введено невірне знчення. Спробуйте ще раз."); // Обробка винятку, якщо введене значення не є цілим числом
                scanner.nextLine(); // Очищення буфера введення для уникнення циклу введення невірного значення
            }
        }
        return result; // Повернення коректного результату
    }

    /**Метод відповідає за запит користувача щодо бажання повернутися в меню*/
    private static boolean confirmNextStep() {
        System.out.println("");//Вівід пустого рядка для відокремлення повідослення від виводу попередньої інформації
        System.out.print("Чи хочете ви повернутися в меню? (Введіть  'yes' для продовження): "); //Вівід пропозиції користувачу
        String input = scanner.next();  // Зчитування введення користувача
        return input.equalsIgnoreCase("yes"); // Порівняння введення (ігноруючи регістр) з рядком "yes" і повернення результату
    }

    /**Метод представляє основний цикл роботи програми, який дозволяє користувачеві взаємодіяти з меню,
     * вибирати функції та повертатися в меню за бажанням або завершити роботу.*/
    public static void startWork() {
        int chooseFunction;
        startMessage();  // Виведення початкового повідомлення
        System.out.println("Вас вітає меню!");

        while (true) {  // Безкінечний цикл для взаємодії з меню
            System.out.println("");
            takeInstruction();  // Виведення інструкції для користувача
            chooseFunction = inputData(scanner);   // Отримання вибору користувача
            createMenu(chooseFunction);  // Виклик методу для обробки вибраної функції

            while(!confirmNextStep()){// Цикл для підтвердження бажання користувача продовжити взаємодію
                confirmNextStep();
            }
        }
    }

}
