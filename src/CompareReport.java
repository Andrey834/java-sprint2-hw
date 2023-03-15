import java.util.ArrayList;
import java.util.HashMap;

public class CompareReport {

    public static void compare(HashMap<String, ArrayList<YearlyConstructor>> dbYear, HashMap<String, ArrayList<MonthlyConstructor>> dbMonths, String year) {
        ArrayList<String> arrayMonth = new ArrayList<>();

        for (String month : Months.months) {
            int difMonth = 0;
            int difMonthYear = 0;

            if (dbMonths.get(month) != null) {
                int monthProfit = 0;
                int monthloss = 0;
                for (MonthlyConstructor monthlyConstructor : dbMonths.get(month)) {
                    if (!monthlyConstructor.isExpense) {
                        monthProfit += monthlyConstructor.quantity * monthlyConstructor.priceOne;
                    } else {
                        monthloss += monthlyConstructor.quantity * monthlyConstructor.priceOne;
                    }
                }
                difMonth = monthProfit - monthloss;
            }
            if (dbYear.get(year) != null) {
                int monthYearProfit = 0;
                int monthYearLoss = 0;
                for (YearlyConstructor yearlyConstructor : dbYear.get(year)) {
                    if (yearlyConstructor.monthYear.equals(month)) {
                        if (!yearlyConstructor.isExpenseYear) {
                            monthYearProfit = yearlyConstructor.amountYear;
                        } else {
                            monthYearLoss = yearlyConstructor.amountYear;
                        }
                    }
                }
                difMonthYear = monthYearProfit - monthYearLoss;
            }
            if (difMonth != difMonthYear) {
                arrayMonth.add(month);
            }
        }
        if (arrayMonth.isEmpty()) {
            System.out.println("Сверка отчетов завершена успешно! Несоответствий не обнаружено.");
        } else {
            for (String s : arrayMonth) {
                System.out.println("Обнаружено несоответствие в месяце под № " + s);
            }
        }
    }
}