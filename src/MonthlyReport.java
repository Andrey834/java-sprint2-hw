import java.util.ArrayList;

class MonthlyReport {
    public String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

    public void getValueMonths(ArrayList<MonthlyConstructor> report, String month) {
        int getMaxProfit = 0;
        int getMaxLoss = 0;
        String getItemMaxProfit = null;
        String getItemMaxLoss = null;

        for (MonthlyConstructor monthlyConstructor : report) {
            if (!monthlyConstructor.isExpense) {
                int currentProfit = monthlyConstructor.quantity * monthlyConstructor.priceOne;
                if (getMaxProfit < currentProfit) {
                    getItemMaxProfit = monthlyConstructor.title;
                    getMaxProfit = currentProfit;
                }
            }
        }

        for (MonthlyConstructor monthlyConstructor : report) {
            if (monthlyConstructor.isExpense) {
                int currentProfit = monthlyConstructor.quantity * monthlyConstructor.priceOne;
                if (getMaxLoss < currentProfit) {
                    getItemMaxLoss = monthlyConstructor.title;
                    getMaxLoss = currentProfit;
                }
            }
        }

        System.out.printf("%1s %-7s %1s %-30s %1s %13s %1s %-34s %1s %12s %1s %n", "* ", month, " | ", getItemMaxProfit, " | ", getMaxProfit, " * ", getItemMaxLoss, " | ", getMaxLoss, " *");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
    }
}
