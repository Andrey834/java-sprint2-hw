import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner inputCommand = new Scanner(System.in);
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        ArrayList<MonthlyConstructor> saveMonthReport = new ArrayList<>();
        ArrayList<YearlyConstructor> saveYearReport = new ArrayList<>();
        boolean checkLoadM = false;
        boolean checkLoadY = false;

        while (true) {
            PrintMenu.printMenu(saveMonthReport, saveYearReport);
            String userInput = inputCommand.nextLine();
            if (userInput.equals("1")) {
                monthlyReport.loadFileMonth(saveMonthReport);
                if (!saveMonthReport.isEmpty()) {
                    checkLoadM = true;
                }
            } else if (userInput.equals("2")) {
                yearlyReport.loadFileMonth(saveYearReport);
                if (!saveYearReport.isEmpty()) {
                    checkLoadY = true;
                }
            } else if (userInput.equals("3")) {

            } else if (userInput.equals("4")) {
                PrintMenu.checkLoadMonth(checkLoadM);
            } else if (userInput.equals("5")) {
                PrintMenu.checkLoadYear(checkLoadY);
            } else if (userInput.equals("0")) {
                break;
            } else {
                System.out.println("Ошибка! Такой команды нет!");
            }
        }
    }
}




