import java.util.ArrayList;
import java.util.HashMap;

class MonthlyReport {

    public static void getValueMonths(HashMap<String, ArrayList<MonthlyConstructor>> dbMonthsReports) {
        PrintMenu.printTable();
        for (String month : Months.months) {
            int getMaxProfit = 0;
            int getMaxLoss = 0;
            String getItemMaxProfit = null;
            String getItemMaxLoss = null;
            if (dbMonthsReports.get(month) != null) {
                for (MonthlyConstructor monthlyConstructor : dbMonthsReports.get(month)) {
                    if (!monthlyConstructor.isExpense) {
                        int currentProfit = monthlyConstructor.quantity * monthlyConstructor.priceOne;
                        if (getMaxProfit < currentProfit) {
                            getItemMaxProfit = monthlyConstructor.title;
                            getMaxProfit = currentProfit;
                        }
                    } else {
                        int currentLoss = monthlyConstructor.quantity * monthlyConstructor.priceOne;
                        if (getMaxLoss < currentLoss) {
                            getItemMaxLoss = monthlyConstructor.title;
                            getMaxLoss = currentLoss;
                        }
                    }
                }
                System.out.printf("%1s %-7s %1s %-30s %1s %13s %1s %-34s %1s %12s %1s %n", "* ", month, " | ", getItemMaxProfit, " | ", getMaxProfit, " * ", getItemMaxLoss, " | ", getMaxLoss, " *");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            }
        }
    }
}
