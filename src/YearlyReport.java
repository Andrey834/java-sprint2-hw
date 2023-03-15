import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {

    public static void getReportYear(String year, ArrayList<YearlyConstructor> dbYear, HashMap<String, ArrayList<MonthlyConstructor>> dbMonths) {
        int averageProfit = 0;
        int averageLoss = 0;
        int currentAverageP = 0;
        int currentAverageL = 0;
        HashMap<String, Integer> getSumMonths = new HashMap<>();

        for (YearlyConstructor report : dbYear) {
            if (!report.isExpenseYear) {
                int getProfitMonth = report.amountYear;
                getSumMonths.put(report.monthYear, (getSumMonths.getOrDefault(report.monthYear, 0) + getProfitMonth));
                currentAverageP += getProfitMonth;
            } else {
                int getLossMonth = report.amountYear;
                getSumMonths.put(report.monthYear, (getSumMonths.getOrDefault(report.monthYear, 0) - getLossMonth));
                currentAverageL += getLossMonth;
            }
        }

        if (!dbMonths.isEmpty()) {
            averageProfit = currentAverageP / dbMonths.size();
            averageLoss = currentAverageL / dbMonths.size();
        } else { //если не загрузить месячные отчеты = краш
            averageProfit = currentAverageP / Months.months.length;
            averageLoss = currentAverageL / Months.months.length;
        }

        PrintMenu.printTableYear(averageProfit, averageLoss, year);
        for (String month : Months.months) {
            if (getSumMonths.get(month) != null) {
                System.out.printf("%1s %-7s %2s %9s %1s %n", "*", month, "|", getSumMonths.get(month), "*");
                System.out.println("------------------------");
            }
        }
    }
}