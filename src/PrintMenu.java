import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class PrintMenu {

    protected static void printMenu(HashMap<String, ArrayList<MonthlyConstructor>> dbMonthReports, HashMap<String, ArrayList<YearlyConstructor>> dbYearReports) {
        Date date = new Date();
        System.out.println("********************************************************");
        System.out.println("|               java-sprint2-hw v0.01b                 |");
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
        System.out.println("Отсутствуют данные для обработки! Считайте месячные отчеты.");
    }

    protected static void checkLoadYear() {
        System.out.println("Отсутствуют данные для обработки! Считайте годовой отчет.");
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
        System.out.printf("%-1s %-37s %10s %1s %n", "*", "Средний доход за все месяцы в году:", profit, "*");
        System.out.printf("%-1s %-37s %10s %1s %n", "*", "Средний расход за все месяцы в году:", loss, "*");
        System.out.println("====================================================");
        System.out.printf("%1s %-7s %2s %9s %1s %n", "*", "Месяц", "|", "Прибыль", "*");
        System.out.println("========================");
    }

    protected static void checkFile() {
        System.out.println("Не удалось загрузить отчеты!");
        System.out.println("Возможные причины:");
        System.out.println("Отсутствуют файлы для считывания.");
        System.out.println("Не поддерживается данный формат файлов.");
    }

    protected static void loadFiles (HashMap db) {
        System.out.println("Доступны отчеты за:" + db.keySet());
    }
}


