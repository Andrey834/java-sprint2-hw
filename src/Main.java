import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner inputCommand = new Scanner(System.in);
        HashMap<String, ArrayList<MonthlyConstructor>> dbMonthsReports = new HashMap<>();
        HashMap<String, ArrayList<YearlyConstructor>> dbYearReports = new HashMap<>();

        while (true) {
            PrintMenu.printMenu(dbMonthsReports, dbYearReports);
            String userInput = inputCommand.nextLine();
            if (userInput.equals("1")) { //Считать все месячные отчёты
                LoadReport.loadFileMonth(dbMonthsReports);
                if (dbMonthsReports.isEmpty()) {
                    PrintMenu.checkFile();
                } else {
                    PrintMenu.loadFiles(dbMonthsReports);
                }
            } else if (userInput.equals("2")) { //Считать годовой отчёт
                LoadReport.loadFileYears(dbYearReports);
                if (dbYearReports.isEmpty()) {
                    PrintMenu.checkFile();
                } else {
                    PrintMenu.loadFiles(dbYearReports);
                }
            } else if (userInput.equals("3")) { //Сверить отчёты
                if (!dbMonthsReports.isEmpty() && !dbYearReports.isEmpty()) {
                    CompareReport.compare(dbYearReports, dbMonthsReports);
                } else {
                    if (dbMonthsReports.isEmpty()) {
                        PrintMenu.checkLoadMonth();
                    }
                    if (dbYearReports.isEmpty()) {
                        PrintMenu.checkLoadYear();
                    }
                }
            } else if (userInput.equals("4")) { //Вывести информацию о всех месячных отчётах
                if (dbMonthsReports.isEmpty()) {
                    PrintMenu.checkLoadMonth();
                } else {
                    MonthlyReport.getValueMonths(dbMonthsReports);
                }
            } else if (userInput.equals("5")) { //Вывести информацию о годовом отчёте
                if (dbYearReports.isEmpty()) {
                    PrintMenu.checkLoadYear();
                } else {
                    YearlyReport.getReportYear(dbYearReports);
                }
            } else if (userInput.equals("0")) { //Выход
                break;
            } else {
                System.out.println("Ошибка! Такой команды нет!");
            }
        }
    }
}