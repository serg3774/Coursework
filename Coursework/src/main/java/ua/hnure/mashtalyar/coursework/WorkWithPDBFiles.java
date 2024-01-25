package ua.hnure.mashtalyar.coursework;

import java.io.*;
import java.util.Scanner;

public class WorkWithPDBFiles {

    /**Метод призначений для читання та виведення вмісту файлу PDB у вигляді шістнадцяткового коду з вказаного шляху.*/
    public static void readPDBFile(String pdbFilePath){
        try (FileInputStream fileInputStream = new FileInputStream(pdbFilePath)) {
            byte[] buffer = new byte[4096]; // Створення буфера для зчитування файлу
            int bytesRead;
            int lineCount = 0;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {  // Цикл читання та виведення вмісту
                System.out.print(String.format("%04X   ", lineCount)); // Виведення адреси рядка у шістнадцятковому форматі
                for (int i = 0; i < bytesRead; i++) { // Виведення байтів у шістнадцятковому форматі та формування блоків по 16 байт
                    System.out.print(String.format("%02X ", buffer[i]));
                    if ((i + 1) % 16 == 0) {   // Додатковий пробіл після кожних 16 байт
                        System.out.print("  ");
                    }
                }
                System.out.println(); // Перехід на новий рядок
                lineCount += bytesRead;
            }
        } catch (IOException e) {
            e.printStackTrace();  // Обробка винятку в разі помилки читання файлу
            System.out.println("Помилка прочитання файлу. Можливо, файл не існує або не є файлом PDB.");
        }
    }


    /** метод взаємодіє з користувачем, отримуючи шлях до файлу PDB від користувача за допомогою Scanner,
     *  а потім викликає метод readPDBFile для читання та виведення вмісту файлу.*/
    public static void readPDBFileByPath(Scanner scanner){
        System.out.print("Введіть шлях до файлу PDB: ");
        String pdbFilePath = scanner.nextLine();// Отримання введення користувача (шлях до файлу PDB)
        readPDBFile(pdbFilePath);// Виклик методу для читання та виведення вмісту файлу PDB
    }

    /** метод використовує рекурсивний підхід для пошуку файлів з розширенням ".pdb" у вказаній директорії та її піддиректоріях.
     * Знайдені шляхи до файлів виводяться на консоль.*/
    public static void findPDBFiles(File directory){
        // Отримуемо список файлів в поточній директорії
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // Рекурсивний визов для піддиректорії
                    findPDBFiles(file);
                } else {
                    // Перевіряемо, чи має файл розширення PDB
                    if (file.getName().toLowerCase().endsWith(".pdb")) {
                        //Виводимо шлях до файлу
                        System.out.println(file.getAbsolutePath());
                    }
                }
            }
        }
    }

    /** метод взаємодіє з користувачем, отримуючи шлях до директорії від користувача за допомогою Scanner, перевіряє існування та тип директорії,
     * а потім викликає метод findPDBFiles для пошуку файлів з розширенням ".pdb" у вказаній директорії.*/
   public static void findPDBFilesByPath(Scanner scanner){
       System.out.print("Введіть шлях до вашої дерикторії: ");
       String directoryPath = scanner.next();
       scanner.nextLine();
       File directory = new File(directoryPath);

       if (directory.exists() && directory.isDirectory()) {
           findPDBFiles(directory);
       } else {
           System.out.println("Така директорія не існує.");
       }
   }

    /**метод взаємодіє з користувачем, отримуючи шляхи до файлу PDB та файлу блокнота від користувача за допомогою Scanner.
     * Після отримання шляхів відбувається читання вмісту з файлу PDB, а потім запис цього вмісту у файл блокнота.*/
   public static void copyFromPDBToTxt(){
       Scanner scanner = new Scanner(System.in);
       System.out.print("Введіть шлях до файлу PDB: ");
       String pdbFilePath = scanner.nextLine(); // Введення шляху до файлу PDB
       System.out.print("Введіть шлях до файлу блокнота для копіювання: ");
       String notepadFilePath = scanner.nextLine(); // Введення шляху до файлу блокнота

       try {
           byte[] pdbContent = readFromFile(pdbFilePath); // Читання вмісту з файлу PDB
           writeToFile(notepadFilePath, pdbContent);  // Запис вмісту в файл блокнота
           System.out.println("Інформація успішно скопійована з PDB файлу до блокноту.");
       } catch (IOException e) { // Обробка винятку в разі помилки читання або запису
           System.err.println("Виникла помилка: " + e.getMessage());
       }
   }

    /**Метод відповідає за читання вмісту файлу та повертає його у вигляді масиву байтів.*/
    private static byte[] readFromFile(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] content = new byte[(int) fis.available()]; // Створення масиву байтів для збереження вмісту файлу
            fis.read(content);// Читання вмісту файлу та збереження його у масив байтів
            return content; // Повернення масиву байтів
        }
    }

    /**Метод відповідає за запис вмісту у файл з використанням переданого шляху та масиву байтів.*/
    private static void writeToFile(String filePath, byte[] content) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(content); // Запис вмісту масиву байтів у файл
        }
    }
}
