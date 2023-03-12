import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class PrintMenu {

    protected static void printMenu(HashMap<String, ArrayList<MonthlyConstructor>> dbMonthReports, HashMap<String, ArrayList<YearlyConstructor>> dbYearReports) {

        Date date = new Date();
        System.out.println("********************************************************");
        System.out.println("|                 Бухгалтерия v0.01b                   |");
        System.out.println("|           " + date + "              |");
        System.out.println("|******************************************************|");
        if (dbMonthReports.isEmpty()) {
            System.out.println("| 1. Считать все месячные отчёты  *(Данные не считаны)*|");
        } else {
            System.out.println("| 1. Считать все месячные отчёты                       |");
        }
        if (dbYearReports.isEmpty()) {
            System.out.println("| 2. Считать годовой отчёт        *(Данные не считаны)*|");
        } else {
            System.out.println("| 2. Считать годовой отчёт                             |");
        }
        System.out.println("| 3. Сверить отчёты                                    |");
        System.out.println("| 4. Вывести информацию о всех месячных отчётах        |");
        System.out.println("| 5. Вывести информацию о годовом отчёте               |");
        System.out.println("|======================================================|");
        System.out.println("| 0. Выход                                             |");
        System.out.println("========================================================");
        System.out.print("Ввод команды: ");
    }

    protected static void checkLoadMonth() {
        System.err.println("Отсутствуют данные для обработки! Считайте месячные отчеты.");
    }

    protected static void checkLoadYear() {
        System.err.println("Отсутствуют данные для обработки! Считайте годовой отчет.");
    }

    protected static void printTable() {
        System.out.println("==========================================================================================================================");
        System.out.printf("%1s %-7s %1s %-30s %1s %13s %1s %-34s %1s %12s %1s %n", "* ", "Месяц", " | ", "Самый прибыльный товар:", " | ", "Сумма прибыли", " * ", "Самая большая трата:", " | ", "Сумма траты", " *");
        System.out.println("==========================================================================================================================");
    }

    protected static void printTableYear(int profit, int loss, String year) {
        System.out.println("====================================================");
        System.out.printf("%-1s %s %s %s %32s %n", "*", "Отчет за", year, "год", "*");
        System.out.println("----------------------------------------------------");
        System.out.printf("%-1s %-37s %10s %1s %n", "*", "Средний доход за все месяцы в году:", (profit/12), "*");
        System.out.printf("%-1s %-37s %10s %1s %n", "*", "Средний расход за все месяцы в году:", (loss/12), "*");
        System.out.println("====================================================");
        System.out.printf("%1s %-6s %1s %8s %4s %n", "*", "Месяц", "|", "Прибыль", "*");
        System.out.println("========================");
    }

    protected static void checkFile() {
        System.out.println("В директории отсутствуют файлы для считывания");
    }

    protected static void errInput() {
        System.out.println("Указанный год отсутствует в базе!");
    }

    protected static void loadFiles (HashMap db) {
        System.out.println("Считаны отчеты за:" + db.keySet());
    }
}


