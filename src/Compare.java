import java.util.ArrayList;
import java.util.HashMap;

public class Compare {

    LoadReport loadReport = new LoadReport();
    public void comTest(String[] months, HashMap<String, ArrayList<MonthlyConstructor>> dbMonthsReports, ArrayList<YearlyConstructor> dbYear) {
        for (String month : months) {
            if (dbMonthsReports.get(month) != null) {
                 getCompareReport(dbMonthsReports.get(month), dbYear.get(loadReport.year), month);
            }

            System.out.println("Сверка отчетов завершилась. Несоответствий не обнаружено");
        }
    }





    public static boolean getCompareReport(ArrayList<MonthlyConstructor> monthReport, ArrayList<YearlyConstructor> dbYear, String month, boolean compare) {
        int getSumProfit = 0;
        int getSumLoss = 0;
        int currentProfit = 0;
        int currentloss = 0;

        for (MonthlyConstructor monthlyConstructor : monthReport) {
            if (!monthlyConstructor.isExpense) {
                currentProfit += monthlyConstructor.quantity * monthlyConstructor.priceOne;
            } else {
                currentloss += monthlyConstructor.quantity * monthlyConstructor.priceOne;
            }
        }

        for (YearlyConstructor yearlyConstructor : dbYear) {
            if (yearlyConstructor.monthYear.equals(month)) {
                if (yearlyConstructor.isExpenseYear) {
                    getSumLoss += yearlyConstructor.amountYear;
                } else {
                    getSumProfit += yearlyConstructor.amountYear;
                }
            }
        }

        if (getSumProfit != currentProfit && getSumLoss != currentloss) {
            compare = true;
        }
        return compare;
    }
}

