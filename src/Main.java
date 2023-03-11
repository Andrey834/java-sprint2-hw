import java.io.IOException;
import java.util.*;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
        Scanner inputCommand = new Scanner(System.in);


        while (true) {
            printMenu();
            int userInput = inputCommand.nextInt();
            MonthlyReport monthlyReport = new MonthlyReport();
            //MonthlyReport monthlyReport = new MonthlyReport();


            if (userInput == 1) {
                monthlyReport.loadFileMonth();
            } else if (userInput == 2) {
                //read.loadFileYear();
            } else if (userInput == 3) {
                monthlyReport.getMaxProfitM();

            } else if (userInput == 4) {

            } else if (userInput == 5) {

            } else if (userInput == 6) {
                break;
            } else {
                System.out.println("Ошибка! Такой команды нет!");
            }
        }
    }

    static void printMenu() {
        Date date = new Date();

        System.out.println("*************************************************");
        System.out.println("|              Бухгалтерия v0.01b               |");
        System.out.println("|        " + date + "          |");
        System.out.println("|***********************************************|");
        System.out.println("| 1. Считать все месячные отчёты                |");
        System.out.println("| 2. Считать годовой отчёт                      |");
        System.out.println("| 3. Сверить отчёты                             |");
        System.out.println("| 4. Вывести информацию о всех месячных отчётах |");
        System.out.println("| 5. Вывести информацию о годовом отчёте        |");
        System.out.println("|===============================================|");
        System.out.println("| 0. Выход                                      |");
        System.out.println("+================================================");
        System.out.print("Ввод команды: ");
    }
}




