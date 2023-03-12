import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner inputCommand = new Scanner(System.in);
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        //ArrayList<MonthlyConstructor> saveMonthReport = new ArrayList<>();
        HashMap<String, ArrayList<MonthlyConstructor>> dbMonthReports = new HashMap<>();
        ArrayList<YearlyConstructor> saveYearReport = new ArrayList<>();
        boolean checkLoadM = false;
        boolean checkLoadY = false;

        while (true) {
            PrintMenu.printMenu(dbMonthReports, saveYearReport);
            String userInput = inputCommand.nextLine();
            if (userInput.equals("1")) {
                monthlyReport.loadFileMonth(dbMonthReports);
                System.out.println("Считаны отчеты за:" + dbMonthReports.keySet());
                if (!dbMonthReports.isEmpty()) {
                    checkLoadM = true;
                }
            } else if (userInput.equals("2")) {
                yearlyReport.loadFileMonth(saveYearReport);
                if (!saveYearReport.isEmpty()) {
                    checkLoadY = true;
                }
            } else if (userInput.equals("3")) {

            } else if (userInput.equals("4")) {
                if (dbMonthReports.isEmpty()) {
                PrintMenu.checkLoadMonth();
                } else {
                    for (int i = 0; i < monthlyReport.months.length; i++) {
                        monthlyReport.getMaxValue(dbMonthReports, monthlyReport.months[i]);
                    }
                }
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




