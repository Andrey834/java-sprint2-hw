import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        Scanner inputCommand = new Scanner(System.in);
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        LoadReport loadReport = new LoadReport();
        HashMap<String, ArrayList<MonthlyConstructor>> dbMonthsReports = new HashMap<>();
        HashMap<String, ArrayList<YearlyConstructor>> dbYearReports = new HashMap<>();
        boolean checkReport = false;
        String errorMonth = null;

        while (true) {
            PrintMenu.printMenu(dbMonthsReports, dbYearReports);
            String userInput = inputCommand.nextLine();
            if (userInput.equals("1")) { //Считать все месячные отчёты
                loadReport.loadFileMonth(dbMonthsReports, months);
                if (dbMonthsReports.isEmpty()) {
                    PrintMenu.checkFile();
                } else {
                    PrintMenu.loadFiles(dbMonthsReports);
                }
            } else if (userInput.equals("2")) { //Считать годовой отчёт
                loadReport.loadFileYears(dbYearReports);
                if (dbYearReports.isEmpty()) {
                    PrintMenu.checkFile();
                } else {
                    PrintMenu.loadFiles(dbYearReports);
                }
            } else if (userInput.equals("3")) { //Сверить отчёты
                if (dbYearReports.isEmpty()) {
                    PrintMenu.checkLoadYear();
                } else if (dbYearReports.size() == 1) {
                    boolean compare = false;
                    for (String month : months) {
                        if (dbMonthsReports.get(month) != null) {
                           compare = Compare.getCompareReport(dbMonthsReports.get(month), dbYearReports.get(loadReport.year), month, compare);
                        }

                        System.out.println("Сверка отчетов завершилась. Несоответствий не обнаружено");
                    }
                } else {
                    PrintMenu.chooseReportYear(dbYearReports);
                    String year = inputCommand.nextLine();
                    if (dbYearReports.get(year) == null) {
                        PrintMenu.errInput();
                    } else {
                        yearlyReport.getReportYear(year, dbYearReports.get(year), months);

                    }
                }
            } else if (userInput.equals("4")) { //Вывести информацию о всех месячных отчётах
                if (dbMonthsReports.isEmpty()) {
                    PrintMenu.checkLoadMonth();
                } else {
                    PrintMenu.printTable();
                    for (String month : months) {
                        if (dbMonthsReports.get(month) != null) {
                            monthlyReport.getValueMonths(dbMonthsReports.get(month), month);
                        }
                    }
                }
            } else if (userInput.equals("5")) { //Вывести информацию о годовом отчёте
                if (dbYearReports.isEmpty()) {
                    PrintMenu.checkLoadYear();
                } else if (dbYearReports.size() == 1) {
                    yearlyReport.getReportYear(loadReport.year, dbYearReports.get(loadReport.year), months);
                } else {
                    PrintMenu.chooseReportYear(dbYearReports);
                    String year = inputCommand.nextLine();
                    if (dbYearReports.get(year) == null) {
                        PrintMenu.errInput();
                    } else {
                        yearlyReport.getReportYear(year, dbYearReports.get(year), months);
                    }
                }
            } else if (userInput.equals("0")) { //Выход
                break;
            } else {
                System.out.println("Ошибка! Такой команды нет!");
            }
        }
    }
}




