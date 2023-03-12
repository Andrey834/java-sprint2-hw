import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class PrintMenu {

    protected static void printMenu(HashMap<String, ArrayList<MonthlyConstructor>> dbMonthReports, ArrayList<YearlyConstructor> dbY) {

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
        if (dbY.isEmpty()) {
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

    protected static void checkLoadYear(boolean checkLoadY) {
        if (!checkLoadY) {
            System.err.println("Отсутствуют данные для обработки! Считайте годовой отчет.");
        }
    }
}


