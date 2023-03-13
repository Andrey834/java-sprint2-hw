import java.util.ArrayList;
import java.util.HashMap;

public class Compare {

    public static void getCompareReport(ArrayList<MonthlyConstructor> monthReport, ArrayList<YearlyConstructor> dbYear, String month) {
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
            System.out.println("Обнаружено несоответствие отчета за " + month + " месяц.");
        } else {
            System.out.println("Сверка отчетов завершилась. Несоответствий не обнаружено");
        }
    }
}

