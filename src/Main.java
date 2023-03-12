import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner inputCommand = new Scanner(System.in);
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        LoadReport loadReport = new LoadReport();
        HashMap<String, ArrayList<MonthlyConstructor>> dbMonthsReports = new HashMap<>();
        HashMap<String, ArrayList<YearlyConstructor>> dbYearReports = new HashMap<>();

        while (true) {
            PrintMenu.printMenu(dbMonthsReports, dbYearReports);
            String userInput = inputCommand.nextLine();
            if (userInput.equals("1")) {
                loadReport.loadFileMonth(dbMonthsReports, monthlyReport.months);
                if (dbMonthsReports.isEmpty()) {
                    PrintMenu.checkFile();
                } else {
                    PrintMenu.loadFiles(dbMonthsReports);
                }
            } else if (userInput.equals("2")) {
                loadReport.loadFileYears(dbYearReports);
                if (dbYearReports.isEmpty()) {
                    PrintMenu.checkFile();
                } else {
                    PrintMenu.loadFiles(dbYearReports);
                }
            } else if (userInput.equals("3")) {
                System.out.println("Работает работает");
            } else if (userInput.equals("4")) {
                if (dbMonthsReports.isEmpty()) {
                    PrintMenu.checkLoadMonth();
                } else {
                    PrintMenu.printTable();
                    for (int i = 0; i < monthlyReport.months.length; i++) {
                        if (dbMonthsReports.get(monthlyReport.months[i]) != null) {
                            monthlyReport.getValueMonths(dbMonthsReports.get(monthlyReport.months[i]), monthlyReport.months[i]);
                        }
                    }
                }
            } else if (userInput.equals("5")) {
                if (dbYearReports.isEmpty()) {
                    PrintMenu.checkLoadYear();
                } else {
                    System.out.println("Выберите год для отчёта:");
                    PrintMenu.loadFiles(dbYearReports);
                    System.out.print("Ввод: ");
                    String year = inputCommand.nextLine();
                    if (dbYearReports.get(year) == null) {
                        PrintMenu.errInput();
                    } else {
                        yearlyReport.getReportYear(year, dbYearReports.get(year));
                    }
                }
            } else if (userInput.equals("0")) {
                break;
            } else {
                System.out.println("Ошибка! Такой команды нет!");
            }
        }
    }
}




