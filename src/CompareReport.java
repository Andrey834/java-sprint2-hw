import java.util.ArrayList;
import java.util.HashMap;

public class CompareReport {
    HashMap<String, ArrayList<MonthlyConstructor>> dbMonthsReports;
    HashMap<String, ArrayList<YearlyConstructor>> dbYearReports;

    protected static void getComapre(HashMap<String, ArrayList<MonthlyConstructor>> dbMonthsReports, HashMap<String, ArrayList<YearlyConstructor>> dbYearReports, String[] months, String year) {
        int getLossMonthYear = 0;
        int getProfitMonthYear = 0;
        boolean check = false;

        if (dbYearReports.get(year) != null) {
            for (YearlyConstructor yearlyConstructor : dbYearReports.get(year)) {
                if (yearlyConstructor.isExpenseYear) {
                    getLossMonthYear = yearlyConstructor.amountYear;
                }
                if (!yearlyConstructor.isExpenseYear) {
                    getProfitMonthYear = yearlyConstructor.amountYear;
                }
                int currentLoss = 0;
                int currentProfit = 0;
                if (dbMonthsReports.get(yearlyConstructor.monthYear) != null) {
                    for (MonthlyConstructor monthlyConstructor : dbMonthsReports.get(yearlyConstructor.monthYear)) {
                        if (monthlyConstructor.isExpense) {
                            currentLoss = currentLoss + (monthlyConstructor.quantity * monthlyConstructor.priceOne);
                        }
                        if (!monthlyConstructor.isExpense) {
                            currentProfit = currentProfit + (monthlyConstructor.quantity * monthlyConstructor.priceOne);
                        }
                    }
                }
                if (currentLoss != getLossMonthYear & currentProfit != getProfitMonthYear) {
                    check = true;
                }
                getLossMonthYear = 0;
                getProfitMonthYear = 0;
            }
        }
        if (check == false) {
            System.out.println("Сверка отчетов завершилась успешно. Несоответствий не обнаружено.");
        }
    }
}

