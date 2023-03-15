import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {

    public static void getReportYear(HashMap<String, ArrayList<YearlyConstructor>> dbYear) {
        int averageProfit = 0;
        int averageLoss = 0;
        int currentAverageP = 0;
        int currentAverageL = 0;
        String year = "";
        HashMap<String, Integer> getSumMonths = new HashMap<>();

        for (String yearReport : dbYear.keySet()) {
            year = yearReport;
            for (YearlyConstructor report : dbYear.get(yearReport)) {
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
            averageProfit = currentAverageP / 12;
            averageLoss = currentAverageL / 12;
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