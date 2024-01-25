package ua.hnure.mashtalyar.coursework;

import java.io.File;

import static ua.hnure.mashtalyar.coursework.Functional.*;
import static ua.hnure.mashtalyar.coursework.WorkWithPDBFiles.*;

public class Menu {

    /**Цей метод визначає меню за допомогою оператора switch для обробки введеного числа. Він викликає різні методи в залежності від введеного числа.*/
    public static void createMenu(int inputNumber) {
            switch (inputNumber){
                case 1:;
                    findPDBFiles(new File("C:"));
                break;

                case 2:
                    findPDBFiles(new File("D:"));
                    break;

                case 3:
                    findPDBFilesByPath(scanner);
                    break;

                case 4:
                    readPDBFileByPath(scanner2);
                    break;

                case 5:
                    copyFromPDBToTxt();
                    break;

                case 6:
                    takeInfoAboutPDBFile();
                break;

                case 7:
                    takeIndoAboutProgramm();
                break;

                case 0:
                    cancelTheProgram();
                break;

                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
    }
}



