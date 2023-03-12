import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    MonthlyReport monthlyReport = new MonthlyReport();

    public void getReportYear(String year, ArrayList<YearlyConstructor> dbYear) {
        int averageProfit = 0;
        int averageLoss = 0;
        HashMap<String, Integer> getSumMonths = new HashMap<>();

        for (YearlyConstructor report : dbYear) {
            if (!report.isExpenseYear) {
                int getProfitMonth = report.amountYear;
                getSumMonths.put(report.monthYear, (getSumMonths.getOrDefault(report.monthYear, 0) + getProfitMonth));
                averageProfit += getProfitMonth;
            } else {
                int getLossMonth = report.amountYear;
                getSumMonths.put(report.monthYear, (getSumMonths.getOrDefault(report.monthYear, 0) - getLossMonth));
                averageLoss += getLossMonth;
            }
        }

        PrintMenu.printTableYear(averageProfit, averageLoss, year);
        for (String month : monthlyReport.months) {
            if (getSumMonths.get(month) != null) {
                System.out.printf("%1s %4s %2s %9s %1s %n", "* ", month, " | ", getSumMonths.get(month), " *");
                System.out.println("------------------------");
            } else {
                continue;
            }
        }
    }
}
